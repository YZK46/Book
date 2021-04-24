package com.yzk46.book.controller;

import com.yzk46.book.entities.CommonResult;
import com.yzk46.book.entities.History;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * @program: book
 * @description: 历史记录接口
 * @author: yzk46
 * @create: 2021-04-14 22:27
 **/
@RestController
@CrossOrigin
public class HistoryController {

    @Autowired
    RestTemplate restTemplate;

    @Value("${service-url.addHisUrl}")
    private String addHisUrl;

    @Value("${service-url.getHisUrl}")
    private String getHisUrl;

    @PostMapping("/api/his/add")
    public CommonResult addHis(@RequestBody  History history){
        return restTemplate.postForObject(addHisUrl,history,CommonResult.class);
    }

    @GetMapping("/api/his/get")
    public CommonResult getHis(@RequestParam("id") Integer id){
        return restTemplate.getForObject(getHisUrl+"?id="+id,CommonResult.class);
    }
}
