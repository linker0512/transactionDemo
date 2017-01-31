package tool;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by zj on 2017-1-31.
 */
public class Cmd {

    private BufferedReader reader;
    private BufferedWriter writer;
    private ProcessBuilder processBuilder;
    private Process process;
    private ArrayList<String> accounts;
    public Cmd(){
        processBuilder = new ProcessBuilder("geth.exe", "attach");
        processBuilder.redirectErrorStream(true);
        accounts = new ArrayList<String>();
        try {
            process = processBuilder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
        LoadScript();
        ReadCmd();

       // GetAccounts();
    }
    private void ReadCmd() {
       synchronized (this){
           new Thread(() ->{
               try {
                    for (String line ; ((line = reader.readLine()) != null);){
                        System.out.println(line);
                        }
               } catch (IOException e) {
                   e.printStackTrace();
               }
               //System.out.println("111111111");
           }).start();
       }
    }

    public void WriteCmd(String command){
        synchronized (this) {
            try {
                writer.write(command);
                writer.newLine();
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void LoadScript(){
        WriteCmd("loadScript(\"src/main/resources/getblock.js\")");
    }

    private ArrayList<String> GetAccounts() {
        //ArrayList<String> _accounts = new ArrayList<String>();
        try {
            writer.write("getAccounts()");
            writer.newLine();
            writer.flush();
            for (String line ; ((line = reader.readLine()) != null)
                    && (line.length() != 0); ){
                if(!line.equals("> ") && (!line.equals("undefined")))
                    accounts.add(line);
                //System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    public void SendTransactionByAccount(int sendIndex , int receiveIndex , int amount , String data , String passwd){
        WriteCmd("personal.unlockAccount("+accounts.get(sendIndex)+","+passwd+", 300)");
        WriteCmd("sendTransactionByAccount(sendIndex,receiveIndex,amount,data)");
    }

    public void test(){
        String a = "personal.unlockAccount("+accounts.get(0)+","+"ethzhangji"+", 300)";
        accounts.add("123");
        System.out.println("personal.unlockAccount("+accounts.get(0)+","+"ethzhangji"+", 300)");
    }
}
