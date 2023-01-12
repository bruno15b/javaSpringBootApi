package com.machadob.customers.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.machadob.customers.entities.Address;
import com.machadob.customers.entities.Customer;
import com.machadob.customers.repositories.CustomerRepository;
import com.machadob.customers.services.AddressService;
import com.machadob.customers.services.CustomerService;

@RestController
@RequestMapping(value = "/customers")
public class CustomerResource {

	@Autowired
	private CustomerService service;
	
	@Autowired
	private AddressService serviceAddress;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@GetMapping
	public ResponseEntity<List<Customer>> findAll() {
		List<Customer> listOfObjsCustomer = service.findAllCustomers();
		return ResponseEntity.ok().body(listOfObjsCustomer);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Customer> findById(@PathVariable Long id) {
		Customer objCustomer = service.findCustomerById(id);
		return ResponseEntity.ok().body(objCustomer);
	}
	
	@GetMapping(value = "/{id}/all_addresses")
	public ResponseEntity<List<Address>>  findAllAdressCustomer(@PathVariable Long id) {
		Customer objCustomer = service.findCustomerById(id);
		List<Address> list = new ArrayList<>();
		list.addAll(objCustomer.getAddresses());
		return ResponseEntity.ok().body(list);
	}

	@PostMapping
	public ResponseEntity<Customer> insertCustomer(@RequestBody Customer objCustomer) {
		objCustomer = service.insertCustomer(objCustomer);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(objCustomer.getId())
				.toUri();
		return ResponseEntity.created(uri).body(objCustomer);
	}
	
	@PostMapping(value = "/{id}/add_address")
	public ResponseEntity<Address> createAdressForCustomer(@PathVariable Long id,@RequestBody Address objAddress) {
		objAddress = serviceAddress.insertAddress(objAddress);
		 URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(objAddress.getId()).toUri();
		 service.findCustomerById(id).getAddresses().add(objAddress);
		 customerRepository.save(service.findCustomerById(id));
		return ResponseEntity.created(uri).body(objAddress);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Customer> updateCustomerInformations(@PathVariable Long id, @RequestBody Customer objCustomer) {
		objCustomer = service.updateCustomer(id, objCustomer);
		return ResponseEntity.ok().body(objCustomer);
	}
	
	@PutMapping(value = "/{id}/{adressId}")
	public ResponseEntity<Customer> assignMainAddress(@PathVariable Long id, @PathVariable Long adressId) {
		service.findCustomerById(id).setPrincipalAdress(serviceAddress.findAddressById(adressId));
		service.findCustomerById(id).getAddresses().add(serviceAddress.findAddressById(adressId));
		customerRepository.save(service.findCustomerById(id));
		return ResponseEntity.ok().body(service.findCustomerById(id));
	}
}
