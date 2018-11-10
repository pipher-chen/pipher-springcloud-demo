package com.example.demo.es.service;

import com.example.demo.es.model.Item;

import java.util.List;

public interface ItemService {

//    <T> boolean createIndex(Class<T> clazz);
//
//    <T> boolean createMapping(Class<T> clazz);

    List<Item> MutilQuery(Item item);


}
