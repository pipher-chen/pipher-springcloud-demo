package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;


/**
 * Book Entity
 *
 * @author suddev
 * @create 2018-03-31 下午12:59
 **/
@Document(indexName = "books",type = "library")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book implements Serializable {
//    @Id
    private String id;

    private String name;
}
