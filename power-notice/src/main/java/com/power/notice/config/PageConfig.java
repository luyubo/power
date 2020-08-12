package com.power.notice.config;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PageConfig {
    @Bean
    public PaginationInterceptor createPaginationInterceptor() {
        return new PaginationInterceptor();
    }
}
