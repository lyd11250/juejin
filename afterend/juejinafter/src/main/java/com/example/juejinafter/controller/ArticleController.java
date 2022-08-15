package com.example.juejinafter.controller;


import com.example.juejinafter.service.ArticleService;
import com.example.juejinafter.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/pub/article")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    /**
     * 数据库获取文章列表 (图片,标题，简介，作者，标签，时间)
     * @param id
     * @return
     */
    @RequestMapping("/list")
    public JsonData articleList(@RequestParam(value = "id",required = true) String id){
        return JsonData.buildSuccess(articleService.listArticles());
    }



}
