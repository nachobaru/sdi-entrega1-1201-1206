package com.uniovi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Article;
import com.uniovi.entities.User;

public interface ArticleRepository extends CrudRepository<Article, Long> {
	@Query("SELECT r FROM Article r WHERE (LOWER(r.title) LIKE LOWER(?1))")
	Page<Article> searchByString(Pageable pageable, String seachtext);

	@Query("SELECT r FROM Article r WHERE NOT (r.owner= ?1) ORDER BY r.id ASC ")
	Page<Article> searchAll(Pageable pageable,Long user_id);
		// TODO Auto-generated method stub
	Page<Article> findAll(Pageable pageable);
	
	@Query("SELECT r FROM Article r WHERE(r.id = ?1) ORDER BY r.id ASC ")
	Article findID(Long id);
}
