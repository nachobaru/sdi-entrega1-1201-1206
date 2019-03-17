package com.uniovi.controllers;



import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
import com.uniovi.services.RoleService;
import com.uniovi.services.SecurityService;
import com.uniovi.services.UserService;
import com.uniovi.validators.SignUpFormValidator;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private SecurityService securityService;
	@Autowired
	private SignUpFormValidator signUpFormValidator;
	@Autowired
	private RoleService roleService;

	@RequestMapping(value = "/user/add", method = RequestMethod.POST)
	public String setUser(@ModelAttribute User user) {
		userService.addUser(user);
		return "redirect:/user/list";
	}

	@RequestMapping("/user/details/{id}")
	public String getDetail(Model model, @PathVariable Long id) {
		model.addAttribute("user", userService.getUser(id));
		User activeUser = getActiveUser();
		model.addAttribute("money", activeUser.getPocket());
		return "user/details";
	}

	@RequestMapping("/user/list")
	public String getListado(Model model) {
		User activeUser = getActiveUser();
		model.addAttribute("money", activeUser.getPocket());
		model.addAttribute("usersList", userService.getUsers());
		return "/user/list";
	}

	@RequestMapping(value = "/user/edit/{id}")
	public String getEdit(Model model, @PathVariable Long id) {
		User user = userService.getUser(id);
		model.addAttribute("user", user);
		User activeUser = getActiveUser();
		model.addAttribute("money", activeUser.getPocket());
		return "user/edit";
	}

	@RequestMapping(value = "/user/edit/{id}", method = RequestMethod.POST)
	public String setEdit(Model model, @PathVariable Long id, @ModelAttribute User user) {
		user.setId(id);
		userService.addUser(user);
		return "redirect:/user/details/" + id;
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(Model model) {
		model.addAttribute("user", new User());
		return "signup";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(@Validated User user, BindingResult result, Model model) {
		signUpFormValidator.validate(user, result);
		if (result.hasErrors()) {
			return "signup";
		}
		user.setRole(roleService.getRoles()[0]);
		userService.addUser(user);
		securityService.autoLogin(user.getEmail(), user.getPasswordConfirm());
		return "redirect:home";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		return "login";
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String searchProduct(Pageable pageable, Model model) {
		User activeUser = getActiveUser();
		model.addAttribute("money", activeUser.getPocket());
		return "search";
	}

	private User getActiveUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User activeUser = userService.getUserByEmail(email);
		return activeUser;
	}

	@RequestMapping(value = { "/home" }, method = RequestMethod.GET)
	public String home(Model model) {
		User activeUser = getActiveUser();
		model.addAttribute("articlesList", activeUser.getArticles());
		model.addAttribute("money", activeUser.getPocket());
		return "home";
	}

	// Prueba para eliminar multiples ususarios

	@RequestMapping(value = "/user/delete", method = RequestMethod.POST)
	public String deleteMultipleUsers(@RequestParam(required = false) List<Long> listID) {
		List<Long> aux = new ArrayList<Long>();
		aux = listID;
		for (int i = 0; i < aux.size(); i++) {

			userService.deleteUser(aux.get(i));

		}
		return "redirect:/user/list";
	}
	@RequestMapping("/article/yourProducts")
	public String productosComprados(Pageable pageable,Model model) {
		User activeUser = getActiveUser();
		model.addAttribute("articlesList", activeUser.getArticles());
		model.addAttribute("money", activeUser.getPocket());
		return "/article/yourProducts";
	}
	
	
}