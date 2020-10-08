package com.best2bee.products.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "address")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(min = 5, max = 40)
	private String street;

	@Size(min = 5, max = 40)
	private String neighborhood;

	@NotNull
	private Integer number;

	@Size(min = 8, max = 9)
	private String zip;

	@Size(min = 3, max = 40)
	private String city;

	@NotNull
	@Size(min = 2, max = 2)
	private String state;

	private String complement;

	public Address() {
		super();
	}

	public Address(Long id, String street, String neighborhood, Integer number, String zip, String city, String state,
			String complement) {
		super();
		this.id = id;
		this.street = street;
		this.neighborhood = neighborhood;
		this.number = number;
		this.zip = zip;
		this.city = city;
		this.state = state;
		this.complement = complement;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	

}