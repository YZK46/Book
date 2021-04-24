package com.yzk46.book.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @program: book
 * @description: 书籍记录实体类
 * @author: yzk46
 * @create: 2021-04-04 16:21
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Record implements Serializable {
    private static final long serialVersionUID = 1874050505358240460L;
    private Integer id;
    private Integer searchTimes;
    private Integer browseTimes;
    private Integer bookId;
}
