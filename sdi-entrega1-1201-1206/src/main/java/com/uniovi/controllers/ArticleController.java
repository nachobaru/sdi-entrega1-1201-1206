package com.uniovi.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.uniovi.entities.Article;
import com.uniovi.entities.User;
import com.uniovi.services.ArticleService;
import com.uniovi.services.UserService;
import com.uniovi.validators.AddArticleValidator;

@Controller
public class ArticleController {

	@Autowired
	private ArticleService articleService;
	@Autowired
	private UserService userService;
	@Autowired
	private AddArticleValidator articleValidator;

	@RequestMapping(value = "/article/add", method = RequestMethod.POST)
	public String setArticle(@ModelAttribute @Validated Article article, BindingResult result, Principal principal) {
		articleValidator.validate(article, result);
		if (result.hasErrors()) {
			return "/article/add";
		}
		String email = principal.getName();
		User user = userService.getUserByEmail(email);
		user.getArticles().add(article);
		article.setOwner(user);
		articleService.addArticle(article);
		return "redirect:/home";
	}

	@RequestMapping(value = "/article/add")
	public String getArticle(Model model) {
		model.addAttribute("article", new Article());
		// TO DO
		return "article/add";
	}

	@RequestMapping("/article/delete/{id}")
	public String deleteMark(@PathVariable Long id) {
		articleService.deleteArticle(id);
		return "redirect:/home";
	}

	@RequestMapping("/article/search")
	public String getSearch(Model model, Principal principal,
			@RequestParam(value = "", required = false) String searchText) {
		searchText = "%" + searchText + "%";
		if (searchText != null && !searchText.isEmpty()) {
			model.addAttribute("articlesList", articleService.searchByString(searchText));
		}
		return "redirect:/article/list";
	}

	@RequestMapping("/article/list")
	public String getList(Model model, Principal principal) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User activeUser = userService.getUserByEmail(email);
		model.addAttribute("articlesList", articleService.searchAll(activeUser));
		// TO DO
		return "/article/list";
	}

}
