package com.example.demo.es;

import com.example.demo.es.dao.ItemRepo;
import com.example.demo.es.model.Item;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.WildcardQueryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	private ItemRepo itemRepo;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testEs(){
		System.out.println(clusterName);
		System.out.println(clusterNodes);
		System.out.println(enabled);
	}

	@Test
	public void testQuery() {
		// 词条查询
//        MatchQueryBuilder queryBuilder = QueryBuilders.matchQuery("title", "小米").operator(Operator.AND);
		WildcardQueryBuilder fileType = QueryBuilders.wildcardQuery("brand", "*小*");
		// 执行查询
//        Iterable<Item> items = this.repository.search(queryBuilder);
		Iterable<Item> items = this.itemRepo.search(fileType);
		items.forEach(System.out::println);
	}

}
