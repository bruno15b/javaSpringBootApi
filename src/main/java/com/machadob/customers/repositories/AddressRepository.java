package com.machadob.customers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.machadob.customers.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
