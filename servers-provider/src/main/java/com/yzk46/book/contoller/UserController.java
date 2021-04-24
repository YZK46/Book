package com.yzk46.book.contoller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yzk46.book.constant.ResponseCons;
import com.yzk46.book.entities.CommonResult;
import com.yzk46.book.entities.Interest;
import com.yzk46.book.entities.User;
import com.yzk46.book.service.InterestService;
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

    @Autowired
    InterestService interestService;

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

    @GetMapping("/user/interest")
    public CommonResult getInterest(@RequestParam("id") Integer id){
        CommonResult commonResult = new CommonResult();
        if(id > 0){
            Interest interest = interestService.query(id);
            if(interest != null){
                commonResult.setResult(interest);
                commonResult.setResultCode(ResponseCons.SUCCESS);
                commonResult.setResultMessage(ResponseCons.QUERY_SUCCESS);
            } else {
                commonResult.setResultCode(ResponseCons.FAIL);
                commonResult.setResultMessage(ResponseCons.QUERY_FAIL);
                commonResult.setResult(null);
            }
        }
        return commonResult;
    }

    @PostMapping("/user/interest/add")
    public CommonResult addInterest(@RequestBody Interest interest){
        CommonResult commonResult = new CommonResult(ResponseCons.FAIL,ResponseCons.ADD_FAIL,null);
        if(interest != null){
            Interest before = interestService.query(interest.getId());
            if(before == null) {
                interestService.add(interest);
                commonResult.setResultCode(ResponseCons.SUCCESS);
                commonResult.setResultMessage(ResponseCons.ADD_SUCCESS);
            }
        }
        return commonResult;
    }
}
