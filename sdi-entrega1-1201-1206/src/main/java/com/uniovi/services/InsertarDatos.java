package com.uniovi.services;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Article;
import com.uniovi.entities.User;

@Service
public class InsertarDatos {
	@Autowired
	UserService userService;

	@PostConstruct
	public void init() {		
		User user1 = new User("user1@email.com", "NAcho", "Baru");
		user1.setPassword("11111");
		user1.setRole("ROLE_STANDARD");

		User user2 = new User("user2@email.com", "Fer", "Ruiz");
		user2.setPassword("11111");
		user2.setRole("ROLE_STANDARD");

		User user3 = new User("user3@email.com", "Ale", "Church");
		user3.setPassword("11111");
		user3.setRole("ROLE_STANDARD");

		User user4 = new User("admin@email.com", "admin", "admin");
		user4.setPassword("admin");
		user4.setRole("ROLE_ADMIN");

		User user5 = new User("user4@email.com", "Miguel", "Puerta");
		user5.setPassword("admin");
		user5.setRole("ROLE_STANDARD");


		Article art11 = new Article("palo", "rama", 10);
		Article art12 = new Article("hoja", "verde lol", 45);
		Article art13 = new Article("Redmi Note 7", "movil", 100);

		Article art21 = new Article("Iphone", "vdv lol", 1000);
		Article art22 = new Article("Galaxy 10", "movil", 700);
		Article art23 = new Article("Pocophone", "movil", 500);

		Article art31 = new Article("Powerade", "bebida", 1);
		Article art32 = new Article("Hucha cerdo", "lo dicho.", 20);
		Article art33 = new Article("Cargador 3.0", "lo dicho lol", 15);

		Article art41 = new Article("Bateria extrible", "bateria", 698);
		Article art42 = new Article("Gafas de sol", "asi no t quedas ciego", 123);
		Article art43 = new Article("Funda movil", "fundita chuli", 7);

		Article art51 = new Article("Lampara ikea", "luz loko", 5);
		Article art52 = new Article("Boli BIC", "ye de oro", 100);
		Article art53 = new Article("Mochila Dora la Exploradora", "te resuelve tus problemas :)", 1000);

		art11.setOwner(user1);
		art12.setOwner(user1);
		art13.setOwner(user1);

		user1.addArticle(art11);
		user1.addArticle(art12);
		user1.addArticle(art13);

		art21.setOwner(user2);
		art22.setOwner(user2);
		art23.setOwner(user2);

		user2.addArticle(art21);
		user2.addArticle(art22);
		user2.addArticle(art23);

		art31.setOwner(user3);
		art32.setOwner(user3);
		art33.setOwner(user3);

		user3.addArticle(art31);
		user3.addArticle(art32);
		user3.addArticle(art33);

		art41.setOwner(user4);
		art42.setOwner(user4);
		art43.setOwner(user4);

		user4.addArticle(art41);
		user4.addArticle(art42);
		user4.addArticle(art43);

		art51.setOwner(user5);
		art52.setOwner(user5);
		art53.setOwner(user5);

		user5.addArticle(art51);
		user5.addArticle(art52);
		user5.addArticle(art53);

		userService.addUser(user1);
		userService.addUser(user2);
		userService.addUser(user3);
		userService.addUser(user4);
		userService.addUser(user5);
	}
}
