import com.yzk46.book.service.CommentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @program: book
 * @description: 测试redis
 * @author: yzk46
 * @create: 2021-03-31 21:56
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = testRedisCache.class)
public class testRedisCache {

    @Resource
    CommentService commentService;

    @Test
    public void getHkey(){
        commentService.updateLike();
    }
}
