package com.example.juejinafter.service.impl;

import com.example.juejinafter.mapper.ArticleMapper;
import com.example.juejinafter.pojo.Article;
import com.example.juejinafter.service.ArticleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    ArticleMapper articleMapper;


    @Override
    public List<Article> listArticles() {
        return null;
    }
}
