package com.example.demo.es.controller;


import com.example.demo.dao.BookRepo;
import com.example.demo.model.Book;
import com.google.common.collect.Lists;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Controller
 *
 * @author suddev
 * @create 2018-03-31 下午1:10
 **/
@RestController
public class BookController {

    @Resource
    private BookRepo bookRepo;

//    @Resource
//    private HelloRepository helloRepository;

    @GetMapping("/book/get/{id}")
    public Book getBook(@PathVariable String id) {
        return bookRepo.findById(id).get();
    }

    @PostMapping("/book/put")
    public Book putBook(@RequestBody Book book) {
        if (book != null) {
            return bookRepo.save(book);
        }
//        userRepository.findByName("1");
        return new Book();
    }

    @GetMapping("/book/all")
    public List<Book> getAll() {
        return Lists.newArrayList(bookRepo.findAll());
    }
}
