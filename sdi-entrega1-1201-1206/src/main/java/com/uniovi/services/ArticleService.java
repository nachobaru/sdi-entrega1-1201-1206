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

	public static List<Article> searchByString (String searchText){
		List<Article> marks = new ArrayList<Article>();
		marks = ArticleRepository.searchByString(searchText);
		return marks;
	}
	
	public static List<Article> searchAll (){
		List<Article> marks = new ArrayList<Article>();
		marks = ArticleRepository.searchAll();
		return marks;
	}
}