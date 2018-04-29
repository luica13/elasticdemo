package com.example.elasticdemo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.elasticdemo.entity.Product;

public interface ProductService {

	Product save(Product Product);

	Iterable<Product> findAll();

	Page<Product> findByProductName(String name, Pageable pageable);

	Page<Product> findByFilteredTagQuery(String tag, Pageable pageable);

	long count();

}
