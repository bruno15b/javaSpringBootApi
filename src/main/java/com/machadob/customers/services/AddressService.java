package com.machadob.customers.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.machadob.customers.entities.Address;
import com.machadob.customers.repositories.AddressRepository;
import com.machadob.customers.services.exceptions.ResourceNotFoundException;

@Service
public class AddressService {
	@Autowired
	private AddressRepository repository;

	public List<Address> findAllAddress() {
		return repository.findAll();
	}

	public Address findAddressById(Long id) {
		Optional<Address> AddressObj = repository.findById(id);
		return AddressObj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Address insertAddress(Address AddressObj) {
		return repository.save(AddressObj);
	}
}
