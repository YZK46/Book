package com.yzk46.book.controller;

import com.yzk46.book.entities.Comment;
import com.yzk46.book.entities.CommonResult;
import com.yzk46.book.entities.Like;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @program: book
 * @description: 评论接口
 * @author: yzk46
 * @create: 2021-03-28 22:02
 **/
@RestController
@CrossOrigin
public class CommentController {

    @Autowired
    RestTemplate restTemplate;

    @Value("${service-url.addCommentUrl}")
    private String addUrl;

    @Value("${service-url.getCommentUrl}")
    private String getUrl;

    @Value("${service-url.addLikeUrl}")
    private String likeUrl;

    @PostMapping("/api/comment/add")
    public CommonResult addComment(@RequestBody Comment comment){
        return restTemplate.postForObject(addUrl,comment,CommonResult.class);
    }

    @GetMapping("/api/comment/get/{id}")
    public CommonResult<List<Comment>> getComments(@PathVariable("id") Integer id){
        return restTemplate.getForObject(getUrl+id,CommonResult.class);
    }

    @PostMapping("/api/comment/like")
    public CommonResult addLike(@RequestBody Like like){
        return restTemplate.postForObject(likeUrl,like,CommonResult.class);
    }
}
