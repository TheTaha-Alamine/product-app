package com.imedia24.serverside.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "category")
public class Category extends AbstractEntity{

	@Column(name = "name" , nullable = false)
	private String name;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL ,fetch = FetchType.LAZY, mappedBy = "category")
	private Set<Product> products ;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	
	
	
}
