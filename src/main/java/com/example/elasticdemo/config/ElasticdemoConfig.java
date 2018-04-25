package com.example.elasticdemo.config;

import org.elasticsearch.node.NodeBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.example.elasticdemo.repository")
@ComponentScan(basePackages = { "com.example.elasticdemo" })
public class ElasticdemoConfig {

	private static Logger logger = LoggerFactory.getLogger(ElasticdemoConfig.class);

	@Bean
	public ElasticsearchOperations elasticsearchTemplate() {
		return new ElasticsearchTemplate(nodeBuilder().local(true).node().client());
	}

	@Bean
	public NodeBuilder nodeBuilder() {
		return new NodeBuilder();
	}

}
