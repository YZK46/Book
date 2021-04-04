package com.yzk46.book.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: book
 * @description: 评论实体类
 * @author: yzk46
 * @create: 2021-03-28 16:48
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment implements Serializable {
    private static final long serialVersionUID = -7337599009500600681L;
    private Integer id;
    private Integer userId;
    private String userAvatar;
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm")
    private Date date;
    private Integer bookId;
    private Integer cLike;
    private String content;
    private Boolean isLike;
}
