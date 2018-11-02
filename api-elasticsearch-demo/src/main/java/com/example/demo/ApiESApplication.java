package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@ComponentScan(basePackages={"com.example.demo"})
@EnableElasticsearchRepositories(basePackages = {"com.example.demo.dao"})
@EnableEurekaClient
@RestController
public class ApiESApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiESApplication.class, args);
	}


}
