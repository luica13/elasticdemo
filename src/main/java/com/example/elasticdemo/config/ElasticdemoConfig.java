package com.example.elasticdemo.config;

import java.net.InetAddress;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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

	// @Bean
	// public Client client() {
	// try {
	// final Path tmpDir =Files.createTempDirectory(Paths.get(System.getProperty("java.io.tmpdir")), "elasticsearch_data");
	// logger.debug(tmpDir.toAbsolutePath().toString());
	//
	// // @formatter:off
	//
	// final Settings.Builder elasticsearchSettings = Settings.settingsBuilder().put("http.enabled", "false")
	// .put("path.data", tmpDir.toAbsolutePath().toString())
	// .put("path.home", elasticsearchHome);
	//
	// return new NodeBuilder().local(true).settings(elasticsearchSettings).node().client();
	//
	// // @formatter:on
	// } catch (final IOException ioex) {
	// logger.error("Cannot create temp dir", ioex);
	// throw new RuntimeException();
	// }
	// }

	@Value("${spring.data.elasticsearch.host}")
	private String esHost;

	@Value("${spring.data.elasticsearch.port}")
	private int esPort;

	@Value("${spring.data.elasticsearch.clustername}")
	private String esClusterName;

	@Bean
	public Client client() throws Exception {

		Settings esSettings = Settings.builder().put("cluster.name", esClusterName)/*.put("node.name", "node_1")*/.build();
		TransportClient client = new PreBuiltTransportClient(esSettings);

		    client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(esHost), esPort));
//		 .addTransportAddress(new InetSocketTransportAddress(
//	                InetAddress.getByAddress(new byte[]{127, 0, 0, 1}), 9300));
		return client;
	}

	@Bean
	public ElasticsearchOperations elasticsearchTemplate() throws Exception {
		return new ElasticsearchTemplate(client());
	}
}