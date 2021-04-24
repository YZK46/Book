package com.yzk46.book.constant;

/**
 * @program: book
 * @description: 接口报文返回码定义
 * @author: yzk46
 * @create: 2021-04-04 17:06
 **/
public interface ResponseCons {
    //接口调用成功
    public static final Integer SUCCESS = 200;
    //接口调用失败
    public static final Integer FAIL = 400;

    public static final String QUERY_FAIL = "查询失败";

    public static final String QUERY_SUCCESS = "查询成功";

    public static final String ADD_SUCCESS = "添加成功";

    public static final String ADD_FAIL = "添加失败";
}
