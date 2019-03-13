package com.uniovi.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.uniovi.entities.Product;
import com.uniovi.entities.User;
import com.uniovi.repositories.ProductsRepository;

import org.springframework.data.domain.*;
@Service
public class ProductsService {

	@Autowired
	private HttpSession httpSession;

	@Autowired
	private ProductsRepository ProductsRepository;

	public Page<Product> getProducts(Pageable pageable) {
		Page<Product> pro = (Page<Product>) new ArrayList<Product>();
		ProductsRepository.findAll();
		return pro;
	}

	public Product getProduct(Long id) {
		Set<Product> consultedList = (Set<Product>) httpSession.getAttribute("consultedList");
		if (consultedList == null) {
			consultedList = new HashSet<Product>();
		}
		Product markObtained = ProductsRepository.findById(id).get();
		consultedList.add(markObtained);
		httpSession.setAttribute("consultedList", consultedList);
		return markObtained;
	}

	public void addProduct(Product Product) {
		// Si en Id es null le asignamos el ultimo + 1 de la lista
		ProductsRepository.save(Product);
	}

	public void deleteProduct(Long id) {
		ProductsRepository.deleteById(id);
	}

	
}