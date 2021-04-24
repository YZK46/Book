package com.yzk46.book.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @program: book
 * @description: 自定义webconfig
 * @author: yzk46
 * @create: 2021-03-15 11:32
 **/
@SpringBootConfiguration
public class MyWebConfigurer implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/user/file/**").addResourceLocations("file:" + "C:/Users/YZK46/Desktop/文件夹/毕设/img/");
    }
}
