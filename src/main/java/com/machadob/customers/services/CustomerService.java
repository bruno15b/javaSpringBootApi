package com.machadob.customers.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.machadob.customers.entities.Customer;
import com.machadob.customers.repositories.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepository repository;

	public List<Customer> findAll() {
		return repository.findAll();
	}

	public Customer findById(Long id) {
		Optional<Customer> customerObj = repository.findById(id);
		return customerObj.get();
	}

	public Customer insert(Customer customerObj) {
		return repository.save(customerObj);
	}

	public Customer update(Long id, Customer newCustomerObjWithUpdates) {
		Customer customerObjFromDatabase = repository.getReferenceById(id);
		updateDataCustomerFromDb(customerObjFromDatabase ,newCustomerObjWithUpdates);
		return repository.save(customerObjFromDatabase);
	}

	private void updateDataCustomerFromDb(Customer OldCustomerObjFromDatabase, Customer newCustomerObjWithUpdates) {
		OldCustomerObjFromDatabase.setName(newCustomerObjWithUpdates.getName());
		OldCustomerObjFromDatabase.setBirthDate(newCustomerObjWithUpdates.getBirthDate());
	}

}
