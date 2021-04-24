package com.yzk46.book.controller;

import com.yzk46.book.entities.CommonResult;
import com.yzk46.book.entities.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @program: book
 * @description: 记录类接口
 * @author: yzk46
 * @create: 2021-04-05 21:46
 **/
@RestController
@CrossOrigin
public class RecordController {

    @Autowired
    RestTemplate restTemplate;

    @Value("${service-url.recordUrl}")
    private String recordUrl;

    @PostMapping("/api/record/add")
    public CommonResult addRecord(@RequestBody List<Record> recordList){
        return restTemplate.postForObject(recordUrl,recordList,CommonResult.class);
    }
}
