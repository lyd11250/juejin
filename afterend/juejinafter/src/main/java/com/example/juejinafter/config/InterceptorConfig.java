package com.example.juejinafter.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.example.juejinafter.interceptor.CorsInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ConditionalOnClass(value = {PaginationInterceptor.class})
public class InterceptorConfig implements WebMvcConfigurer {

    @Bean
    CorsInterceptor corsInterceptor(){
        return new CorsInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /**
         * 拦截全部路径，允许跨域
         */
        registry.addInterceptor(corsInterceptor()).addPathPatterns("/**");
        WebMvcConfigurer.super.addInterceptors(registry);
    }
    @Bean
      public PaginationInterceptor paginationInterceptor() {
                 PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
                 return paginationInterceptor;
             }
}
