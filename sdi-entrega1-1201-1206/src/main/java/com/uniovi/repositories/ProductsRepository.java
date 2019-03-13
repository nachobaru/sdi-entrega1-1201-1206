package com.uniovi.repositories;

import org.springframework.data.repository.CrudRepository;
import com.uniovi.entities.Product;
import com.uniovi.entities.User;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductsRepository extends CrudRepository<Product, Long> {
	Optional<Product> findById(Long id);
	Page<Product> findAll(Pageable pageable);



}