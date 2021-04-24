package com.yzk46.book.contoller;

import com.yzk46.book.constant.ResponseCons;
import com.yzk46.book.entities.Book;
import com.yzk46.book.entities.CommonResult;
import com.yzk46.book.entities.History;
import com.yzk46.book.service.BookService;
import com.yzk46.book.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: book
 * @description: 历史记录接口提供端
 * @author: yzk46
 * @create: 2021-04-14 21:47
 **/
@RestController
public class HistoryController {

    @Autowired
    HistoryService historyService;

    @Autowired
    BookService bookService;

    @PostMapping("/his/add")
    public CommonResult addHis(@RequestBody History history){
        CommonResult commonResult = new CommonResult(ResponseCons.FAIL,ResponseCons.ADD_FAIL,null);
        if(history != null){
            List<History> histories = historyService.getHis(history.getUserId());
            if(!CollectionUtils.isEmpty(histories)){
                List<Integer> list = histories.stream().filter(history1 -> history1.getBookId().equals(history.getBookId())).map(History::getBookId).collect(Collectors.toList());
                if(CollectionUtils.isEmpty(list)){
                    historyService.addHis(history);
                    commonResult.setResultMessage(ResponseCons.ADD_SUCCESS);
                    commonResult.setResultCode(ResponseCons.SUCCESS);
                }
            }
        }
        return commonResult;
    }

    @GetMapping("/his/get")
    public CommonResult<List<Book>> getHis(@RequestParam("id") Integer uId){
        CommonResult<List<Book>> commonResult = new CommonResult(ResponseCons.FAIL,ResponseCons.QUERY_FAIL,null);
        if(uId != 0){
            List<History> histories = historyService.getHis(uId);
            if(!CollectionUtils.isEmpty(histories)){
                List<Integer> bIdList = histories.stream().map(History::getBookId).collect(Collectors.toList());
                List<Book> books = bookService.getBookByList(bIdList);
                if(!CollectionUtils.isEmpty(books)){
                    commonResult.setResultCode(ResponseCons.SUCCESS);
                    commonResult.setResultMessage(ResponseCons.QUERY_SUCCESS);
                    commonResult.setResult(books);
                }
            }
        }
        return commonResult;
    }
}
