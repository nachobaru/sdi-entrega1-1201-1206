package com.uniovi.services;

import java.util.LinkedList;

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
		Page<Article> art = new PageImpl<Article>(new LinkedList<Article>());
		art = articleRepository.searchByString(pageable, searchText);
		return art;
	}

	public  Page<Article> searchAll (Pageable pageable, User activeUser){
		Page<Article> art = new PageImpl<Article>(new LinkedList<Article>());
		art  = articleRepository.findByUsuario(pageable, activeUser);
		return art;
	}


	public Article findArticle(Long id) {

		return   articleRepository.findID(id);

	}


	public void Comprar (User u, Article a) {
		if(u.getPocket()>=a.getPrice()) {
			u.subtractMoney(a.getPrice());
			User b=a.getOwner();
			b.addMoney(a.getPrice());
			a.setComprado(true);
			a.setOwner(u);
			u.addArticle(a);
		}
	}

}