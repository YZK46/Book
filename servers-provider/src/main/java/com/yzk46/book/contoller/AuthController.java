package com.yzk46.book.contoller;

import com.yzk46.book.entities.CommonResult;
import com.yzk46.book.entities.Permission;
import com.yzk46.book.entities.Role;
import com.yzk46.book.entities.User;
import com.yzk46.book.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: book
 * @description: 处理认证和授权
 * @author: yzk46
 * @create: 2021-03-07 11:07
 **/
@RestController
public class AuthController {
    @Autowired
    private UserService userService;

    @GetMapping("/shiro/private")
    public String shiroPublic(){
        return "private resource";
    }

    @CrossOrigin
    @PostMapping("/auth/login")
    public CommonResult<User> shiroPrivate(@RequestBody User user){

        CommonResult<User> commonResult = new CommonResult<>();
        //获取主体对象
        Subject subject = SecurityUtils.getSubject();


        if(user != null){
            try {
                UsernamePasswordToken token = new UsernamePasswordToken(user.getName(),user.getPassword());
                subject.login(token);
                User loginInfo = userService.getRoleByName(user.getName());
                List<Role> roles = loginInfo.getRoles();
                roles.forEach(role -> {
                    List<Permission> permList = userService.getPermByname(role.getName());
                    role.setPermissions(permList);
                });
                if(!ObjectUtils.isEmpty(loginInfo)){
                    commonResult.setResultCode(200);
                    commonResult.setResult(loginInfo);
                    commonResult.setResultMessage("登录成功");
                }
            } catch (UnknownAccountException e) {
                e.printStackTrace();
                commonResult.setResultCode(402);
                commonResult.setResult(null);
                commonResult.setResultMessage("账号不存在");
            } catch (IncorrectCredentialsException e) {
                e.printStackTrace();
                commonResult.setResultCode(403);
                commonResult.setResult(null);
                commonResult.setResultMessage("密码错误");
            }
        } else {
            commonResult.setResultMessage("登录失败");
            commonResult.setResultCode(400);
        }
        return commonResult;
    }

    @GetMapping("/auth/Index")
    public String shiroIndex(){
        return "Hello world";
    }

    @CrossOrigin
    @PostMapping("/auth/register")
    public CommonResult register(@RequestBody User user){


        int result = 0;
        if(user != null){
            User user1 = userService.getUserByName(user.getName());
            if(user1 != null){
                return new CommonResult(401,"用户名已存在",null);
            } else {
                result = userService.register(user);
            }
        }
        if(result != 0){
            return new CommonResult(200,"注册成功",null);
        }
        return new CommonResult(402,"注册失败",null);
    }
}
