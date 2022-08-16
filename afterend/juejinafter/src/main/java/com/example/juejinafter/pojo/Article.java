package com.example.juejinafter.pojo;

import lombok.Data;

@Data
public class Article {
    private Integer id;
    private String title;
    private String author;
    private String authorHead;
    private String date;
    private String content;
    private  Integer click;
    private  Integer like;
    private  Integer comment;
}
