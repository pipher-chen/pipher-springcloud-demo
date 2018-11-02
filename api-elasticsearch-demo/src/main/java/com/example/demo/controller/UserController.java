package com.example.demo.controller;


import com.example.demo.dao.BookRepo;
import com.example.demo.dao.HelloRepository;
import com.example.demo.dao.UserRepository;
import com.example.demo.model.Book;
import com.example.demo.model.Hello;
import com.example.demo.model.User;
import com.google.common.collect.Lists;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

//import com.example.demo.dao.UserRepository;
//import com.example.demo.dao.HelloRepository;

/**
 * Controller
 *
 * @author suddev
 * @create 2018-03-31 下午1:10
 **/
@RestController
public class UserController {

    @Resource
    private BookRepo bookRepo;

    @Resource
    private HelloRepository helloRepository;

    @Resource
    private UserRepository userRepository;

    @GetMapping("/hello/get/{id}")
    public Hello getBook(@PathVariable String id) {
        return helloRepository.findById(id).get();
    }

    @GetMapping("/user/get/{id}")
    public User getUser(@PathVariable String id) {
        return userRepository.findById(id).get();
    }

//    @PostMapping("/book/put")
//    public Book putBook(@RequestBody Book book) {
//        if (book != null) {
//            return bookRepo.save(book);
//        }
////        userRepository.findByName("1");
//        return new Book();
//    }
//
//    @GetMapping("/book/all")
//    public List<Book> getAll() {
//        return Lists.newArrayList(bookRepo.findAll());
//    }
}
