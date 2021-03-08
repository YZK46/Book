package com.yzk46.book.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @program: book
 * @description: 配置类
 * @author: yzk46
 * @create: 2021-02-25 11:12
 **/
@Configuration
public class ApplicationContext {
    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {return new RestTemplate();}
}
