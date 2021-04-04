package com.yzk46.book.controller;

import com.yzk46.book.entities.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @program: book
 * @description: 搜索接口
 * @author: yzk46
 * @create: 2021-03-24 22:43
 **/
@RestController
@CrossOrigin
public class SearchController {

    @Autowired
    RestTemplate restTemplate;

    @Value("${service-url.searchUrl}")
    String searchUrl;

    @Value("${service-url.searchRecommendUrl}")
    String searchRecommendUrl;

    @GetMapping("/api/search")
    public CommonResult searchBook(@RequestParam("content") String content){
        return restTemplate.getForObject(searchUrl+"?content="+content,CommonResult.class);
    }

    @GetMapping("/api/search/recommend")
    public CommonResult searchRecommend(@RequestParam("input") String input){
        return  restTemplate.getForObject(searchRecommendUrl+"?input="+input,CommonResult.class);
    }
}
