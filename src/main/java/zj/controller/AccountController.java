package zj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import zj.entity.SpringResult;
import zj.entity.user;
import zj.service.ServiceInterface.userService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * Created by zj on 2017-3-12.
 */
@Controller
public class AccountController {


    @Autowired
    private userService userservice;

    @RequestMapping("/login")
    public String tologin(){
        return "login";
    }

    @RequestMapping("/login.do")
    public String login(@RequestParam String account , @RequestParam String password ,
                        HttpServletRequest httpServletRequest){
        user _user = new user(account,password);
        boolean result = userservice.login(_user);
        if(result) {
            userservice.addSession(httpServletRequest, _user);
            System.out.println("login ok");
            return "redirect:/home";
        }
        else
            return "login";
    }


    @RequestMapping("/register.do")
    @ResponseBody
    public SpringResult register(@RequestParam String account , @RequestParam String password){
        boolean flg = userservice.insertUser(new user(account , password));
        SpringResult result = new SpringResult();
        if(!flg){
            result.setFlg(SpringResult.SUCCESS);
            result.addResult("resultText" , "Welcome " + account +"!\n" + "login in 3s");
            return result;
        }else{
            result.setFlg(SpringResult.FAIL);
            result.addResult("resultText" , account + " has been regietered");
            return result;
        }
    }

    @RequestMapping("/test")
    @ResponseBody
    public String t(){
        return "123";
    }
}
