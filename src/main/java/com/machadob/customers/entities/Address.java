package com.machadob.customers.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_address")
public class Address implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String streetOrAvenueName;
	private Integer addressNumber;
	private String zipCode;
	private String cityName;

	@JsonIgnore
	@ManyToMany(mappedBy = "addresses")
	private Set<Customer> customer = new HashSet<>();

	@JsonIgnore
	@OneToOne(mappedBy = "principalAddress")
	private Customer customerMainAddress;

	public Address() {
	}

	public Address(Long id, String streetOrAvenueName, Integer addressNumber, String zipCode, String cityName) {
		this.id = id;
		this.streetOrAvenueName = streetOrAvenueName;
		this.addressNumber = addressNumber;
		this.zipCode = zipCode;
		this.cityName = cityName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStreetOrAvenueName() {
		return streetOrAvenueName;
	}

	public void setStreetOrAvenueName(String streetOrAvenueName) {
		this.streetOrAvenueName = streetOrAvenueName;
	}

	public Integer getAddressNumber() {
		return addressNumber;
	}

	public void setAddressNumber(Integer addressNumber) {
		this.addressNumber = addressNumber;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Set<Customer> getCustomer() {
		return customer;
	}

	public Customer getCustomerMainAddress() {
		return customerMainAddress;
	}

	public void setCustomerMainAddress(Customer customerMainAddress) {
		this.customerMainAddress = customerMainAddress;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((addressNumber == null) ? 0 : addressNumber.hashCode());
		result = prime * result + ((cityName == null) ? 0 : cityName.hashCode());
		result = prime * result + ((streetOrAvenueName == null) ? 0 : streetOrAvenueName.hashCode());
		result = prime * result + ((zipCode == null) ? 0 : zipCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		if (addressNumber == null) {
			if (other.addressNumber != null)
				return false;
		} else if (!addressNumber.equals(other.addressNumber))
			return false;
		if (cityName == null) {
			if (other.cityName != null)
				return false;
		} else if (!cityName.equals(other.cityName))
			return false;
		if (streetOrAvenueName == null) {
			if (other.streetOrAvenueName != null)
				return false;
		} else if (!streetOrAvenueName.equals(other.streetOrAvenueName))
			return false;
		if (zipCode == null) {
			if (other.zipCode != null)
				return false;
		} else if (!zipCode.equals(other.zipCode))
			return false;
		return true;
	}

}
