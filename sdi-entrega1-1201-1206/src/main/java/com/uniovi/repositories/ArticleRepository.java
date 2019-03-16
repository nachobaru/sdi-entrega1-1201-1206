package com.uniovi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.uniovi.entities.Article;
import com.uniovi.entities.User;

public interface ArticleRepository extends CrudRepository<Article, Long> {
	@Query("SELECT r FROM Article r WHERE (LOWER(r.title) LIKE LOWER(?1))")
	Page<Article> searchByString(Pageable pageable, String seachtext);

//	@Query("SELECT r FROM Oferta r WHERE r.owner = ?1  ORDER BY r.id ASC  ")
//	Page<Article> searchAll(Pageable pageable,User u);
		// TODO Auto-generated method stub
	Page<Article> findAll(Pageable pageable);
	
	@Query("SELECT r FROM Article r WHERE  r.owner <> ?1   ORDER BY r.id ASC ")
	Page<Article> findByUsuario(Pageable pageable, User user);
	
	@Query("SELECT r FROM Article r WHERE(r.id = ?1) ORDER BY r.id ASC ")
	Article findID(Long id);
//	@Query("SELECT r FROM Article r WHERE NOT (r.owner= ?1) AND (LOWER(r.title) LIKE LOWER(?2) ")
//	Page<Article> buscarUserText(Pageable pageable,  User user ,String seachtext);
		

	
//	@Transactional 
//	@Modifying
//	@Query("DELETE FROM Oferta WHERE usuario.id=?1")
//	void deleteByArticleId(Long id);
}
