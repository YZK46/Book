package com.yzk46.book.controller;

import com.yzk46.book.entities.CommonResult;
import com.yzk46.book.entities.Interest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * @program: book
 * @description: 用户接口
 * @author: yzk46
 * @create: 2021-04-16 11:01
 **/
@RestController
@CrossOrigin
public class UserController {

    @Autowired
    RestTemplate restTemplate;

    @Value("${service-url.getInterestUrl}")
    private String getInterestUrl;

    @Value("${service-url.addInterestUrl}")
    private String addInterestUrl;

    @GetMapping("/api/user/interest")
    public CommonResult getInterest(@RequestParam("id") Integer id){
        return restTemplate.getForObject(getInterestUrl+"?id="+id,CommonResult.class);
    }

    @PostMapping("/api/user/interest/add")
    public CommonResult addInterest(@RequestBody Interest interest){
        return restTemplate.postForObject(addInterestUrl,interest,CommonResult.class);
    }
}
