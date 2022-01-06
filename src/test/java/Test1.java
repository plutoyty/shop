import com.yty.dao.UserMapper;
import com.yty.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class Test1 {

    @Autowired
    UserMapper userMapper;

    @Test
    public void test1() {
//        System.out.println(userMapper);
        System.out.println(userMapper.getUser("2631223275@qq.com"));
        System.out.println(userMapper.getUser("17674678748"));
    }
}
