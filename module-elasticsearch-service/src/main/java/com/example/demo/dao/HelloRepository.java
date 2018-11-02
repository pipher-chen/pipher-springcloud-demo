package com.example.demo.dao;

import com.example.demo.model.Hello;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface HelloRepository extends ElasticsearchRepository<Hello, String> {

    Hello findByName(String id);
}
