package com.example.demo.service;

import com.example.demo.model.Item;

import java.util.List;

public interface ItemService {

    <T> boolean createIndex(Class<T> clazz);

    <T> boolean createMapping(Class<T> clazz);

    List<Item> MutilQuery(Item item);


}
