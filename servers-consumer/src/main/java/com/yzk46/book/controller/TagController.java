package com.yzk46.book.controller;

import com.yzk46.book.entities.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @program: book
 * @description: 标签查询
 * @author: yzk46
 * @create: 2021-03-21 13:52
 **/
@RestController
@CrossOrigin
public class TagController {

    @Value("${service-url.getTagUrl}")
    private String getTagUrl;

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/tag/get")
    public CommonResult getTag(){
        return restTemplate.getForObject(getTagUrl,CommonResult.class);
    }
}
