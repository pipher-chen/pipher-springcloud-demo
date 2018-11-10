package com.example.demo.es.controller;


import com.example.demo.common.ApiResult;
import com.example.demo.es.model.Item;
import com.example.demo.es.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 */
@RestController
public class ItemController {

    @Autowired
    ItemService itemService;

    @GetMapping("/mutilSearch")
    public ApiResult mutilSearch(@RequestBody Item item){
        List<Item> items = itemService.MutilQuery(item);
        return ApiResult.success(items);
    }




}
