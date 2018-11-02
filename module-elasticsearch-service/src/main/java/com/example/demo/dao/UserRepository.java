package com.example.demo.dao;

import com.example.demo.model.Book;
import com.example.demo.model.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface UserRepository  extends ElasticsearchRepository<User, String> {

//    User findByName(String id);
}
