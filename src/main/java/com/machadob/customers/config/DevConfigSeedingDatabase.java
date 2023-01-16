package com.machadob.customers.config;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.machadob.customers.entities.Address;
import com.machadob.customers.entities.Customer;
import com.machadob.customers.repositories.AddressRepository;
import com.machadob.customers.repositories.CustomerRepository;

@Configuration
@Profile("dev")
public class DevConfigSeedingDatabase implements CommandLineRunner {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		Customer c1 = new Customer(null,"Bruno Borges Machado", LocalDate.parse("1996-01-30"));
		Customer c2 = new Customer(null,"João Gomes Alexandre", LocalDate.parse("1980-07-14"));
		Customer c3 = new Customer(null,"Gabriela Fonseca Assis", LocalDate.parse("1992-02-25"));
		Customer c4 = new Customer(null,"Marcia Vonsik", LocalDate.parse("1970-08-10"));
		
		customerRepository.saveAll(Arrays.asList(c1,c2,c3,c4));
		
		Address ad1 = new Address(null,"Rua Equador",125,"123587-741","São Paulo");
		Address ad2 = new Address(null,"Av California",550,"235784-665","São Paulo");
		Address ad3 = new Address(null,"Rua Oswaldo Cruz",52,"125473-885","Pedra Bela");
		Address ad4 = new Address(null,"Rua Benedicto dos Santos",13,"198745-302","Salvador");
		Address ad5 = new Address(null,"Rua Antonio Caudato",117,"118587-772","Brasilia");
		Address ad6 = new Address(null,"Av.Edgar Vieira",312,"129122-752","Cuiabá");
		
		addressRepository.saveAll(Arrays.asList(ad1,ad2,ad3,ad4,ad5,ad6));
		
		c1.getAddresses().add(ad6);
		c1.getAddresses().add(ad4);
		c1.getAddresses().add(ad2);
		c2.getAddresses().add(ad1);
		c3.getAddresses().add(ad3);
		c4.getAddresses().add(ad6);
		c4.getAddresses().add(ad2);
		c4.getAddresses().add(ad5);
		
		c1.setPrincipalAdress(ad6);
		c2.setPrincipalAdress(ad1);
		c3.setPrincipalAdress(ad3);
		c4.setPrincipalAdress(ad6);
		
		customerRepository.saveAll(Arrays.asList(c1,c2,c3,c4));
		
	}
	

}
