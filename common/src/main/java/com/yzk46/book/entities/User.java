package com.yzk46.book.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @program: cloud
 * @description: 用户类
 * @author: yzk46
 * @create: 2021-03-02 22:41
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = -7923406595238195316L;
    private Long user_id;
    private String name;
    private String password;
    private String salt;
    private List<Role> roles;
    private String avatar;
}
