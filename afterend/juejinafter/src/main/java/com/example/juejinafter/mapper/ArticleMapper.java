package com.example.juejinafter.mapper;



import com.example.juejinafter.pojo.Article;

import java.util.List;

public interface ArticleMapper {
    List<Article> listArticles(int page, int size, int category);

    Article findArticleById(String id);

    List<Article> listRelate(String id, int page, int size);

    List<Article> listArticlesLast(int page, int size, int category);

    List<Article> listArticlesFire(int page, int size, int category);
}
