package com.yzk46.book.service.impl;

import com.yzk46.book.dao.UserDao;
import com.yzk46.book.entities.Permission;
import com.yzk46.book.entities.User;
import com.yzk46.book.service.UserService;
import com.yzk46.book.util.RandomCharUtil;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: cloud
 * @description: 用户服务接口实现
 * @author: yzk46
 * @create: 2021-03-03 15:18
 **/
@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public Integer  register(User user) {
        //1.生成随机盐
        String salt = RandomCharUtil.create(8);
        //2.将随机盐保存到数据中
        user.setSalt(salt);
        //3.明文密码进行md5 + salt + hash散列
        Md5Hash md5Hash = new Md5Hash(user.getPassword(),salt,1024);
        user.setPassword(md5Hash.toHex());
        return userDao.save(user);
    }

    @Override
    public User getUserByName(String userName) {
        return userDao.getUserByName(userName);
    }

    @Override
    public User getRoleByName(String name) {
        return userDao.getRoleByName(name);
    }

    @Override
    public List<Permission> getPermByname(String name) {
        return userDao.getPermByName(name);
    }

    @Override
    public Integer updateByName(User user) {
        return userDao.updateByName(user);
    }
}
