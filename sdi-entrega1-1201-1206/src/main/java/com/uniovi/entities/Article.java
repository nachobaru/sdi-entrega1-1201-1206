package com.uniovi.entities;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
public class Article {
	@Id
	@GeneratedValue
	private Long id;
	private String title;
	private String description;
	@Temporal(TemporalType.DATE)
	private Calendar date;
	private double price;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User owner;
	
	public Article(Long id, String title, String description,  double price) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		Calendar today =  Calendar.getInstance();
		setDate(today);
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", description=" + description + ", date=" + date + ", price="
				+ price + "]";
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}
	
	
	
	
}
