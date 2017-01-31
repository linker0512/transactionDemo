package tool;

import java.io.*;

/**
 * Created by zj on 2017-1-31.
 */
public class Cmd {

    private BufferedReader reader;
    private BufferedWriter writer;
    private ProcessBuilder processBuilder;
    private Process process;
    public Cmd(){
        processBuilder = new ProcessBuilder("geth.exe", "attach");
        processBuilder.redirectErrorStream(true);

        try {
            process = processBuilder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
        ReadCmd();
    }
    private void ReadCmd() {
        synchronized (this){
            new Thread(()->{
                while(true){
                    try {
//                        while (reader.read()!=-1) {//缺少第一个字符
//                            System.out.println(reader.readLine());
//                        }
                        for (String line ; (line = reader.readLine()) != null; ) {
                            System.out.println(line);
                        }
                        Thread.sleep(100);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
    public void WriteCmd(String command){
        synchronized (this){
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
}
