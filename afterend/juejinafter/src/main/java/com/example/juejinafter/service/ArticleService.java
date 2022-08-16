package com.example.juejinafter.service;


import com.example.juejinafter.pojo.Article;

import java.util.List;

public interface ArticleService {
    List<Article> listArticles(int category, int page, int size, int sort);

    Article findArticleById(String id);

    List<Article> listRelate(String id, int page, int size);
}
