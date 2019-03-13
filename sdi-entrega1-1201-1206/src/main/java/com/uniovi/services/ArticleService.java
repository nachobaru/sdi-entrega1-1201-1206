package com.uniovi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Article;
import com.uniovi.repositories.ArticleRepository;

@Service
public class ArticleService {
	@Autowired
	private ArticleRepository articleRepository;

	public void addArticle(Article article) {
		articleRepository.save(article);
	}

	public void deleteArticle(Long id) {
		articleRepository.deleteById(id);
	}
}