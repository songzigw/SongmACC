package songm.account.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 用户控制器
 * 
 * @author zhangsong
 *
 */
@Controller
@RequestMapping("/")
public class UserController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String toLogin() {

        return "/login";
    }
}
