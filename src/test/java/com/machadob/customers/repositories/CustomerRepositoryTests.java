package com.machadob.customers.repositories;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.machadob.customers.entities.Customer;

@DataJpaTest
public class CustomerRepositoryTests {

	@Autowired
	CustomerRepository repository;

	Customer customer1, customer2, customer3;
	private long existingId;
	private long nonExistingId;
	private long countTotalCustomers;

	@BeforeEach
	void setUp() throws Exception {
		countTotalCustomers = 6L;
		existingId = 1L;
		nonExistingId = 999999L;

		customer1 = new Customer(null, "UsuarioForTest1", LocalDate.parse("2000-01-15"));
		customer2 = new Customer(null, "UsuarioForTest3", LocalDate.parse("2001-02-16"));
		customer3 = new Customer(null, "UsuarioForTest2", LocalDate.parse("2002-03-17"));
		repository.saveAll(Arrays.asList(customer1, customer2, customer3));
	}
	
	@Test
	public void saveShouldPersistWithAutoIncrementWhenIdIsNull() {

		Customer customer = new Customer(999L, "UsuarioShouldNotNull", LocalDate.parse("2000-01-15"));
		customer.setId(null);
		customer = repository.save(customer);
		
		Optional<Customer> result = repository.findById(customer.getId());

		Assertions.assertNotNull(customer.getId());
		Assertions.assertEquals(countTotalCustomers + 1, customer.getId());
		Assertions.assertTrue(result.isPresent());
		Assertions.assertSame(result.get(), customer);
	}
	
	@Test
	public void findByIdShouldReturnNonEmptyOptionalWhenIdExist() {
		Optional<Customer> result = repository.findById(existingId);
		Assertions.assertTrue(result.isPresent());
	}
	
	@Test
	public void findByIdShouldReturnNonEmptyOptionalWhenIdDoesNotExist() {
		Optional<Customer> result = repository.findById(nonExistingId);
		Assertions.assertTrue(result.isEmpty());
	}

}
