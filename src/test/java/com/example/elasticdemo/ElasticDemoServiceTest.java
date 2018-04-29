package com.example.elasticdemo;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.elasticdemo.config.ElasticdemoConfig;
import com.example.elasticdemo.entity.Product;
import com.example.elasticdemo.enums.Category;
import com.example.elasticdemo.service.ProductService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ElasticdemoConfig.class)
@TestPropertySource({"classpath:application.properties"})
@SpringBootTest(classes = ElasticdemoApplication.class)
public class ElasticDemoServiceTest {

	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;

	@Autowired
	private ProductService productService;

	@Before
	public void before() {
		if(elasticsearchTemplate.indexExists(Product.class)) {
			elasticsearchTemplate.deleteIndex(Product.class);
		}
		elasticsearchTemplate.createIndex(Product.class);

		Product product = new Product();
		product.setName("AAAAA");
		product.setCategories(Arrays.asList(Category.BOOKS));
		product.setPrice(BigDecimal.TEN);
		productService.save(product);

		product = new Product();
		product.setName("BBBBB");
		product.setCategories(Arrays.asList(Category.CLOTHING));
		product.setPrice(BigDecimal.ONE);
		productService.save(product);

		product = new Product();
		product.setName("CCCCC");
		product.setCategories(Arrays.asList(Category.CLOTHING));
		product.setPrice(BigDecimal.ZERO);
		productService.save(product);
	}
	
	@After
	public void after() {
		elasticsearchTemplate.deleteIndex(Product.class);
	}
	
	@Test
	public void getFilteredQuery_whenSearchByProductName() {
		final Page<Product> productByName = productService.findByProductName("BBBBB", PageRequest.of(0, 10));
		assertEquals(1L, productByName.getTotalElements());
	}
	
	@Test
	public void getFilteredQuery_whenSearchByCategory() {
		final Page<Product> productByName = productService.findByFilteredTagQuery("CLOTHING", PageRequest.of(0, 10));
		assertEquals(2L, productByName.getTotalElements());
	}
}
