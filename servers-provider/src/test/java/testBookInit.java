import com.alibaba.fastjson.JSONObject;
import com.yzk46.book.contoller.TagController;
import com.yzk46.book.entities.Book;
import com.yzk46.book.entities.CommonResult;
import io.micrometer.core.instrument.util.StringEscapeUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: book
 * @description: 测试添加书籍方法
 * @author: yzk46
 * @create: 2021-03-20 11:17
 **/
@SpringBootTest
public class testBookInit  {


    @Test
    public void test(){
        CommonResult<List<Book>> commonResult = new CommonResult(200,"success");
        File file = new File("C:/Users/YZK46/Desktop/毕设/data/1-2.txt");
        String line = "";
        List<Book> bookList = new ArrayList<>();
        Book book = new Book();
        StringBuilder content = new StringBuilder();
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
                        content.append(line).append("\n");
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
//            for (int j = 0; j < bookList.size() ; j++) {
//                Book book2 = new Book();
//                book2 = bookList.get(j);
//                if(book2 != null){
//                    bookService.create(book2);
//                }
//            }
            commonResult.setResult(bookList);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
    }
}
