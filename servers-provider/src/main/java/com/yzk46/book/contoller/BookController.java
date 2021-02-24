package com.yzk46.book.contoller;

import com.yzk46.book.entities.Book;
import com.yzk46.book.entities.CommonResult;
import com.yzk46.book.service.BookService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @program: book
 * @description: 书籍管理
 * @author: yzk46
 * @create: 2021-02-23 22:41
 **/
@RestController
public class BookController {

    @Resource
    private BookService bookService;

    @Value("${server.port}")
    private String port;

    @PostMapping("/book/create")
    public CommonResult create(@RequestBody Book book){
        int result = bookService.create(book);

        if(result > 0){
            return new CommonResult(200,"插入数据成功，调用端口号为"+port,result);
        }else {
            return new CommonResult(400,"插入数据失败",null);
        }
    }

    @GetMapping("/book/get/{id}")
    public CommonResult getBook(@PathVariable("id") Long id){
        Book book = bookService.getBook(id);

        if(book != null){
            return new CommonResult(200,"获取数据成功，调用端口号为"+port,book);
        }else {
            return new CommonResult(400,"获取不到该数据",null);
        }
    }
}
