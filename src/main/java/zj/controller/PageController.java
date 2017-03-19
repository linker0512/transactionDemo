package zj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import zj.entity.data;
import zj.entity.sendData;

import java.util.ArrayList;

import static zj.controller.CONSTANT.*;
/**
 * Created by zj on 2017-3-16.
 */
@Controller
public class PageController {

    @RequestMapping("/login")
    public String tologin(){
        return TEMPLATE_LOGIN;
    }

    @RequestMapping("/home")
    public String Init(Model model){
        return TEMPLATE_HOME;
    }
    @RequestMapping("/createAccountPage")
    public String CreatAccountpage(Model model){
        return TEMPLATES_ACCOUNT_CREATE_ACCOUNT;
    }

    @RequestMapping("/getAccountsPage")
    public String getAccountsPage(Model model){
        model.addAttribute("TYPE","INIT");
        return TEMPLATES_ACCOUNT_GET_ACCOUNT;
    }

    @RequestMapping("/dataManagePage")
    public String toManagement(Model model){
        ArrayList<data> datas = new ArrayList<>();
        for(int i = 0 ; i < 10 ; i++)
            datas.add(new data(i,String.valueOf(i*100),String.valueOf(i*1000)));
        data a = new data(1,String.valueOf(100),String.valueOf(1000));
        model.addAttribute("DATAS",datas);
        model.addAttribute("data",a);
        return TEMPLATES_DATAMANAGEMENT_PAGE;
    }
    @RequestMapping("/checkDataPage")
    public String checkDataPage(Model model, @RequestParam int index){
        model.addAttribute("index",index);
        model.addAttribute("data",new sendData("1","2","3","4","5","6"));
        return TEMPLATES_DATAMANAGEMENT_CHECKDATA;
    }
    @RequestMapping("/createDataPage")
    public String reatePage(Model model){
        return TEMPLATES_DATAMANAGEMENT_CREATEDATA;
    }
//    @RequestMapping("/dataManagePage/sendData")
//    public String sendDataPage(Model model , @RequestParam int id){
//        return TEMPLATES_DATAMANAGEMENT_SENDDATA;
//    }
//
//    @RequestMapping("/dataManagePage/addData")
//    public String addDataPage(Model model){
//        return TEMPLATES_DATAMANAGEMENT_ADDDATA;
//    }
//
//    @RequestMapping("/dataManagePage/editData")
//    public String editDataPage(Model model , @RequestParam int id){
//        return TEMPLATES_DATAMANAGEMENT_EDITDATA;
//    }
}
