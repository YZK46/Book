package com.yzk46.book.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: cloud
 * @description: 权限实体类
 * @author: yzk46
 * @create: 2021-03-06 10:20
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Permission {
    private  int id;
    private String name;
    private String url;
    private String code;
}
