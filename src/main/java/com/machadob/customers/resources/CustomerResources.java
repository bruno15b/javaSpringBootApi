package com.machadob.customers.resources;

import java.net.URI;
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

import com.machadob.customers.entities.Customer;
import com.machadob.customers.services.CustomerService;

@RestController
@RequestMapping(value = "/customers")
public class CustomerResources {

	@Autowired
	private CustomerService service;

	@GetMapping
	public ResponseEntity<List<Customer>> findAll() {
		List<Customer> listOfObjsCustomer = service.findAll();
		return ResponseEntity.ok().body(listOfObjsCustomer);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Customer> findById(@PathVariable Long id) {
		Customer objCustomer = service.findById(id);
		return ResponseEntity.ok().body(objCustomer);
	}

	@PostMapping
	public ResponseEntity<Customer> insert(@RequestBody Customer objCustomer) {
		objCustomer = service.insert(objCustomer);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id)").buildAndExpand(objCustomer.getId())
				.toUri();
		return ResponseEntity.created(uri).body(objCustomer);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Customer> update(@PathVariable Long id, @RequestBody Customer objCustomer) {
		objCustomer = service.update(id, objCustomer);
		return ResponseEntity.ok().body(objCustomer);
	}
}
