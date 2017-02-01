package tool;

import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * Created by zj on 2017-1-31.
 */
public class Cmd {

    private BufferedReader reader;
    private BufferedWriter writer;
    private ProcessBuilder processBuilder;
    private Process process;
    private ArrayList<String> accounts;
    public Cmd() {
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
        GetAccounts();
    }
    private String ReadCmd() {
        final ExecutorService service;
        final Future<String> task;
        service = Executors.newFixedThreadPool(1);
        task = service.submit(new ReadCallBack());
        try {
            final String result = task.get();
            service.shutdownNow();
            return result;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }

    }

    public String WriteCmd(String command){
            try {
                writer.write(command);
                writer.newLine();
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        return ReadCmd();
//        Thread writecommand =  new Thread(new WriteThread(command));
//        writecommand.start();
//        try {
//            writecommand.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        //System.out.println("111111111111111");

    }
    public void LoadScript(){
        WriteCmd("loadScript(\"src/main/resources/getblock.js\")");
    }

    private ArrayList<String> GetAccounts() {
        //String result = WriteCmd("eth.accounts");
        //result.chars().
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

    private class ReadCallBack implements Callable<String> {
        public String result;
        private char[] buf;
        public ReadCallBack(){buf = new char[1024];}
        public String call() {
            try {

                Thread.sleep(5000);
                int len = 1;
                len = reader.read(buf);
                result = new String(buf,0,len);
                System.out.println(result);
            } catch(final InterruptedException ex) {
                ex.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return (result);
        }
    }

    private class WriteThread implements Runnable{

        private String command;
        WriteThread(String a){
            command = a;
        }
        @Override
        public void run() {
            try {
                writer.write(command);
                writer.newLine();
                writer.flush();


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
//    private class ReadThread implements Runnable{
//
//        @Override
//        public void run() {
//
//        }
//    }
}
