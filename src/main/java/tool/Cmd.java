package tool;

import com.sun.org.apache.regexp.internal.RE;
import entity.Account;
import entity.TransactionData;
import lombok.*;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.*;

/**
 * Created by zj on 2017-1-31.
 */

@Component
public class Cmd {

    public static final String UNLOCK_FAIL = "UNLOCK_FAIL";
    public static final String UNLOCK_SUCCESS = "UNLOCK_SUCCESS";
    public static final String UNLOCK_FAIL_RETUEN = "Error: could not decrypt key with given passphrase";
    public static final String ACCOUNT_LOCKED = "Error: account is locked";
    public static final String ACCOUNT_INSUFFICIENT = "Error: Insufficient funds for gas * price + value";
    public static final String RE_HEX_STRING = "^\"0x[0-9a-fA-F]+\"$";
    public static final String RE_HEX = "^0x[0-9a-fA-F]+$";

    private enum State{ NULLSTATE , CREATEACCOUNT , GETTRANSACTION ,
        SENDTRANSACTION , UNLOCKACCOUNT , GETACCOUNTS , GETBALANCE};
    private State state;
    private final String BEGIN = "eeeeeeee";
    private final String END = "ffffffff";
    private final String END_COMMAND = "end();";
    private BufferedReader reader;
    private BufferedWriter writer;
    private ProcessBuilder processBuilder;
    private Process process;

    @Getter private ArrayList<Account> accounts;
    @Getter private ArrayList<TransactionData> transactions;
    private int accounts_count;

    public Cmd() {
        processBuilder = new ProcessBuilder("geth.exe", "attach");
        processBuilder.redirectErrorStream(true);
        accounts = new ArrayList<Account>();

        accounts_count = 0;
        state = State.NULLSTATE;
        try {
            process = processBuilder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));

        LoadScript();
        WriteCmdNoReturn(END_COMMAND);
        Clear();
       ///////以上调用WriteCmdNoReturn

