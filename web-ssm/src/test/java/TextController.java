import com.heitian.ssm.arch.utils.Identities;
import com.heitian.ssm.model.User;
import com.heitian.ssm.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * Created by xiang on 2017/6/22.
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring-*.xml"})
public class TextController {
    @Autowired
    UserService userService;
    @Test
    public  void testUser(){
        User newUser=new User();
        newUser.setId(Identities.uuid());
        newUser.setLoginName("admin");
        newUser.setUserName("admin");
        String pwd = DigestUtils.md5Hex("123456");
        newUser.setPassword(pwd);
        newUser.setRoleCode("1");
        newUser.setRoleName("管理员");
        newUser.setPhone("18888886666");
        newUser.setSex("男");
        newUser.setCreateTime(new Date());
        int i=userService.insert(newUser);
    }
}
