package com.uniovi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Article;
import com.uniovi.entities.User;

public interface ArticleRepository extends CrudRepository<Article, Long> {
	@Query("SELECT r FROM Article r WHERE (LOWER(r.title) LIKE LOWER(?1))")
	List<Article> searchByString(String seachtext);

	@Query("SELECT r FROM Article r WHERE NOT (r.owner = ?1) ORDER BY r.id ASC ")
	List<Article> searchAll(User activeUser);
		// TODO Auto-generated method stub
	
	
		
	
}
