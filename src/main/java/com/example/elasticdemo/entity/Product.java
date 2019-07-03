package com.example.elasticdemo.entity;

import static org.springframework.data.elasticsearch.annotations.FieldType.Double;
import static org.springframework.data.elasticsearch.annotations.FieldType.text;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.example.elasticdemo.enums.Category;

@Document(indexName = "market", type = "product")
public class Product {
    @Id
    private String id;
    
    @Field(type = text)	
    private String name;

    @Field(type = Double)	
    private BigDecimal price;
     
    @Field(type = FieldType.Nested)
    private List<Category> categories;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", categories=" + categories + "]";
	}
     
}
