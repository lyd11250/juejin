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

            article.setAuthorHead(ob.getString("pic"));
            article.setContent("今天我们来聊 `React`，`React` 已经风靡前端届很长一段时间了，在这段时间里它发展了一个非常全面而强大的生态系统。大厂喜欢在大型的前端项目中选择 `React`，它的生态功不可没。\n" +
                    "\n" +
                    "今天的文章，我们将从状态管理、样式和动画、路由、代码风格等多个方面来看看 `React` 最新的生态，希望你以后在做技术选型的时候能够有所帮助。\n" +
                    "\n" +
                    "## 创建 React 项目\n" +
                    "\n" +
                    "\n" +
                    "![image.png](https://p1-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/f3af75dd166d4e8abde4aae5e2028879~tplv-k3u1fbpfcp-watermark.image?)\n" +
                    "\n" +
                    "对于大多数 `React` 初学者来说，在刚刚开始学习 `React` 时如何配置一个 `React` 项目往往都会感到迷惑，可以选择的框架有很多。`React` 社区中大多数会给推荐 `Facebook` 的 `create-react-app (CRA)`。它基本上零配置，为你提供开箱即用的简约启动和运行 `React` 应用程序。\n" +
                    "\n" +
                    "![image.png](https://p1-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/03a0461b81e14075bba509d1f72520c0~tplv-k3u1fbpfcp-watermark.image?)\n" +
                    "\n" +
                    "但现在来看，`CRA` 使用的工具过时了 — 从而导致我们的开发体验变慢。`Vite` 是近期最受欢迎的打包库之一，它具有令人难以置信的开发和生产速度，而且也提供了一些模板（例如 `React、React + TypeScript`）可以选择。\n" +
                    "\n" +
                    "\n" +
                    "![image.png](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/4e9916b1a7c74b3abae031d0a05c7a3b~tplv-k3u1fbpfcp-watermark.image?)\n" +
                    "如果你已很经熟悉 `React` 了，你可以选择它最流行的框架之一作为替代：`Next.js` 和 `Gatsby.js`。这两个框架都基于 `React` 建立，因此你应该至少熟悉了 `React` 的基础知识再去使用。这个领域另一个流行的新兴框架是 `Remix`，它在 2022 年绝对值得一试。\n");

            articleMapper.insertList(article);
        }


    }

}
