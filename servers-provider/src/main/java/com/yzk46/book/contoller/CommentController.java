package com.yzk46.book.contoller;

import com.yzk46.book.constant.RedisCons;
import com.yzk46.book.constant.ResponseCons;
import com.yzk46.book.entities.Comment;
import com.yzk46.book.entities.CommonResult;
import com.yzk46.book.entities.Like;
import com.yzk46.book.service.CommentService;
import com.yzk46.book.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
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
        CommonResult commonResult = new CommonResult(ResponseCons.FAIL,ResponseCons.ADD_FAIL,null);
        if(comment != null){
            Date date = new Date();
            comment.setDate(date);
            int result = commentService.setComment(comment);
            if(result != 0){
                commonResult.setResultMessage(ResponseCons.ADD_SUCCESS);
                commonResult.setResultCode(ResponseCons.SUCCESS);
            }
        }
        return commonResult;
    }

    @GetMapping("/comment/get/{id}")
    public CommonResult<List<Comment>> getComment(@PathVariable("id") Integer bId){
        CommonResult<List<Comment>> commonResult = new CommonResult(ResponseCons.FAIL,ResponseCons.QUERY_FAIL,null);
        if(bId !=0){
            List<Comment> resultList = commentService.getComment(bId);
            if(!CollectionUtils.isEmpty(resultList)){
                commonResult.setResultCode(ResponseCons.SUCCESS);
                commonResult.setResultMessage(ResponseCons.QUERY_SUCCESS);
                commonResult.setResult(resultList);
            }
        }
        return commonResult;
    }

    @PostMapping("/comment/like")
    public CommonResult addLike(@RequestBody Like like){
        CommonResult commonResult = new CommonResult<>(ResponseCons.FAIL,ResponseCons.ADD_FAIL,null);
        if(like != null){
            try{
                String redisNum = (String) redisUtil.hget(RedisCons.LIKE_KEY,like.getComId().toString());
                if(!StringUtils.isEmpty(redisNum))
                {
                    Integer likeNum = Integer.valueOf(redisNum);
                    if(like.getLike()){
                        likeNum++;
                    }else {
                        likeNum--;
                    }
                    redisUtil.hset(RedisCons.LIKE_KEY,like.getComId().toString(),likeNum.toString());
                    commonResult.setResultMessage(ResponseCons.ADD_SUCCESS);
                    commonResult.setResultCode(ResponseCons.SUCCESS);
                } else {
                    redisUtil.hset(RedisCons.LIKE_KEY,like.getComId().toString(), RedisCons.LIKE_INIT);
                    commonResult.setResultMessage(ResponseCons.ADD_SUCCESS);
                    commonResult.setResultCode(ResponseCons.SUCCESS);
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
