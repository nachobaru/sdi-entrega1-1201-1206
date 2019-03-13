package com.uniovi.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniovi.entities.Article;
import com.uniovi.entities.User;
import com.uniovi.services.ArticleService;
import com.uniovi.services.UserService;

@Controller
public class ArticleController {

	@Autowired
	private ArticleService articleService;
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/article/add", method = RequestMethod.POST)
	public String setArticle(@ModelAttribute Article article,Principal principal) {
		String email=principal.getName();
		User user= userService.getUserByEmail(email);
		user.getArticles().add(article);
		article.setOwner(user);
		articleService.addArticle(article);
		return "redirect:/home";
	}
	
	@RequestMapping(value = "/article/add")
	public String getArticle(Model model) {
		model.addAttribute("usersList", userService.getUsers());
		return "article/add";
	}
	
	
	@RequestMapping("/article/delete/{id}")
	public String deleteMark(@PathVariable Long id) {
		articleService.deleteArticle(id);
		return "redirect:/home";
	}
}
