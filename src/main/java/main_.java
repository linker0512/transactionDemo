/**
 * Created by zj on 2017-1-30.
 */


import entity.TestClass1;
import tool.Cmd;
import tool.Convert;


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
        Cmd cmd = new Cmd();
        cmd.WriteCmd("eth.accounts");
//        cmd.SendTransactionByAccount(0,1,10,"0x1111111131","ethzhangji");
//        cmd.test();
        cmd.WriteCmd("eth.accounts");

    }
}
