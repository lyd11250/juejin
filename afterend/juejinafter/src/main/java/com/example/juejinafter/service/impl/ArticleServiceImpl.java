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
        /**
         * sort 0默认 1最新 2最热
         *size 每页展示的个数
         * page 页码
         * category 0综合 1后端 2前端 3Android 4IOS 5人工智能 6开发工具 7代码人生
         *
         */
        page = (page-1)*size;
        if(sort == 0){
            return articleMapper.listArticles(page,size,category);
        }else if(sort ==1){
            return articleMapper.listArticlesLast(page,size,category);
        }else if(sort ==2){
            return articleMapper.listArticlesFire(page,size,category);
        }
        return null;
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
