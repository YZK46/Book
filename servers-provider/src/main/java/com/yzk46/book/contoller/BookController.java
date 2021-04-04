package com.yzk46.book.contoller;

import com.alibaba.fastjson.JSONObject;
import com.yzk46.book.entities.Book;
import com.yzk46.book.entities.CommonResult;
import com.yzk46.book.service.BookService;
import com.yzk46.book.util.RedisUtil;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: book
 * @description: 书籍管理
 * @author: yzk46
 * @create: 2021-02-23 22:41
 **/
@RestController
public class BookController {
    @Resource
    private RedisUtil redisUtil;

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

    @GetMapping("/book/redis")
    public String testRedis(@Value("key") String key){
        boolean result =redisUtil.setString(key,"testValue");
        if(result){
            return "success";
        }else return  "fail";
    }

    @GetMapping("/book")
    public CommonResult getBookByTag(@RequestParam("tagId") Long tagId){
        List<Book> bookList = bookService.getBookByTag(tagId);
        if(!CollectionUtils.isEmpty(bookList)){
            return new CommonResult(200,"获取数据成功",bookList);
        }else {
            return new CommonResult(400,"获取数据为空",null);
        }
    }

    @GetMapping("/book/init")
    public CommonResult<List<Book>> bookInit(){
        CommonResult<List<Book>> commonResult = new CommonResult(200,"success");
        File file = new File("C:/Users/YZK46/Desktop/毕设/data/1-2.txt");
        String line = "";
        List<Book> bookList = new ArrayList<>();
        Book book = new Book();
        StringBuffer content = new StringBuffer();
        int i = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            while ((line = bufferedReader.readLine()) != null){

                if ( i == 0){
                    book.setTitle(line);
                }
                if ( i == 1 ){
                    book.setAuthor(line);
                }
                if(i == 2){
                    book.setCover(line);
                }
                if(i == 3){
                    book.setPress(line);
                }
                if(i == 4){
                    book.setDate(line);
                }
                if(i == 5){
                    if(!"".equals(line)){
                        content.append(line+"\n");
                        continue;
                    } else {
                        book.setRemark(content.toString());
                        content.setLength(0);
                        Book book1 = new Book();
                        book1.setRemark(book.getRemark());
                        book1.setTitle(book.getTitle());
                        book1.setAuthor(book.getAuthor());
                        book1.setPress(book.getPress());
                        book1.setCover(book.getCover());
                        book1.setDate(book.getDate());
                        bookList.add(book1);
                        i = -1;
                    }
                }
                i++;
            }
            for (int j = 0; j < bookList.size() ; j++) {
                Book book2 = new Book();
                book2 = bookList.get(j);
                if(book2 != null){
                    bookService.updateRemark(book2);
                }
            }
            commonResult.setResult(bookList);
            return commonResult;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return commonResult;
        }
    }

}
