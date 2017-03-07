package cn.songm.acc.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-acc.xml" })
public class UserRedisTest {

    @Autowired
    private UserRedis userRedis;

    @Test
    public void testRegister() {
        userRedis.set("name", "zhangsong");
    }
    
    @Test
    public void testGet() {
        int num = 30;
        for (int i = 0; i < num; i++) {
            System.out.println(i + " ===========" + userRedis.get("name"));
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
