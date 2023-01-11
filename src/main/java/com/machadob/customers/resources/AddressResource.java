package com.machadob.customers.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.machadob.customers.entities.Address;
import com.machadob.customers.services.AddressService;

@RestController
@RequestMapping(value = "/address")
public class AddressResource {

	@Autowired
	private AddressService service;

	@GetMapping
	public ResponseEntity<List<Address>> findAll() {
		List<Address> listOfObjsAddress = service.findAllAddress();
		return ResponseEntity.ok().body(listOfObjsAddress);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Address> findById(@PathVariable Long id) {
		Address objAddress = service.findAddressById(id);
		return ResponseEntity.ok().body(objAddress);
	}

	@PostMapping
	public ResponseEntity<Address> insert(@RequestBody Address objAddress) {
		objAddress = service.insertAddress(objAddress);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id)").buildAndExpand(objAddress.getId())
				.toUri();
		return ResponseEntity.created(uri).body(objAddress);
	}
}
