package com.yzk46.book.controller;

import com.yzk46.book.entities.CommonResult;
import com.yzk46.book.entities.Relate;
import com.yzk46.book.entities.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @program: book
 * @description: 用户评分接口
 * @author: yzk46
 * @create: 2021-04-08 16:30
 **/
@RestController
@CrossOrigin
public class RelateController {
    @Resource
    RestTemplate restTemplate;

    @Value("${service-url.setRelateUrl}")
    private String setRelateUrl;

    @Value("${service-url.getRelateUrl}")
    private String getRelateUrl;

    @Value("${service-url.getRecommendUrl}")
    private String getRecommendUrl;

    @Value("${service-url.getRelateNumUrl}")
    private String getByBookUrl;

    @PostMapping("/api/relate/set")
    public CommonResult setRelate(@RequestBody  Relate relate){
        return  restTemplate.postForObject(setRelateUrl,relate,CommonResult.class);
    }

    @PostMapping("/api/relate/get")
    public CommonResult getRelate(@RequestBody Relate relate){
        return restTemplate.postForObject(getRelateUrl,relate,CommonResult.class);
    }
    @PostMapping("/api/relate/getByBook")
    public CommonResult getRelateByBook(@RequestBody Relate relate){
        return restTemplate.postForObject(getByBookUrl,relate,CommonResult.class);
    }

    @PostMapping("/api/relate/guess")
    public CommonResult getRecommend(@RequestBody User user){
        return restTemplate.postForObject(getRecommendUrl,user,CommonResult.class);
    }
}
