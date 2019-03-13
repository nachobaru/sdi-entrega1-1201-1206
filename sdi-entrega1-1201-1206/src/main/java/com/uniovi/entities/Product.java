package com.uniovi.entities;

import javax.persistence.*;

@Entity
public class Product {
	@Id
	@GeneratedValue
	private Long id;
	private String description;
	private Double price;
	private int UnitsAvalible = 1;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public Product(Long id, String description, Double score) {
		super();
		this.id = id;
		this.description = description;
		this.price = score;
	}

	public Product(String description, Double score, User user) {
		super();
		this.description = description;
		this.price = score;
		this.user = user;
	}

	public Product() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getScore() {
		return price;
	}

	public void setScore(Double score) {
		this.price = score;
	}

	public User getUser() {
		return user;
	}
	public void setUser(User user ) {
		this.user = user;
	}

	public int getUnitsAvalible() {
		return UnitsAvalible;
	}

	public void setUnitsAvalible(int unitsAvalible) {
		UnitsAvalible = unitsAvalible;
	}

	// Lo pongo abajo porque estoy acostumbrado a que este aqui
	@Override
	public String toString() {
		return "Mark [id=" + id + ", description=" + description + ", score=" + price + "]";
	}

}
