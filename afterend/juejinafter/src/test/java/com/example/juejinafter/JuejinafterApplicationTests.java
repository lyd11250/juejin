package com.example.juejinafter;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.juejinafter.mapper.ArticleMapper;
import com.example.juejinafter.pojo.ArticleList;
import com.example.juejinafter.utils.JsonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
class JuejinafterApplicationTests {
    @Autowired
    private ArticleMapper articleMapper;

    @Test
    void contextLoads() {
        String jsonStr = JsonUtil.readJsonFile("/Users/heerxin/code/article.list.json");
        JSONArray result = JSONObject.parseArray(jsonStr);
        System.out.println(result.toJSONString());
        ArrayList<ArticleList> list = new ArrayList<>();
        for(int i =0 ;i<result.size();i++){
            JSONObject ob = (JSONObject)result.get(i);

            ArticleList article = new ArticleList();
            article.setAbstractStr(ob.getString("abstract"));
            article.setAuthor(ob.getString("author"));
            article.setCategory(ob.getInteger("category"));
            article.setClick(ob.getInteger("click"));
            article.setComment(ob.getInteger("comment"));
            article.setDate(ob.getString("date"));
            article.setLike(ob.getInteger("like"));
            article.setPic(ob.getString("pic"));
            article.setTitle(ob.getString("title"));
            article.setUuid(ob.getString("id"));

            articleMapper.insertList(article);
        }


    }

}
