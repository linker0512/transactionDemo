package app;

import entity.Account;
import entity.TransactionData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tool.Cmd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.BlockingDeque;

/**
 * Created by zj on 2017-2-1.
 */
@Controller
@ComponentScan("tool")
public class CmdControllor {

    @Autowired Cmd mycmd;

    @RequestMapping(value = "/")
    public String Init(Model model){
        return "home";
    }

    @RequestMapping("/getAccountsPage")
    public String getAccountsPage(Model model){
        model.addAttribute("TYPE","INIT");
        return "getAccount";
    }


    @RequestMapping("/getAccounts")
    public String getAccounts(Model model){
        model.addAttribute("ACCOUNTS",mycmd.getAccounts());
        model.addAttribute("TYPE","OWN");
        return "getAccount :: result";
    }

    @RequestMapping("/transaction")
    public String b(Model model){
        return "transaction";
    }

    @RequestMapping("/sendtransaction")
    public String transaction(Model model ,
                              @RequestParam String sendIndex , @RequestParam String receiveIndex ,
                              @RequestParam String sendAmount ,@RequestParam String sendIdentification ,
                              @RequestParam String sendData ,  @RequestParam String password_input){
        String unlock = mycmd.UnlockAccount(Integer.parseInt(sendIndex),password_input,20);

        if(unlock.equals(Cmd.UNLOCK_FAIL)){
            model.addAttribute("TYPE","FAIL");
            model.addAttribute("TRANSCATION",Cmd.UNLOCK_FAIL_RETUEN);

        }else{
            try {
                String result = mycmd.SendTransactionByAccount(Integer.parseInt(sendIndex),
                        Integer.parseInt(receiveIndex), Integer.parseInt(sendAmount), sendData, sendIdentification);
                if(result.matches(Cmd.RE_HEX_STRING)){
                    model.addAttribute("TYPE","SUCCESS");
                    model.addAttribute("TRANSCATION",result);
                    System.out.println("333333333333");
                }
                else if(result.equals(Cmd.ACCOUNT_INSUFFICIENT)){
                    model.addAttribute("TYPE","FAIL");
                    model.addAttribute("TRANSCATION",Cmd.ACCOUNT_INSUFFICIENT);
                }
            }catch (NumberFormatException e){
                model.addAttribute("TYPE","FAIL");
                model.addAttribute("TRANSCATION","Amount is 32 bits");
            }
        }
        return "transaction :: transaction_result";
    }
    @RequestMapping("/searchtransaction")
    public String GetTransaction(Model model , @RequestParam String Identification){
        mycmd.GetTransactionDataByIdentification(Identification);
        model.addAttribute("TYPE", "SUCCESS");
        model.addAttribute("GETTRANSACTION",mycmd.getTransactions());
        return "transaction :: search_result";
    }

    @RequestMapping("/createAccount")
    @ResponseBody
    public String CreatAccount1(@RequestParam String password_input){
        return "Address\n  "+mycmd.CreateAccuont(password_input);
    }
    @RequestMapping("/createAccountPage")
    public String CreatAccountpage(){
        return "account1";
    }
}
