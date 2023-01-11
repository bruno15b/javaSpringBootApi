package com.machadob.customers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.machadob.customers.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
