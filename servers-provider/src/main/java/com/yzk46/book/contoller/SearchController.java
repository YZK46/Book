package com.yzk46.book.contoller;

import com.alibaba.fastjson.JSONObject;
import com.yzk46.book.constant.ResponseCons;
import com.yzk46.book.entities.*;
import com.yzk46.book.service.BookService;
import com.yzk46.book.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: book
 * @description: 搜索接口
 * @author: yzk46
 * @create: 2021-03-24 21:39
 **/
@RestController
public class SearchController {

    @Autowired
    TagService tagService;

    @Autowired
    BookService bookService;

    @Autowired
    RecordController recordController;

    @GetMapping("/search")
    public CommonResult<List<Book>> searchBook(@RequestParam("content") String searchContent){
        CommonResult<List<Book>> commonResult = new CommonResult<>(ResponseCons.FAIL,"查询失败",null);
        List<Book> searchResult = new ArrayList<>();
        if(!StringUtils.isEmpty(searchContent)){
            List<Tag> tagList = tagService.find();
            if(!CollectionUtils.isEmpty(tagList)){
                tagList.forEach( item -> {
                    if(searchContent.equals(item.getTagName())){
                        int tagId = item.getTagId();
                        List<Book> bookList = bookService.getBookByTag((long) tagId);
                        searchResult.addAll(bookList);
                    }
                });
            }
            List<Book> bookList = bookService.getBookByTitle(searchContent);
            if(!CollectionUtils.isEmpty(bookList)){
                searchResult.addAll(bookList);
                commonResult.setResultCode(ResponseCons.SUCCESS);
                commonResult.setResultMessage("查询成功");
                commonResult.setResult(searchResult);
            }
        }
        return commonResult;
    }

    @GetMapping("/search/recommend")
    public CommonResult<List<Recommend>> searchRecommend(@RequestParam("input") String input){
        CommonResult<List<Recommend>> commonResult = new CommonResult<>(400,"查询失败");
        if(!StringUtils.isEmpty(input)){
            List<Recommend> recommend = bookService.getBookForSearch(input);
            if(!CollectionUtils.isEmpty(recommend)){
                commonResult.setResultCode(200);
                commonResult.setResultMessage("查询成功");
                commonResult.setResult(recommend);
            }
        }
        return commonResult;
    }

}
