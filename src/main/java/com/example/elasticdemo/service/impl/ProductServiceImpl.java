package com.example.elasticdemo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.elasticdemo.entity.Product;
import com.example.elasticdemo.enums.Category;
import com.example.elasticdemo.repository.ProductRepository;
import com.example.elasticdemo.service.ProductService;

public class ProductServiceImpl implements ProductService {
	   
	@Autowired
    private ProductRepository productRepository;

	@Override
	public Product save(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Iterable<Product> findAll() {
		return productRepository.findAll();
	}

	@Override
	public Page<Product> findByProductName(String name, Pageable pageable) {
		return productRepository.findByProductName(name, pageable);
	}

	@Override
	public Page<Product> findByFilteredTagQuery(String tag, Pageable pageable) {
		return productRepository.findByFilteredTagQuery(Category.valueOf(tag), pageable);
	}

	@Override
	public long count() {
		return productRepository.count();
	}

}
