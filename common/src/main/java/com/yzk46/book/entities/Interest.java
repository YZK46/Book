package com.yzk46.book.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: book
 * @description: 兴趣实体类
 * @author: yzk46
 * @create: 2021-04-15 22:03
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Interest {
    private Integer id;
    private Integer userId;
    private Integer tag1;
    private Integer tag2;
    private Integer tag3;
    private Integer tag4;
    private Integer tag5;
}
