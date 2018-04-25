package com.example.elasticdemo.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.elasticdemo.entity.Product;
import com.example.elasticdemo.service.ProductService;

public class ProductServiceImpl implements ProductService {

	@Override
	public Product save(Product Product) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Product> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Product> findByProductName(String name, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Product> findByFilteredTagQuery(String tag, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

}
