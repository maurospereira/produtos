package com.best2bee.products.model;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(min = 4, max = 150)
	@Column(nullable = false, length = 150)
	private String name;

	
	
	@Column(name = "create_date", nullable = false)
	@JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss", timezone = "America/Sao_Paulo")
	private Instant createDate;

	@NotNull
	@Column(nullable = false)
	private Double price;

	@NotNull
	@Column(nullable = false)
	private Float amount;

	public Product() {
		super();
	}

	public Product(Long id, String name, Instant createDate, Double price, Float amount) {
		super();
		this.id = id;
		this.name = name;
		this.createDate = createDate;
		this.price = price;
		this.amount = amount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Instant getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Instant createDate) {
		this.createDate = createDate;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}
	
}