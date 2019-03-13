package com.uniovi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Article;

public interface ArticleRepository extends CrudRepository<Article, Long> {
	@Query("SELECT r FROM Mark r WHERE (LOWER(r.title) LIKE LOWER(?1))")
	static
	List<Article> searchByString(String seachtext) {
		// TODO Auto-generated method stub
		return null;
	}

	@Query("SELECT r FROM Mark r")
	static
	List<Article> searchAll() {
		// TODO Auto-generated method stub
		return null;
	}
		
	
}
