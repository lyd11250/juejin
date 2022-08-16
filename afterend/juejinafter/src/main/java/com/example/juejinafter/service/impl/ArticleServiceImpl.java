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
    public List<Article> listArticles(int category, int page, int size, int sort) {
        return articleMapper.listArticles();
    }

    @Override
    public Article findArticleById(String id) {
        return articleMapper.findArticleById(id);
    }

    @Override
    public List<Article> listRelate(String id, int page, int size) {
        return articleMapper.listRelate(id,page,size);
    }
}
