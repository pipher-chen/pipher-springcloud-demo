package com.example.demo.es;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiElasticsearchDemoApplicationTests {

	@Value("${spring.data.elasticsearch.cluster-name}")
	private static String clusterName; //最大连接数

	@Value("${spring.data.elasticsearch.cluster-nodes}")
	private static String clusterNodes;

	@Value("${spring.data.elasticsearch.repositories.enabled}")
	private static String enabled;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testEs(){
		System.out.println(clusterName);
		System.out.println(clusterNodes);
		System.out.println(enabled);
	}

}
