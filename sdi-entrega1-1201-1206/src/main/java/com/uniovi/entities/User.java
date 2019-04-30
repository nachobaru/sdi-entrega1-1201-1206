package com.uniovi.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
@Entity
public class User {

	@Id
	@GeneratedValue
	private long id;
	private String name;
	private String lastName;
	private String role;
	private String password;
	private double pocket=100;
	//Set<Article> comprados;
	@Transient 
	private String passwordConfirm;
	@Column(unique = true)
	private String email;
	@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
	Set<Article> articles;

	
	
	public User(String email, String name, String lastName) {
		super();
		this.email=email;
		this.name = name;
		this.lastName = lastName;
		this.pocket=100;
		articles= new HashSet<Article>();
	}
	public User() {
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return this.name + " " + this.lastName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
	public Set<Article> getArticles() {
		return articles;
	}

	public void setArticles(Set<Article> articles) {
		this.articles = articles;
	}
	public double getPocket() {
		return pocket;
	}
	public void setPocket(double pocket) {
		this.pocket = pocket;
	}
	public void addMoney (double plus) {
		setPocket(getPocket()+plus);
	}
	public void subtractMoney(double amount) {
		setPocket(getPocket()-amount);
	}
	
	public void addArticle(Article art) {
		articles.add(art);
	}
//	public Set <Article> getComprados() {
//		return comprados;
//	}
//	public void setComprados(Article article) {
//		this.comprados.add(article);
//	}
//	public Set<Article> getComprados(){
//		Page<Article> art = new PageImpl<Article>(new LinkedList<Article>());
//		
//		Article[] aux= (Article[]) articles.toArray();
//	
//		for(int i=0; i<articles.size();i++) {
//			if(aux[i].isComprado()==true) {
//				art.
//			}
//			
//		}
//	}
	
	
}
