package com.yzk46.book.controller;

import com.yzk46.book.entities.Book;
import com.yzk46.book.entities.CommonResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @program: book
 * @description: 书籍api接口
 * @author: yzk46
 * @create: 2021-02-25 11:28
 **/
@CrossOrigin
@RestController
public class BookController {

    @Value("${service-url.createUrl}")
    private String createUrl;

    @Value("${service-url.getBookUrl}")
    private String getBookUrl;

    @Value("${service-url.getByTagUrl}")
    private String getByTagUrl;

    @Value("${service-url.getRankUrl}")
    private String getRankUrl;

    @Resource
    private RestTemplate restTemplate;

    @PostMapping("/api/create")
    public CommonResult<Book> create(@RequestBody Book book){
        return restTemplate.postForObject(createUrl,book,CommonResult.class);
    }

    @GetMapping("/api/get/{id}")
    public CommonResult<Book> get(@PathVariable("id") int id){
        return restTemplate.getForObject(getBookUrl+id,CommonResult.class);
    }

    @GetMapping("/api/book")
    public CommonResult getBookById(@RequestParam("tagId") Long tagId){
        return restTemplate.getForObject(getByTagUrl+"?tagId="+tagId,CommonResult.class);
    }

    @GetMapping("/api/book/getRank")
    public CommonResult getRank(){
        return restTemplate.getForObject(getRankUrl,CommonResult.class);
    }
}
