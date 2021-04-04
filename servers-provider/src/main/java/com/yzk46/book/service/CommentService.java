package com.yzk46.book.service;

import com.yzk46.book.entities.Comment;

import java.util.List;

public interface CommentService {
    int setComment(Comment comment);

    List<Comment> getComment(Integer bId);

    int updateLike();
}
