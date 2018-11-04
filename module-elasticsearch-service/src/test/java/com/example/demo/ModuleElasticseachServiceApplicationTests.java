package com.example.demo;

import com.example.demo.model.Item;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ModuleElasticseachServiceApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;

	@Test
	public void testCreate(){
		// 创建索引，会根据Item类的@Document注解信息来创建
		elasticsearchTemplate.createIndex(Item.class);
		// 配置映射，会根据Item类中的id、Field等字段来自动完成映射
		elasticsearchTemplate.putMapping(Item.class);
	}

}
