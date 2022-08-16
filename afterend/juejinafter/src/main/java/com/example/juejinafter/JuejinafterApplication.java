package com.example.juejinafter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.example.juejinafter.mapper")
@EnableTransactionManagement
public class JuejinafterApplication {

    public static void main(String[] args) {
        SpringApplication.run(JuejinafterApplication.class, args);
    }

}
