package songm.account.service;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import songm.account.entity.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application.xml"})
public class UserServiceTest {

    @Resource(name = "userService")
    private UserService userService;
    
    @Test
    public void testRegister() {
        User u = null;
        try {
            u = userService.register("zhangsong2", "123456", "送子2");
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        if (u == null) {
            Assert.fail();
            return;
        }
        User uo = userService.getUserById(u.getUserId());
        Assert.assertEquals(u, uo);
    }
}
