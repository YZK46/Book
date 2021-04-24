package com.yzk46.book.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @program: book
 * @description: 历史记录实体类
 * @author: yzk46
 * @create: 2021-04-14 21:29
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class History {
    private Integer id;
    private Integer userId;
    private Integer bookId;
    private Date date;
}
