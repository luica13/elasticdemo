package com.example.elasticdemo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.example.elasticdemo.entity.Product;
import com.example.elasticdemo.enums.Category;

@Repository
public interface ProductRepository extends ElasticsearchRepository<Product, String> {

	Page<Product> findByName(String name, Pageable pageable);

	@Query("{\"bool\": {\"must\": {\"match_all\": {}}, \"filter\": {\"term\": {\"category\": \"?0\" }}}}")
	Page<Product> findByCategory(Category category, Pageable pageable);
}
