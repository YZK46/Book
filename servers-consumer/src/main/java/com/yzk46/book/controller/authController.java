package com.yzk46.book.controller;

import com.yzk46.book.entities.Book;
import com.yzk46.book.entities.CommonResult;
import com.yzk46.book.entities.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @program: book
 * @description: 登录验证
 * @author: yzk46
 * @create: 2021-03-07 11:32
 **/
@CrossOrigin
@RestController
public class authController {

    @Value("${service-url.loginUrl}")
    private String loginUrl;

    @Value("${service-url.registerUrl}")
    private String registerUrl;

    @Resource
    private RestTemplate restTemplate;

    @PostMapping("/auth/login")
    public CommonResult<User> login(@RequestBody User user){
        return restTemplate.postForObject(loginUrl,user,CommonResult.class);
    }


    @PostMapping("/auth/register")
    public CommonResult<Book> register(@RequestBody User user){
        return restTemplate.postForObject(registerUrl,user,CommonResult.class);
    }
}
