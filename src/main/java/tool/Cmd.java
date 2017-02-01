package tool;

import com.sun.org.apache.xpath.internal.SourceTree;
import entity.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Stream;

/**
 * Created by zj on 2017-1-31.
 */


public class Cmd {

    private final String BEGIN = "eeeeeeee";
    private final String END = "ffffffff";
    private final String END_COMMAND = "end();";
    private final String RE_HEX = "^\"0x[0-9a-fA-F]+\"$";
    private BufferedReader reader;
    private BufferedWriter writer;
    private ProcessBuilder processBuilder;
    private Process process;
//    private ArrayList<String> accounts;
    @Setter private HashMap<Integer, Account> accounts;
    private int lineCount;
    public Cmd() {
        processBuilder = new ProcessBuilder("geth.exe", "attach");
        processBuilder.redirectErrorStream(true);
        accounts = new HashMap<Integer, Account>();
        lineCount = 0;
        try {
            process = processBuilder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));

        LoadScript();
       ///////以上调用WriteCmdNoReturn
        try {
            for (String line; (line = reader.readLine()) != null; ){
                System.out.println(line);
                lineCount++;
                if(lineCount == 10)
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        ////////以下调用WriteCmd
        _GetAccounts();
    }

    public HashMap<Integer, Account> getAccounts() {
        return accounts;
    }

    private String ReadCmd() {
        StringBuilder sb = new StringBuilder();
        try {
            for (String line ; (line = reader.readLine()) != null; ) {
                if(line.equals(END)){
                    break;
                }else if(line.equals("undefined") || line.equals("> ")){
                   ;
                }else if(line.contains("ReferenceError:") || line.contains("Error:")) {
                    System.out.println("error");
                    WriteCmdNoReturn(END_COMMAND);
                }else if(line.matches(RE_HEX)){
                    if(line.length() == 68)
                        System.out.println("this is a block or transaction address");
                    else if(line.length() == 44)
                        System.out.println("this is a account address");
                    System.out.println(line);
                    WriteCmdNoReturn(END_COMMAND);
                }else if(line.equals("true")){
                    System.out.println("ok");
                    WriteCmdNoReturn(END_COMMAND);
                }else{
                    System.out.println(line);
                    sb.append(line+'\n');
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
       return sb.toString();
    }

    public String WriteCmd(String command){
        WriteCmdNoReturn(command);
        return ReadCmd();
    }

    private void WriteCmdNoReturn(String command){
        try {
            writer.write(command);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void LoadScript(){
        WriteCmdNoReturn("loadScript(\"src/main/resources/getblock.js\")");
    }

    private void _GetAccounts() {
        ArrayList<String> result = new ArrayList<String>();
        int index = 0;
        for(String s : WriteCmd("getAccounts()").split("\n")){
            accounts.put(index,new Account(s,WriteCmd("getBalance("+(index++)+")")));
        }
    }

    public void SendTransactionByAccount(int sendIndex , int receiveIndex , int amount , String data , String identification){
        //System.out.println("s = generate("+sendIndex+","+receiveIndex+","+amount+",\""+data+"\",\""+identification+"\")");
        WriteCmd("s = generate("+sendIndex+","+receiveIndex+","+amount+",\""+data+"\",\""+identification+"\")");
        WriteCmd("eth.sendTransaction(s)");
        //WriteCmd(END_COMMAND);

//        WriteCmd("sendTransactionByAccount(sendIndex,receiveIndex,amount,data)");
    }
    public void CreateAccuont(String passwd){
//        System.out.println("personal.newAccount(\""+passwd+"\")");
        WriteCmd("personal.newAccount(\""+passwd+"\")");
    }

    public void UnlockAccount(int account , String passwd , int length){
//        System.out.println("personal.unlockAccount(\"" + accounts.get(account) + "\",\"" + passwd + "\"," + 300 + ")");
        WriteCmd("personal.unlockAccount(\""+accounts.get(account)+"\",\""+passwd+"\","+ 300+")");

    }
    public void GetTransactionDataByIdentification(String identification){
        WriteCmd("getTransactionDataByIdentification(\""+identification+"\")");
    }
//    public void GetBalance

//    成基本交互，包含创建用户，解锁用户，交易，获取特定特征码交易的数据，获取账户
//    private abstract class CmdReader{
//        public String ReadCmd(){
//            StringBuilder sb = new StringBuilder();
//            try {
//                for (String line ; (line = reader.readLine()) != null; ){
//                    if(line.equals(END)){
//                        break;
//                    }else (line.equals("undefined") || line.equals("> ")){
//                        ;
//                    }
//                    DiffCmd();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        protected abstract void DiffCmd();
//    }

}
