/**
 * Created by zj on 2017-1-30.
 */


import entity.TestClass1;
import tool.Cmd;
import tool.Convert;


public class main_ {

    public static void main(String[] args) {

//        TestClass1 testClass1 = new TestClass1(1,"453245");
//        Convert.GetConvertJsonString(testClass1);
//        System.out.println(testClass1);
//        String hexString = Convert.ConvertObjectToHexString(testClass1);
//        System.out.println(hexString);
//        TestClass1 testClass2 = (TestClass1) Convert.ConvertHexStringToObject(hexString,TestClass1.class);
//        System.out.println(testClass2);
//        System.out.println(testClass1.equals(testClass2));
          Cmd cmd = new Cmd();
          cmd.LoadScript();
          cmd.WriteCmd("getAllBlock()");


    }
}
