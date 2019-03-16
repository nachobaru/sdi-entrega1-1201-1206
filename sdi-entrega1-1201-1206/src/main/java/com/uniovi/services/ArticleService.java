package com.uniovi.services;

import java.util.LinkedList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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

	public  Page<Article> searchByString (Pageable pageable, String searchText){
		Page<Article> marks = new PageImpl<Article>(new LinkedList<Article>());
		marks = articleRepository.searchByString(pageable, searchText);
		return marks;
	}

	public  Page<Article> searchAll (Pageable pageable, User activeUser){
		Page<Article> marks = new PageImpl<Article>(new LinkedList<Article>());
		Long user_id=activeUser.getId();
		marks = articleRepository.searchAll(pageable, user_id);
		return marks;
	}
	
	public Page<Article> buscarUserText(Pageable pageable, User u, String s) {
		return articleRepository.buscarUserText(pageable, u,s);
	}

	public Article findArticle(Long id) {

		return   articleRepository.findID(id);

	}

		public void Comprar (User u,) {
			String email = principal.getName();
			User user = userService.getUserByEmail(email);
			user.getArticles().add(article);
			article.setOwner(user);
			articleService.addArticle(article);
		}
}