package com.example.juejinafter.pojo;

import lombok.Data;

@Data
public class List {
    private Integer id;
    private String title;
    private String author;
    private String date;
    private String abstractStr;
    private String pic;
    private Integer click;
    private Integer like;
    private Integer comment;

}
