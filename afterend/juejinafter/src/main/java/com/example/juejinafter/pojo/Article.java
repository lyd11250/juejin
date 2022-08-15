package com.example.juejinafter.pojo;

import lombok.Data;

@Data
public class Article {
    private Integer id;
    private  Integer bid;
    private String title;
    private String autor;
    private String publishTime;
    private Integer readNumber;

    private String contentText;


    private String imageurl;
    private String articleTag;
    private String categoryName;
}
