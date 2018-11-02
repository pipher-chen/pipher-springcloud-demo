package com.example.demo.dao;

import com.example.demo.model.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Book Repository
 *
 * @author suddev
 * @create 2018-03-31 下午12:58
 **/
public interface BookRepo extends ElasticsearchRepository<Book,String> {

//    Book findByName(String id);
}
