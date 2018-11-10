package com.example.demo.es;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;


@SpringBootApplication
@ComponentScan(basePackages={"com.example.demo"})
@EnableElasticsearchRepositories(basePackages = {"com.example.demo.es.dao"})
@EnableEurekaClient
public class ApiESApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiESApplication.class, args);
	}


}
