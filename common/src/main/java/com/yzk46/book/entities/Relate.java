package com.yzk46.book.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @program: book
 * @description: 用户与书籍相关性
 * @author: yzk46
 * @create: 2021-04-08 11:12
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Relate implements Serializable {
    private static final long serialVersionUID = -6359124193466822328L;
    private Integer id;
    private Integer userId;
    private Integer bookId;
    private Integer relating;
}
