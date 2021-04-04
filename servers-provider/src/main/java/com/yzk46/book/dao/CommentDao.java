package com.yzk46.book.dao;

import com.yzk46.book.entities.Comment;
import com.yzk46.book.entities.Like;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentDao {
    int setComment(Comment comment);

    List<Comment> getComment(Integer bId);

    int updateLike(@Param("updateList") List<Like> updateList);
}
