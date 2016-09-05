package songm.account.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 用户控制器服务
 * 
 * @author zhangsong
 *
 */
@Controller
@RequestMapping("/polling")
public class UserController {

    /**
     * 长轮询
     * 
     * @param token
     * @param session
     * @param callback
     * @return
     */
    @RequestMapping(value = "/long", method = RequestMethod.GET)
    public String getToken(String token, String session, String callback) {

        return "/data";
    }
}
