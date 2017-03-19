package zj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import zj.entity.SpringResult;
import zj.entity.data;
import zj.entity.sendData;
import zj.entity.user;
import zj.service.ServiceInterface.userService;
import static zj.controller.CONSTANT.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;


/**
 * Created by zj on 2017-3-12.
 */
@Controller
public class AccountController {


//    private static final String = ;

    @Autowired
    private userService userservice;



    @RequestMapping("/login.do")
    public String login(@RequestParam String account , @RequestParam String password ,
                        HttpServletRequest httpServletRequest){
        user _user = new user(account,password);
        boolean result = userservice.login(_user);
        if(result) {
            userservice.addSession(httpServletRequest, _user);
            System.out.println("login ok");
            return REDIRECT + TEMPLATE_HOME;
        }
        else
            return TEMPLATE_LOGIN;
    }

    @RequestMapping("/logout")
    public String logoutPage (HttpServletRequest httpServletRequest) {
            userservice.removeSession(httpServletRequest);
        return REDIRECT + TEMPLATE_LOGIN;
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

    @RequestMapping("/sendData.do")
    public String sendData(@RequestParam int index ,Model model){
        ArrayList<data> datas = new ArrayList<>();
        for(int i = 0 ; i < 10 ; i++)
            datas.add(new data(i,String.valueOf(i*100),String.valueOf(i*1000)));
        data a = new data(1,String.valueOf(100),String.valueOf(1000));
        model.addAttribute("DATAS",datas);
        model.addAttribute("data",a);
        System.out.println("send "+index);
        return TEMPLATES_DATAMANAGEMENT_PAGE;
    }
    @RequestMapping("/editData.do")
    public String editData(@ModelAttribute sendData data , Model model){
        ArrayList<data> datas = new ArrayList<>();
        for(int i = 0 ; i < 10 ; i++)
            datas.add(new data(i,String.valueOf(i*100),String.valueOf(i*1000)));
        data a = new data(1,String.valueOf(100),String.valueOf(1000));
        model.addAttribute("DATAS",datas);
        model.addAttribute("data",a);
        System.out.println("edit " + data);
        return TEMPLATES_DATAMANAGEMENT_PAGE;
    }

    @RequestMapping("/createData.do")
    public String createData(@ModelAttribute sendData data , Model model){
        ArrayList<data> datas = new ArrayList<>();
        for(int i = 0 ; i < 10 ; i++)
            datas.add(new data(i,String.valueOf(i*100),String.valueOf(i*1000)));
        data a = new data(1,String.valueOf(100),String.valueOf(1000));
        model.addAttribute("DATAS",datas);
        model.addAttribute("data",a);
        System.out.println("create " + data);
        return TEMPLATES_DATAMANAGEMENT_PAGE;
    }
    @RequestMapping("/deleteData.do")
    public String deleteData(Model model , @RequestParam int index){
        ArrayList<data> datas = new ArrayList<>();
        for(int i = 0 ; i < 10 ; i++)
            datas.add(new data(i,String.valueOf(i*100),String.valueOf(i*1000)));
        data a = new data(1,String.valueOf(100),String.valueOf(1000));
        model.addAttribute("DATAS",datas);
        model.addAttribute("data",a);
        System.out.println("delete " + index);
        return TEMPLATES_DATAMANAGEMENT_PAGE;
    }
//
//    @RequestMapping("/dataManagePage/sendData")
//    public String sendData(Model model){
////        ArrayList<data> datas = new ArrayList<>();
////        for(int i = 0 ; i < 10 ; i++)
////            datas.add(new data(i,String.valueOf(i*100),String.valueOf(i*1000)));
//        data a = new data(1,String.valueOf(100),String.valueOf(1000));
//        model.addAttribute("flg","true");
//        model.addAttribute("data",a);
//        return REDIRECT + "/dataManagePage";
//    }



    @RequestMapping("/test")
    @ResponseBody
    public String t(){
        return "123";
    }
}
