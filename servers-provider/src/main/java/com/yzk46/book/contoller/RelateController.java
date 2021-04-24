package com.yzk46.book.contoller;

import com.alibaba.fastjson.JSON;
import com.yzk46.book.constant.ResponseCons;
import com.yzk46.book.dao.BookDao;
import com.yzk46.book.dao.RelateDao;
import com.yzk46.book.entities.Book;
import com.yzk46.book.entities.CommonResult;
import com.yzk46.book.entities.Relate;
import com.yzk46.book.entities.User;
import com.yzk46.book.recommend.RecMath;
import com.yzk46.book.service.BookService;
import com.yzk46.book.service.RelateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: book
 * @description: 用户评分接口
 * @author: yzk46
 * @create: 2021-04-08 15:41
 **/
@RestController
@Slf4j
public class RelateController {

    @Resource
    RelateService relateService;

    @Resource
    BookService bookService;

    @PostMapping("/relate/set")
    public CommonResult setRelate(@RequestBody Relate relate){
        CommonResult commonResult = new CommonResult(ResponseCons.FAIL,ResponseCons.ADD_FAIL,null);
        if(relate.getUserId() != null && relate.getBookId() != null && relate.getRelating() != null){
            if(relateService.getRelate(relate) != null){
                relateService.updateRelate(relate);
                commonResult.setResultCode(ResponseCons.SUCCESS);
                commonResult.setResultMessage(ResponseCons.ADD_SUCCESS);
            } else {
                int result = relateService.addRelate(relate);
                if(result > 0){
                    commonResult.setResultCode(ResponseCons.SUCCESS);
                    commonResult.setResultMessage(ResponseCons.ADD_SUCCESS);
                }
            }
        }
        return commonResult;
    }

    @PostMapping("/relate/get")
    public CommonResult getRelate(@RequestBody Relate relate){
        CommonResult commonResult = new CommonResult(ResponseCons.FAIL,ResponseCons.QUERY_FAIL,null);
        if(relate != null){
            Relate relateQuery = relateService.getRelate(relate);
            if(relateQuery != null){
                commonResult.setResultCode(ResponseCons.SUCCESS);
                commonResult.setResultMessage(ResponseCons.QUERY_SUCCESS);
                commonResult.setResult(relateQuery);
            }
        }
        return commonResult;
    }

    @PostMapping("/relate/getByBook")
    public CommonResult getRelateByBook(@RequestBody Relate relate){
        CommonResult commonResult = new CommonResult(ResponseCons.FAIL,ResponseCons.QUERY_FAIL,null);
        if(relate!= null){
            List<Relate> relates = relateService.getRelateByBook(relate);
            if(!CollectionUtils.isEmpty(relates)){
                commonResult.setResultCode(ResponseCons.SUCCESS);
                commonResult.setResultMessage(ResponseCons.QUERY_SUCCESS);
                commonResult.setResult(relates.size());
            }
        }
        return commonResult;
    }

    @PostMapping("/relate/guess")
    public CommonResult<Map<String,Object>> getRecommend(@RequestBody User user){
        CommonResult<Map<String,Object>> commonResult = new CommonResult<>(ResponseCons.FAIL, ResponseCons.QUERY_FAIL,null);
        List<Relate> relates = relateService.getAllRelate();
        RecMath recMath = new RecMath();
        if(!CollectionUtils.isEmpty(relates)){
            log.info("查询所有评分成功");
            List<Integer> recommendations =recMath.recommend(Integer.valueOf(String.valueOf(user.getUser_id())), relates);
            if(!CollectionUtils.isEmpty(recommendations)){
                log.info("推荐列表为：{}", JSON.toJSONString(recommendations));
                List<Book> bookList = bookService.getBookByList(recommendations);
                List<Book> interestBooks = bookService.queryByInterest(Integer.valueOf(String.valueOf(user.getUser_id())));
                Map<String,Object> resultMap = new HashMap<>();
                resultMap.put("guess",bookList);
                resultMap.put("interest",interestBooks);
                if(!CollectionUtils.isEmpty(bookList)){
                    commonResult.setResult(resultMap);
                    commonResult.setResultCode(ResponseCons.SUCCESS);
                    commonResult.setResultMessage(ResponseCons.QUERY_SUCCESS);
                }
            }else {
                log.info("推荐结果为空");
            }
        }
        return commonResult;
    }
}
