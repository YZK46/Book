package com.yzk46.book.contoller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yzk46.book.entities.CommonResult;
import com.yzk46.book.entities.User;
import com.yzk46.book.service.UserService;
import com.yzk46.book.util.RandomCharUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @program: book
 * @description: 用户数据相关
 * @author: yzk46
 * @create: 2021-03-15 10:35
 **/
@CrossOrigin
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/user/cover")
    public String coversUpload(MultipartFile file,@RequestParam(value = "userName") String userName) throws Exception {
        String folder = "C:/Users/YZK46/Desktop/毕设/img";
        File imageFolder = new File(folder);
        File f = new File(imageFolder, RandomCharUtil.create(6) + file.getOriginalFilename()
                .substring(file.getOriginalFilename().length() - 4));
        if (!f.getParentFile().exists())
            f.getParentFile().mkdirs();
        try {
            file.transferTo(f);
            String imgURL = "http://localhost:8484/user/file/" + f.getName();
            User user = userService.getUserByName(userName);
            user.setAvatar(imgURL);
            Integer result = userService.updateByName(user);
            if(result != null){
                return imgURL;
            } else {
                return "400";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
