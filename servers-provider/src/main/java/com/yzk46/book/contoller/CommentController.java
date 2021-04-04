package com.yzk46.book.contoller;

import com.yzk46.book.cache.RedisCache;
import com.yzk46.book.cache.RedisCacheManager;
import com.yzk46.book.entities.Comment;
import com.yzk46.book.entities.CommonResult;
import com.yzk46.book.entities.Like;
import com.yzk46.book.service.CommentService;
import com.yzk46.book.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @program: book
 * @description: 评论控制器
 * @author: yzk46
 * @create: 2021-03-28 17:10
 **/
@RestController
public class CommentController {

    @Autowired
    CommentService commentService;

    @Resource
    RedisUtil redisUtil;


    @PostMapping("/comment/add")
    public CommonResult addComment(@RequestBody Comment comment){
        CommonResult commonResult = new CommonResult(400,"添加失败",null);
        if(comment != null){
            Date date = new Date();
            comment.setDate(date);
            int result = commentService.setComment(comment);
            if(result != 0){
                commonResult.setResultMessage("添加成功");
                commonResult.setResultCode(200);
            }
        }
        return commonResult;
    }

    @GetMapping("/comment/get/{id}")
    public CommonResult<List<Comment>> getComment(@PathVariable("id") Integer bId){
        CommonResult<List<Comment>> commonResult = new CommonResult(400,"查询失败",null);
        if(bId !=0){
            List<Comment> resultList = commentService.getComment(bId);
            if(!CollectionUtils.isEmpty(resultList)){
                commonResult.setResultCode(200);
                commonResult.setResultMessage("查询成功");
                commonResult.setResult(resultList);
            }
        }
        return commonResult;
    }

    @PostMapping("/comment/like")
    public CommonResult addLike(@RequestBody Like like){
        CommonResult commonResult = new CommonResult<>(400,"添加失败",null);
        if(like != null){
            try{
                String redisNum = (String) redisUtil.hget("LIKE_CACHE",like.getComId().toString());
                if(!StringUtils.isEmpty(redisNum))
                {
                    Integer likeNum = Integer.valueOf(redisNum);
                    if(like.getLike()){
                        likeNum++;
                    }else {
                        likeNum--;
                    }
                    redisUtil.hset("LIKE_CACHE",like.getComId().toString(),likeNum.toString());
                    commonResult.setResultMessage("添加成功");
                    commonResult.setResultCode(200);
                } else {
                    redisUtil.hset("LIKE_CACHE",like.getComId().toString(),"1");
                    commonResult.setResultMessage("添加成功");
                    commonResult.setResultCode(200);
                }
            }catch (NullPointerException e){

            }
        }
        return commonResult;
    }

    @GetMapping("test/redis")
    public void testRedis(){
        commentService.updateLike();
    }
}
