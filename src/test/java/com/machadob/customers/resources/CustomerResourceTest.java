package com.machadob.customers.resources;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.machadob.customers.entities.Customer;
import com.machadob.customers.services.CustomerService;
import com.machadob.customers.services.exceptions.ResourceNotFoundException;

@WebMvcTest(CustomerResource.class)
public class CustomerResourceTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private CustomerService service;

	Customer customer1, customer2, customer3;
	private long existingId;
	private long nonExistingId;
	final List<Customer> customers = new ArrayList<>();

	@BeforeEach
	void setUp() throws Exception {

		existingId = 1L;
		nonExistingId = 999999L;

		customer1 = new Customer(1L, "UsuarioForTest1", LocalDate.parse("2000-01-15"));
		customer2 = new Customer(2L, "UsuarioForTest3", LocalDate.parse("2001-02-16"));
		customer3 = new Customer(3L, "UsuarioForTest2", LocalDate.parse("2002-03-17"));

		customers.add(customer1);
		customers.add(customer2);
		customers.add(customer3);

		when(service.findAllCustomers()).thenReturn(customers);
		when(service.findCustomerById(existingId)).thenReturn(customer1);
		when(service.findCustomerById(nonExistingId)).thenThrow(ResourceNotFoundException.class);
		when(service.updateCustomer(eq(existingId), any())).thenReturn(customer1);
		when(service.updateCustomer(eq(nonExistingId), any())).thenThrow(ResourceNotFoundException.class);
	}

	@Test
	public void findAllShouldReturn() throws Exception {
		ResultActions result = mockMvc.perform(get("/customers").accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isOk());
	}

	@Test
	public void findByIdShouldReturnCostumerWhenIDExist() throws Exception {
		ResultActions result = mockMvc.perform(get("/customers/{id}", existingId).accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.id").exists());
		result.andExpect(jsonPath("$.name").exists());
		result.andExpect(jsonPath("$.birthDate").exists());

	}

	@Test
	public void findByIdShouldReturnNotFoundWhenIDNonExist() throws Exception {
		ResultActions result = mockMvc
				.perform(get("/customers/{id}", nonExistingId).accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isNotFound());

	}

	@Test
	public void updateShouldReturnCustomerWhenIdExists() throws Exception {
		
		String jsonBody = objectMapper.writeValueAsString(customer2);

		ResultActions result = mockMvc.perform(put("/customers/{id}", existingId).content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));

		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.id").exists());
		result.andExpect(jsonPath("$.name").exists());
		result.andExpect(jsonPath("$.birthDate").exists());
	}

	@Test
	public void updateShouldReturnNotFoundWhenIdExists() throws Exception {

		String jsonBody = objectMapper.writeValueAsString(customer2);

		ResultActions result = mockMvc.perform(put("/customers/{id}", nonExistingId).content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));

		result.andExpect(status().isNotFound());
	}

}
