package com.example.demo.es.dao;

import com.example.demo.es.model.Item;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ItemRepo extends ElasticsearchRepository<Item,String> {

}
