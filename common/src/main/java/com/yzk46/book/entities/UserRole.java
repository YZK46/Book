package com.yzk46.book.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @program: book
 * @description: 用户权限关联类
 * @author: yzk46
 * @create: 2021-04-10 16:56
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRole implements Serializable {
    private static final long serialVersionUID = 8035862932216987701L;
    private Integer id;
    private Integer userId;
    private Integer roleId;
}
