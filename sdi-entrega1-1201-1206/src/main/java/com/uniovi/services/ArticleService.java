package com.uniovi.services;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Article;
import com.uniovi.repositories.ArticleRepository;

@Service
public class ArticleService {
	@Autowired
	private HttpSession httpSession;
	
	@Autowired
	private ArticleRepository articleRepository;
	
	public void addArticle(Article  article) {
		httpSession.getAttribute("user");
		articleRepository.save(article);
	}
}