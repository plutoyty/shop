import com.yty.dao.ProductMapper;
import com.yty.dao.UserMapper;
import com.yty.entity.User;
import com.yty.utils.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class Test1 {

    @Autowired
    UserMapper userMapper;
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    ProductMapper productMapper;

    @Test
    public void test1() {
//        System.out.println(userMapper);
//        System.out.println(userMapper.getUser("2631223275@qq.com"));
        User user = new User();
        user.setAccount("123123123");
        user.setEmail("123123123");
        user.setPassword("123123");
        user.setUsername("1231231");
        System.out.println(userMapper.register(user));
    }

    @Test
    public void test2() {
        String emailTest = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
        String phoneTest = "^(13[0-9]|14[0-9]|15[0-9]|16[0-9]|17[0-9]|18[0-9]|19[0-9])\\d{8}$";
        String email = "2631223275@qq.com";
        String phone = "17674678748";
        System.out.println(email.matches(emailTest));
        System.out.println(phone.matches(phoneTest));
    }

    @Test
    public void test3() {
        System.out.println(productMapper.getProduct("1"));
    }

}
