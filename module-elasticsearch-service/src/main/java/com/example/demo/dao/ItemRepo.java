package com.example.demo.dao;

import com.example.demo.model.Item;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ItemRepo extends ElasticsearchRepository<Item,String> {

}
