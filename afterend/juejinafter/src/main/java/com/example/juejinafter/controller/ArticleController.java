package com.example.juejinafter.controller;


import com.example.juejinafter.service.ArticleService;
import com.example.juejinafter.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    /**
     * 数据库获取文章列表
     * @param
     * @return
     */
    @GetMapping("/list")
    public JsonData articleList(@RequestParam(value = "category",required = true) int category,@RequestParam(value = "page",required = true) int page,@RequestParam(value = "size",required = true) int size,@RequestParam(value = "sort",required = true) int sort){
        return JsonData.buildSuccess(articleService.listArticles(category,page,size,sort));
    }

    /**
     * 数据库获取文章详情 (图片,标题，简介，作者，标签，时间)
     * @param id
     * @return
     */
    @GetMapping("/detail")
    public JsonData articleDetail(@RequestParam(value = "id",required = true) String id){
        return JsonData.buildSuccess(articleService.findArticleById(id));
    }



    /**
     * 数据库获取文章相关推荐
     * @param id
     * @return
     */
    @GetMapping("/relate")
    public JsonData articleRelate(@RequestParam(value = "id",required = true) String id,@RequestParam(value = "page",required = true) int page,@RequestParam(value = "size",required = true) int size){
        return JsonData.buildSuccess(articleService.listRelate(id,page,size));
    }



}
