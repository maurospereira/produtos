package com.best2bee.products.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Sale implements Serializable {

	private static final long serialVersionUID = -2390553894772640423L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Client client;

	@Column(name = "create_date", nullable = false)
	@JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss", timezone = "America/Sao_Paulo")
	private Instant createDate;


	@OneToMany(mappedBy = "sale")
	@OnDelete(action = OnDeleteAction.CASCADE)
	List<SaleItem> items = new ArrayList<SaleItem>();	

	public Sale() {
		super();
	}

	public Sale(Long id, Instant createDate, Client client) {
		super();
		this.id = id;
		this.createDate = createDate;
		this.client = client;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Instant getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Instant createDate) {
		this.createDate = createDate;
	}

	public List<SaleItem> getItems() {
		return items;
	}

	public void setItems(List<SaleItem> items) {
		this.items = items;
	}
	
}