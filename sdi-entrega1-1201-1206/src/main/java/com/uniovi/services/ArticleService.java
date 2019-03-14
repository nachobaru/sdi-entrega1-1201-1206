package com.uniovi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Article;
import com.uniovi.entities.User;
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

	public  List<Article> searchByString (String searchText){
		List<Article> marks = new ArrayList<Article>();
		marks = articleRepository.searchByString(searchText);
		return marks;
	}
	
	public  List<Article> searchAll (User activeUser){
		List<Article> marks = new ArrayList<Article>();
		marks = articleRepository.searchAll(activeUser);
		return marks;
	}
}