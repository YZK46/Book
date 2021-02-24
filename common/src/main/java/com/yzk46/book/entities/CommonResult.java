package com.yzk46.book.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: book
 * @description: 通用结果实体类
 * @author: yzk46
 * @create: 2021-02-23 22:06
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    private Integer resultCode;
    private String resultMessage;
    private T result;

    public CommonResult(Integer resultCode, String resultMessage){
        this(resultCode,resultMessage,null);
    }
}
