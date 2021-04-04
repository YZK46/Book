package com.yzk46.book.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @program: book
 * @description: 搜索建议类
 * @author: yzk46
 * @create: 2021-03-25 15:18
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recommend implements Serializable {
    private static final long serialVersionUID = 849811592058106827L;
    private String value;
}
