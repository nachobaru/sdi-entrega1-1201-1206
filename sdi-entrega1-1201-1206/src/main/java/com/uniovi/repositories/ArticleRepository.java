package com.uniovi.repositories;

import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Article;

public interface ArticleRepository extends CrudRepository<Article, Long> {
	
}
