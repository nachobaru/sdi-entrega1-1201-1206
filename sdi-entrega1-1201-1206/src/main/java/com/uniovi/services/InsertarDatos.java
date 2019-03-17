package com.uniovi.services;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Article;
import com.uniovi.entities.User;
import com.uniovi.repositories.UserRepository;

@Service
public class InsertarDatos {
	@Autowired
	UserService userService;
	@Autowired
	private UserRepository userRepository;
	@PostConstruct
	public void init() {
		
//		User userAdmin = new User("admin@email.com", "admin","admin");
//		userAdmin.setPassword("admin");
//		userAdmin.setRole("ROLE_ADMIN");
//		
//		User user1 = new User("user1@email.com", "NAcho","Baru");
//		user1.setPassword("11111");
//		user1.setRole("ROLE_STANDARD");
////		
//////		Article art1= new Article((long)1, "palo", "rama",10);
//////		Article art2 =new Article((long)2, "hoja", "verde lol",1231450);
//////		
//////		user1.setArticles(art1);
//////		user1.getArticles().add(art1);
//////		art1.setOwner(user1);
//////		art2.setOwner(user1);
//		userService.addUser(userAdmin);
//		userService.addUser(user1);
		
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
		Article art13 = new Article("sfg", "vsf", 10);

		Article art21 = new Article("dfv", "vdv lol", 78);
		Article art22 = new Article("dvf", "fvdfvfv lol", 56);
		Article art23 = new Article("fvddfvd", "dfv lol", 111);

		Article art31 = new Article("wefwrerf", "werfwef lol", 98);
		Article art32 = new Article("wefwefwef", "wefwf lol", 89);
		Article art33 = new Article("hwefwefweoja", "wefwef lol", 96);

		Article art41 = new Article("howefwefja", "efewf lol", 698);
		Article art42 = new Article("bdfb", "verqwdqwdde lol", 123);
		Article art43 = new Article("hwefwefoja", "vewefwefrde lol", 7);

		Article art51 = new Article("hoeefja", "verefwefde lol", 5);
		Article art52 = new Article("hoefwefja", "verefwefde lol", 63);
		Article art53 = new Article("hfewefoja", "verfwefwefbede lol", 71);

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
