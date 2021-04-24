package com.yzk46.book.service.impl;

import com.alibaba.fastjson.JSON;
import com.yzk46.book.dao.CommentDao;
import com.yzk46.book.entities.Comment;
import com.yzk46.book.entities.Like;
import com.yzk46.book.service.CommentService;
import com.yzk46.book.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @program: book
 * @description: 评论服务类
 * @author: yzk46
 * @create: 2021-03-28 17:07
 **/
@Slf4j
@Service("CommentService")
public class CommnetServiceImpl implements CommentService {

    @Resource
    CommentDao commentDao;

    @Resource
    RedisUtil redisUtil;

    @Override
    public int setComment(Comment comment) {
        return commentDao.setComment(comment);
    }

    @Override
    public List<Comment> getComment(Integer bId) {
        return commentDao.getComment(bId);
    }

    @Override
    public int updateLike() {
        Map<Object,Object> cache = redisUtil.getHKeyAndValue("LIKE_CACHE");
        List<Like> updateList = new ArrayList<>();
        if(!CollectionUtils.isEmpty(cache)){
            for(Map.Entry<Object,Object> entry: cache.entrySet()){
                Like like = new Like();
                like.setComId(Integer.valueOf(entry.getKey().toString()));
                if(StringUtils.isEmpty(entry.getValue().toString())){
                    like.setLikeNum(0);
                }else {
                    like.setLikeNum(Integer.valueOf(entry.getValue().toString()));
                }
                updateList.add(like);
            }
        }
        if(log.isInfoEnabled()){
            log.info("点赞更新的数据为:{}", JSON.toJSONString(updateList));
        }
        if(!CollectionUtils.isEmpty(updateList)){
            commentDao.updateLike(updateList);
        }
        return 0;
    }
}
