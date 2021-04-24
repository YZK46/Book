package com.yzk46.book.service.impl;

import com.yzk46.book.dao.RoleDao;
import com.yzk46.book.entities.UserRole;
import com.yzk46.book.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @program: book
 * @description: 角色权限服务类
 * @author: yzk46
 * @create: 2021-04-10 16:31
 **/
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    RoleDao roleDao;

    @Override
    public void initRole(UserRole ur) {
        roleDao.initRole(ur);
    }
}
