package com.machadob.customers.config;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.machadob.customers.entities.Customer;
import com.machadob.customers.repositories.CustomerRepository;

@Configuration
@Profile("dev")
public class DevConfigSeedingDatabase implements CommandLineRunner {
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public void run(String... args) throws Exception {
		
		Customer c1 = new Customer(null,"Bruno Borges Machado", LocalDate.parse("1996-01-30"));
		Customer c2 = new Customer(null,"João Gomes Alexandre", LocalDate.parse("1980-07-14"));
		Customer c3 = new Customer(null,"Gabriela Fonseca Assis", LocalDate.parse("1992-02-25"));
		Customer c4 = new Customer(null,"Marcia Vonsik", LocalDate.parse("1970-08-10"));
		
		customerRepository.saveAll(Arrays.asList(c1,c2,c3,c4));
		
	}
	

}
