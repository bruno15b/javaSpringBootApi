package com.machadob.customers.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.machadob.customers.entities.Customer;
import com.machadob.customers.repositories.CustomerRepository;

@ExtendWith(SpringExtension.class)
public class CustomerServiceTests {
	
	@InjectMocks
	private CustomerService service;
	
	@Mock
	private CustomerRepository repository;
	
	Customer customer1, customer2, customer3;
	private long existingId;
	private long nonExistingId;
	private long countTotalCustomers;
	final List<Customer> customers = new ArrayList<>();
	
	@BeforeEach
	void setUp() throws Exception {
		
		countTotalCustomers = 6L;
		existingId = 1L;
		nonExistingId = 999999L;

		customer1 = new Customer(1L, "UsuarioForTest1", LocalDate.parse("2000-01-15"));
		customer2 = new Customer(2L, "UsuarioForTest3", LocalDate.parse("2001-02-16"));
		customer3 = new Customer(3L, "UsuarioForTest2", LocalDate.parse("2002-03-17"));
		
		customers.add(customer1);
		customers.add(customer2);
		customers.add(customer3);
			
		Mockito.when(repository.findAll()).thenReturn(customers);
		Mockito.when(repository.findById(existingId)).thenReturn(Optional.of(customer1));
	}
	
	@Test
	public void FindAllCustomersShouldReturnAListOfCustomers() {
	    List<Customer> foundCustomers = service.findAllCustomers();

	    Assertions.assertNotNull(foundCustomers);
	    Assertions.assertEquals(3, foundCustomers.size());
	    Mockito.verify(repository).findAll();
	}
	
	@Test
	public void FindByIdCustomerShouldBeReturnNonEmptyWhenIdExist(){
		Customer result = service.findCustomerById(existingId);
		Assertions.assertEquals(result, customer1);
		Mockito.verify(repository).findById(existingId);
	}
	

	
}
