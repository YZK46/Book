package com.yzk46.book.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @program: cloud
 * @description: 角色实体类
 * @author: yzk46
 * @create: 2021-03-06 10:19
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role implements Serializable {
    private static final long serialVersionUID = 36245760208122369L;
    private int id;
    private String name;
    private List<Permission> permissions;
}
