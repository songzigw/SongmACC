package cn.songm.acc.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.songm.acc.entity.User;
import cn.songm.common.service.ServiceException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-acc.xml" })
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testRegister() {
        User u = null;
        try {
            u = userService.register("zhangsong", "123456", "张松", "sysVcode", "vcode");
        } catch (ServiceException e) {
            return;
        }
        if (u == null) {
            Assert.fail();
            return;
        }
        Assert.assertEquals(u, userService.getUserById(u.getUserId()));
    }
    
    @Test
    public void testGetUserById() {
        Long uId = 21L;
        User u = userService.getUserById(uId);
        if (u != null) {
            Assert.assertEquals(uId, u.getUserId());
        }
    }
}
