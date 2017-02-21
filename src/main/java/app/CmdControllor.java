package app;

import entity.Account;
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

/**
 * Created by zj on 2017-2-1.
 */
@Controller
@ComponentScan("tool")
public class CmdControllor {

    @Autowired Cmd mycmd;

    @RequestMapping(value = "/")
    public String Init(Model model){
        return "index";
    }

    @RequestMapping("/createAccount/{createAccountPasswd}")
    public String CreatAccount(Model model , @PathVariable String createAccountPasswd){
        model.addAttribute("TYPE","SUCCESS");
        model.addAttribute("CREATEACCOUNT", "the address is " + mycmd.CreateAccuont(createAccountPasswd));
        return "result :: resultCreateAccount";
    }

    @RequestMapping(value = "/accounts")
    public String GetAccount(Model model){
        mycmd._GetAccounts();
        model.addAttribute("Accounts", mycmd.getAccounts());
        return "result :: resultAccounts";
    }

    @RequestMapping("/unlock/{accountIndex}/{unlockpasswd}/{length}")
    public String Unlock(Model model , @PathVariable String  accountIndex,
                         @PathVariable String unlockpasswd,@PathVariable String length){

        if(!accountIndex.matches("\\d+") || !length.matches("\\d+")){
            model.addAttribute("TYPE","FAIL");
            model.addAttribute("UNLOCK","Account Index  or Length must be number");
            return "result :: resultUnlock";
        }
        String unlock = mycmd.UnlockAccount(Integer.parseInt(accountIndex),unlockpasswd,Integer.parseInt(length));
        if(unlock.equals(Cmd.UNLOCK_FAIL)){
            model.addAttribute("TYPE","FAIL");
            model.addAttribute("UNLOCK",Cmd.UNLOCK_FAIL_RETUEN);
        }
        if(unlock.equals(Cmd.UNLOCK_SUCCESS)){
            model.addAttribute("TYPE","SUCCESS");
            model.addAttribute("UNLOCK","unlock success in "+length+" scoends");
        }
        return "result :: resultUnlock";
    }

    @RequestMapping("/transaction/{sendIndex}/{receiveIndex}/{sendAmount}/{sendData}/{sendIdentification}")
    public String SendTransaction(Model model , @PathVariable String  sendIndex,
                                  @PathVariable String receiveIndex , @PathVariable String sendAmount,
                                  @PathVariable String sendData , @PathVariable String sendIdentification){
        if(!sendIndex.matches("\\d+") || !receiveIndex.matches("\\d+") || !sendAmount.matches("\\d+")){
            model.addAttribute("TYPE","FAIL");
            model.addAttribute("TRANSCATION","Account Index  or Length Identification or must be number");
            return "result :: resultTransaction";
        }
        if(sendIdentification.length() != 8){
            model.addAttribute("TYPE","FAIL");
            model.addAttribute("TRANSCATION","Identification'length must be 8");
            return "result :: resultTransaction";
        }
        try {
            String result = mycmd.SendTransactionByAccount(Integer.parseInt(sendIndex),
                    Integer.parseInt(receiveIndex), Integer.parseInt(sendAmount), sendData, sendIdentification);
            if(result.equals(Cmd.ACCOUNT_LOCKED)){
                model.addAttribute("TYPE","FAIL");
                model.addAttribute("TRANSCATION",Cmd.ACCOUNT_LOCKED);
            }
            else if(result.matches(Cmd.RE_HEX_STRING)){
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
        return "result :: resultTransaction";
    }

    @RequestMapping("/getTransaction/{identification}")
    public String GetTransaction(Model model , @PathVariable String identification){
        if(identification.length() == 8) {
            mycmd.GetTransactionDataByIdentification(identification);
            model.addAttribute("TYPE", "SUCCESS");
            model.addAttribute("GETTRANSACTION", mycmd.getTransactions());
            System.out.println("444444444444");
        }else{
            model.addAttribute("TYPE", "FAIL");
            model.addAttribute("GETTRANSACTION", "Identification's length must be 8");
        }
        return "result :: resultGetTransaction";

    }

    @RequestMapping("/error/{type}")
    public String Error(Model model , @PathVariable String type){
        model.addAttribute("TYPE","FAIL");
        switch(type){
            case "unlock":
                model.addAttribute("UNLOCK","miss token");
                return "result :: resultUnlock";
            case "transaction":
                model.addAttribute("TRANSCATION","miss token");
                return "result :: resultTransaction";
            case "getTransaction":
                model.addAttribute("GETTRANSACTION","miss token");
                return "result :: resultGetTransaction";
            case "createAccount":
                model.addAttribute("CREATEACCOUNT","miss token");
                return "result :: resultCreateAccount";
            default:
                return null;
        }
    }

}
