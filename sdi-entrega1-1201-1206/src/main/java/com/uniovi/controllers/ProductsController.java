package com.uniovi.controllers;

import java.security.Principal;
import java.util.LinkedList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.uniovi.entities.*;
import com.uniovi.services.ProductsService;
import com.uniovi.services.UserService;
import org.springframework.data.domain.*;

@Controller
public class ProductsController {

	@Autowired
	private HttpSession httpSession;
	@Autowired // Inyectar el servicio
	private ProductsService ProductsService;

	@Autowired
	private UserService usersService;

	@RequestMapping("/Product/list")
	public String getList(Model model, Pageable pageable, Principal principal,
			@RequestParam(value = "", required = false) String searchText) {
		Page<Product> pro = new PageImpl<Product>(new LinkedList<Product>());
		pro=ProductsService.getProducts(pageable);
		model.addAttribute("ProductsList", Product.getDescription());
		model.addAttribute("page", pro);
		return "mark/list";
	}



}
