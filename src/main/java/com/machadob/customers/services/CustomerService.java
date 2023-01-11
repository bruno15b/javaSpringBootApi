package com.machadob.customers.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.machadob.customers.entities.Customer;
import com.machadob.customers.repositories.CustomerRepository;
import com.machadob.customers.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepository repository;

	public List<Customer> findAllCustomers() {
		return repository.findAll();
	}

	public Customer findCustomerById(Long id) {
		Optional<Customer> customerObj = repository.findById(id);
		return customerObj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Customer insertCustomer(Customer customerObj) {
		return repository.save(customerObj);
	}

	public Customer updateCustomer(Long id, Customer newCustomerObjWithUpdates) {
		try {
			Customer customerObjFromDatabase = repository.getReferenceById(id);
			updateDataCustomerFromDb(customerObjFromDatabase, newCustomerObjWithUpdates);
			return repository.save(customerObjFromDatabase);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateDataCustomerFromDb(Customer OldCustomerObjFromDatabase, Customer newCustomerObjWithUpdates) {
		OldCustomerObjFromDatabase.setName(newCustomerObjWithUpdates.getName());
		OldCustomerObjFromDatabase.setBirthDate(newCustomerObjWithUpdates.getBirthDate());
	}
}
