package com.uniovi.controllers;

import java.security.Principal;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
		User activeUser = getActiveUser();
		model.addAttribute("money", activeUser.getPocket());
		return "article/add";
	}

	@RequestMapping("/article/delete/{id}")
	public String deleteMark(@PathVariable Long id) {
		articleService.deleteArticle(id);
		return "redirect:/home";
	}

//	@RequestMapping(value="/article/list", method = RequestMethod.GET)
//	public String getSearch(Model model, Pageable pageable, Principal principal,
//			@RequestParam(value = "", required = false) String searchText) {
//		searchText = "%" + searchText + "%";
//		Page<Article> art = new PageImpl<Article>(new LinkedList<Article>());
//		if (searchText != null && !searchText.isEmpty()) {
//			art = articleService.buscarUserText(pageable,getActiveUser(), searchText);
//		}else {
//			art=articleService.searchAll(pageable, getActiveUser());
//		}
//		model.addAttribute("articlesList", art.getContent());
//		model.addAttribute("page", art);
//		return "/article/list";
//	}

	@RequestMapping("/article/list/update")
	public String updateList(Model model,Pageable pageable, Principal principal) {
	
		User activeUser = getActiveUser();
		model.addAttribute("articlesList", articleService.searchAll(pageable, activeUser));
		return "/article/list :: tableArticles";
	}
	private User getActiveUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User activeUser = userService.getUserByEmail(email);
		return activeUser;
	}
//	@RequestMapping(value="/article/buy/{id}", method = RequestMethod.POST)
//	public String getBuy(Model model, Pageable pageable, Principal principal, @PathVariable Long id) {
//		User u= getActiveUser();
//		Article a=articleService.findArticle(id);
//		if(u.getPocket()>=a.getPrice()) {
//			Comprar(u,a);
//		}
//		return "/article/list/update";
//	}


}
