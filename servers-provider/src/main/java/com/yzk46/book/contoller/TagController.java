package com.yzk46.book.contoller;

import com.alibaba.fastjson.JSONObject;
import com.yzk46.book.entities.Book;
import com.yzk46.book.entities.CommonResult;
import com.yzk46.book.entities.Tag;
import com.yzk46.book.service.BookService;
import com.yzk46.book.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: book
 * @description: 标签控制器
 * @author: yzk46
 * @create: 2021-03-17 22:30
 **/
@RestController
public class TagController {

    @Autowired
    TagService tagService;

    @GetMapping("/tag/get")
   public CommonResult getTag(){
       CommonResult commonResult = new CommonResult(200,"查询成功",null);
       List<String> tagGroup = tagService.findGroup();
       List<Tag> tagList = tagService.find();
       if(!CollectionUtils.isEmpty(tagGroup) && !CollectionUtils.isEmpty(tagList)){
           Map<String,Object> resultMap = new HashMap<>();
           tagGroup.forEach(group -> {
               List<Tag> itemList = new ArrayList<>();
               tagList.forEach(tag -> {
                   if(tag.getTagGroup().equals(group)){
                       itemList.add(tag);
                   }
               });
               resultMap.put(group,itemList);
           });
           commonResult.setResult(resultMap);
       } else {
           commonResult.setResultCode(400);
           commonResult.setResultMessage("查询失败");
       }
       return commonResult;
   }



    
    
}
