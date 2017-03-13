/**
 * Created by zj on 2017-1-30.
 */
package zj.tool;

import zj.entity.Account;
import zj.entity.TestClass1;
import zj.entity.TransactionData;
import zj.tool.Cmd;
import zj.tool.Convert;

import java.util.*;
import java.util.stream.Collectors;


public class main_ {
    public static int[] findDiagonalOrder(int[][] matrix) {
        int[] result = new int[matrix.length * matrix[0].length];
        int count = matrix.length + matrix[0].length - 1;
        int index = 0;
        int i = 0;
        int j = 0;
        for(int sum = 0 ; sum < count ; sum++){

            if((sum & 1) == 0){
                while(i>=0 && j < matrix[0].length)
                    result[index++] = matrix[i--][j++];
                if(j == matrix[0].length)
                    i+=2;
                if(i == -1)
                    i++;
                if(j == matrix[0].length)
                    j--;
            }else{
                while(i< matrix.length && j >=0)
                    result[index++] = matrix[i++][j--];
                if(i == matrix.length)
                    j+=2;
                if(i == matrix.length)
                    i--;
                if(j == -1)
                    j++;
            }
        }
        return result;
    }
    public static int[] nextGreaterElements(int[] nums) {
        if(nums.length == 0)
            return new int[]{};
        int current = 0;
        int count = 0;
        int[] result = new int[nums.length];
        int i = 0;
        while(current < nums.length){
            if(nums[i] > nums[current]){
                result[current++] = nums[i];
                count = 0;
                i = current;
            }
            i = (++i)%nums.length;
            count++;
            if(count == nums.length){
                result[current++] = -1;
                count = 0;
            }

        }
        return result;
    }
    public static void main(String[] args) {
        int[] aa = new int[]{5,4,3,2,1};
        for(int i : nextGreaterElements(aa))
            System.out.println(i);
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
//        Cmd cmd = new Cmd();
////        cmd.CreateAccuont("1");
//        cmd.UnlockAccount(0,"1",3);
//        cmd.SendTransactionByAccount(0,1,1,"131","^^^^^^^^");
//        System.out.println("USA".matches("[A-Z]+"));
//        cmd.GetTransactionDataByIdentification("^^^^^^^^");
//        cmd.getTransactions().forEach(System.out::println);
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
//        try {
//            Thread.sleep(50);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        cmd.SendTransactionByAccount(2,0,10,"helloworld","^^^^^^^^");
//        cmd.CreateAccuont("5");
//        System.out.println("123123");
        //cmd.WriteCmd("getBalance("+0+")");
        //cmd.GetTransactionDataByIdentification("^^^^^^^^");
        HashMap<Integer,Integer> map = new HashMap<>();
        map.put(1,1);
        map.put(2,34);
        map.put(3,-1);
        List<Integer> types = map
                .entrySet()
                .stream()
                .sorted(Map.Entry.<Integer, Integer>comparingByKey().reversed())
                .map(Map.Entry::getKey)
                .limit(2)
                .collect(Collectors.toList());
//        map.entrySet().stream()
//                .sorted(Map.Entry.<Integer, Integer>comparingByValue())
//                .forEachOrdered(x -> result.add( x.getValue()));
        System.out.println(4&1);
    }
}
