/**
 * Created by zj on 2017-1-30.
 */
package tool;

import entity.Account;
import entity.TestClass1;
import entity.TransactionData;
import tool.Cmd;
import tool.Convert;

import java.util.Iterator;
import java.util.Map;


public class main_ {

    public static void main(String[] args) {

//        TestClass1 testClass1 = new TestClass1(1,"453245");
//        String testClass1 = new String("123");
//        Convert.GetConvertJsonString(testClass1);
//        System.out.println(testClass1);
//        String hexString = Convert.ConvertObjectToHexString(testClass1);
//        System.out.println(hexString);
//        String testClass2 = (String) Convert.ConvertHexStringToObject(hexString,String.class);
//        System.out.println(testClass2);
//        System.out.println(testClass1.equals(testClass2));
//        System.out.println("21321321321".matches("\\d+"));
        Cmd cmd = new Cmd();
        cmd.GetTransactionDataByIdentification("^^^^^^9^^");
        cmd.getTransactions().forEach(System.out::println);
//        System.out.println("\"0xba3ce47b92eefa36e5535f21826cff4a90c3d2ae\"".length());
//        cmd.WriteCmdNoReturn("end();");
//        cmd.Clear();
//        cmd.WriteCmd("getAccounts()");
//        System.out.println("0xec32dce9bef77dfacf1c52503ed6b7a7da8892d4".length());
//        System.out.println(Convert.ConvertStringTohex("helloworld"));
//        System.out.println((cmd.UnlockAccount(0, "1", 300)));
//        System.out.println(cmd.SendTransactionByAccount(0, 1, 10, "0x1111111131", "1"));
//        cmd.test();
        // cmd.WriteCmd("getAllBlock()");
        //System.out.println("\"0xcce6b1e24e42c00e431917797aab3aea53231ce3e304d5375d0e47f8f3ac5b4c\"".matches("^\"0x[0-9a-fA-F]+\"$"));
//        System.out.println("\"0x0000000000000000000000000000000000000000000000000000000000000000\"".length());
//        System.out.println((cmd.UnlockAccount(2, "3", 300)));
//        cmd.CreateAccuont("5");
//        cmd.getAccounts();
//        cmd.CreateAccuont("5");
//        cmd.getAccounts();
//        cmd.CreateAccuont("5");
//        System.out.println((cmd.UnlockAccount(2, "3", 300)));
//        cmd.CreateAccuont("5");
//        System.out.println((cmd.UnlockAccount(3, "4", 300)));
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        cmd.SendTransactionByAccount(2,0,10,"helloworld","^^^^^^^^");
//        cmd.CreateAccuont("5");

        //cmd.WriteCmd("getBalance("+0+")");
        //cmd.GetTransactionDataByIdentification("^^^^^^^^");
    }
}
