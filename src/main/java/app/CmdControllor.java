package app;

import entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tool.Cmd;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by zj on 2017-2-1.
 */
@Controller
public class CmdControllor {


    @RequestMapping(value = "/")
    public String CheckAccounts(Model model){
         return "index";
    }
    @RequestMapping(value = "/accounts")
    public String CheckAccounts1(Model model){
        ArrayList<Account> accounts = new ArrayList<Account>();
        System.out.println("11111111111111");
        accounts.add(new Account(1,"123232323","123"));
        accounts.add(new Account(1,"123","321"));
        accounts.add(new Account(1,"321","123"));
        accounts.add(new Account(1,"123","321"));
        accounts.add(new Account(1,"321","123"));
        accounts.add(new Account(1,"123","321"));
        accounts.add(new Account(1,"321","123"));
        accounts.add(new Account(1,"123","321"));
        accounts.add(new Account(1,"321","123"));
        accounts.add(new Account(1,"123","321"));
        accounts.add(new Account(1,"321","123"));
        accounts.add(new Account(1,"123","321"));
        accounts.add(new Account(1,"321","123"));
        accounts.add(new Account(1,"123","321"));
        accounts.add(new Account(1,"321","123"));
        accounts.add(new Account(1,"123","321"));
        accounts.add(new Account(1,"321","123"));
        model.addAttribute("Accounts",accounts);
        return "result :: resultsAccounts";


    }

    @RequestMapping("/unlock/{accountIndex}/{unlockpasswd}/{length}")
    public String Unlock(Model model , @PathVariable String  accountIndex, @PathVariable String unlockpasswd,@PathVariable String length){
        System.out.println("2222222222");
        return "result :: resultsUnlock";

    }

    @RequestMapping("/unlockerror")
    public String Unlockerror(Model model ){
        System.out.println("eeeeeeeee");
        return "result :: resultsUnlock";

    }

}
