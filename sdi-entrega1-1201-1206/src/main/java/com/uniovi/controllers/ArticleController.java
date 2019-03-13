package com.uniovi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniovi.entities.Article;
import com.uniovi.services.ArticleService;
import com.uniovi.services.UserService;

@Controller
public class ArticleController {

	@Autowired
	private ArticleService articleService;
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/article/add", method = RequestMethod.POST)
	public String setArticle(@ModelAttribute Article article) {
		articleService.addArticle(article);
		return "redirect:/article/list";
	}
	
	@RequestMapping(value = "/article/add")
	public String getArticle(Model model) {
		model.addAttribute("usersList", userService.getUsers());
		return "article/add";
	}
}
