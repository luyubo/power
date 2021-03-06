package com.power.article;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import util.IdWorker;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.power.article.dao")
public class ArticleApplication {
    public static void main(String[] args) {
        SpringApplication.run(ArticleApplication.class,args);
    }

    @Bean
    public IdWorker createIdWork(){
        return new IdWorker(1,1);
    }
}