        ////////以下调用WriteCmd
        _GetAccounts();
    }

    private String ReadCmd(){
        StringBuffer sb = new StringBuffer();
        try {
            switch (state){
                case NULLSTATE:
                    break;
                case CREATEACCOUNT:
                    state = State.NULLSTATE;
                    for (String line ; (line = reader.readLine()) != null; ){
                        if(line.contains(END)){
                            break;
                        }else if(line.equals("undefined") || line.equals("> ")){
                            ;
                        }else {
                            System.out.println("address");
                            return line.substring(1,43);
                        }
                    }
                    break;
                case UNLOCKACCOUNT:
                    state = State.NULLSTATE;
                    for (String line ; (line = reader.readLine()) != null; ){
                        if(line.contains(END)){
                            break;
                        }else if(line.equals("undefined") || line.equals("> ")){
                            ;
                        }else if(line.contains("Error:")){
                            System.out.println("passwd error");
                            return UNLOCK_FAIL;
                        }
                        else {
                            System.out.println("unlock");
                            return UNLOCK_SUCCESS;
                        }
                    }
                    break;
                case GETTRANSACTION:
                    state = State.NULLSTATE;
//                    WriteCmdNoReturn(END_COMMAND);
//                    Clear();
                    transactions = new ArrayList<TransactionData>();
                    for (String line ; (line = reader.readLine()) != null; ){
                        if(line.contains(END)) {
                            break;
                        }else if(line.equals("undefined") || line.equals("> ")){
                            ;
                        }else{
                            System.out.println("GETTRANSACTION");
                            transactions.add(new TransactionData(line,reader.readLine(),reader.readLine()));
                        }
                    }
                    return null;

                case SENDTRANSACTION:
                    state = State.NULLSTATE;
                    Clear();
                    for (String line ; (line = reader.readLine()) != null; ){
                        if(line.contains(END)){
                            break;
                        }else if(line.equals("undefined") || line.equals("> ")){
                            ;
                        }else if(line.contains(ACCOUNT_LOCKED)){
                            System.out.println("account unlock");
                            WriteCmdNoReturn(END_COMMAND);
                            Clear();
                            return ACCOUNT_LOCKED;
                        }else if(line.contains(ACCOUNT_INSUFFICIENT)){
                            System.out.println("insufficient money");
                            WriteCmdNoReturn(END_COMMAND);
                            Clear();
                            return ACCOUNT_INSUFFICIENT;
                        }
                        else if(line.matches(RE_HEX_STRING)){
                            System.out.println("send ok");
                            System.out.println(line);
                            return line;
                        }else{
                            System.out.println(line);
                        }
                    }
                    break;
                case GETACCOUNTS:
                    state = State.NULLSTATE;
                    for (String line ; (line = reader.readLine()) != null; ){
                        if(line.contains(END)){
                            break;
                        }else if(line.equals("undefined") || line.equals("> ")){
                            ;
                        }else {
                            sb.append(line+"\n");
                        }
                    }
                    System.out.println("GETACCOUNTS");
                    return sb.toString();
                case GETBALANCE:
                    state = State.NULLSTATE;
                    for (String line ; (line = reader.readLine()) != null; ){
                        if(line.contains(END)){
                            break;
                        }else if(line.equals("undefined") || line.equals("> ")){
                            ;
                        }else if(line.matches("\\d*|\\d+\\.\\d+e\\+\\d+")){
//                            System.out.println("GETBALANCE");
                            return line;
                        }else {
                            ;
                        }
                    }
                    return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String WriteCmd(String command){
        WriteCmdNoReturn(command);
        return ReadCmd();
    }

    private void Clear(){
        try {
            for (String line ; (line = reader.readLine()) != null;){
                System.out.println(line);
                if(line.contains(END)) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    private void LoadScript(){
        WriteCmdNoReturn("loadScript(\"src/main/resources/getblock.js\")");
    }

    public void _GetAccounts() {
        state = State.GETACCOUNTS;
        accounts_count = 0;
        accounts = new ArrayList<Account>();
        for(String s : WriteCmd("getAccounts()").split("\n")){
            state = State.GETBALANCE;
            accounts.add(new Account(accounts_count,s,WriteCmd("getBalance("+( accounts_count++)+")")));
        }
    }

    public ArrayList<Account[]> GetAccountsInto3(){
        ArrayList<Account[]> re = new ArrayList<Account[]>();
//        int index = 0;
        int i = 0;
        for(int j = 0; j< accounts_count / 3 ; j++){
            Account[] a = new Account[3];
            for(int index = 0 ; index <3 ;){
                System.out.println(index);
                a[index++] = accounts.get(i++);
                a[index++] = accounts.get(i++);
                a[index++] = accounts.get(i++);
            }
            re.add(a);
        }
        if (!(accounts_count % 3 == 0 )) {
            Account[] a = new Account[accounts_count % 3];
            for (int j = 0; j < accounts_count % 3; j++)
                a[j] = accounts.get(i++);
            re.add(a);
        }
        return re;
    }

    public String SendTransactionByAccount(int sendIndex , int receiveIndex , int amount , String data , String identification){
        state = State.SENDTRANSACTION;
        WriteCmdNoReturn("s = generate("+sendIndex+","+receiveIndex+","+amount+",\""+data+"\",\""+identification+"\")");
        return WriteCmd("eth.sendTransaction(s)");
    }
    public String CreateAccuont(String passwd){
        state = State.CREATEACCOUNT;
        return(WriteCmd("personal.newAccount(\""+passwd+"\")"));
    }

    public String UnlockAccount(int account , String passwd , int length){
        state = State.UNLOCKACCOUNT;
        if(account > accounts_count)
            return UNLOCK_FAIL_RETUEN;
        return(WriteCmd("personal.unlockAccount(\""+accounts.get(account).getAddress()+"\",\""+passwd+"\","+ length+")"));
    }
    public void GetTransactionDataByIdentification(String identification){
        state = State.GETTRANSACTION;
        WriteCmd("getTransactionDataByIdentification(\""+identification+"\")");
    }


}
