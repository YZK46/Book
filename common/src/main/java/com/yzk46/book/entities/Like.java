package com.yzk46.book.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: book
 * @description: 点赞实体类
 * @author: yzk46
 * @create: 2021-03-29 21:10
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Like {
    private Integer comId;
    private Integer likeNum;//点赞数
    private Boolean like;//true为增加，false为减少
}
