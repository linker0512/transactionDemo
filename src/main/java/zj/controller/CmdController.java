package zj.controller;

import zj.entity.Account;
import zj.entity.TransactionData;
import zj.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import zj.service.ServiceInterface.userService;
import zj.service.userServiceImpl;
import zj.tool.Cmd;

import javax.management.modelmbean.ModelMBeanOperationInfo;

import static zj.controller.CONSTANT.*;

/**
 * Created by zj on 2017-2-1.
 */
@Controller
@ComponentScan("zj/tool")
public class CmdController {


    @Autowired
    Cmd mycmd;

    @RequestMapping("/getAccounts")
    public String getAccounts(Model model){
        model.addAttribute("ACCOUNTS",mycmd.getAccounts());
        model.addAttribute("TYPE","OWN");
        return TEMPLATES_ACCOUNT_GET_ACCOUNT + RESULT;
    }

    @RequestMapping("/createAccount")
    @ResponseBody
    public String CreatAccount1(@RequestParam String password_input){
        return "Address\n  "+mycmd.CreateAccuont(password_input);
    }

    @RequestMapping("/sendTransaction")
    @ResponseBody
    public String sendTransactions(){
        return "send success";
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


}
