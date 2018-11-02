package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

//@Document(indexName = "users",type = "library")
@Document(indexName = "users",type = "library")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Hello implements Serializable {

    @Id
    private String id;

    private String name;
    //要有空的构造方法

//    public User() {
//    }
//
//    public User(String id, String empname) {
//        this.id = id;
//        this.empname = empname;
//    }
//
//
//    public String getEmpname() {
//        return empname;
//    }
//
//    public void setEmpname(String empname) {
//        this.empname = empname;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
}
