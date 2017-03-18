package zj.tool;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by zj on 2017-1-30.
 */
public class Convert {
    final static String IDENTIFICATION = "0x11111111";

    public static String GetConvertJsonString(Object o){
        try {
            System.out.println(new ObjectMapper().enableDefaultTyping().writeValueAsString(o));
            return new ObjectMapper().enableDefaultTyping().writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String ConvertObjectToHexString(Object o){
//
        try {
            return IDENTIFICATION +
                    new ObjectMapper().
                            writeValueAsString(o).
                            chars().
                            mapToObj(n -> Integer.toHexString(n)).
                            collect(Collectors.joining());
                  } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
    public static Object ConvertHexStringToObject(String hex , Class type){
        hex = hex.substring(10);
        List<String> strings = new ArrayList<String>();
        for(int i = 0 ; i <hex.length() ; i=i+2){
            strings.add(""+hex.charAt(i)+hex.charAt(i+1));
        }
        try {
            return new ObjectMapper().enableDefaultTyping().readValue(
                    strings.stream().
                            mapToInt(n -> Integer.parseInt(n, 16)).
                            mapToObj(n -> ""+(char)(n)).
                            collect(Collectors.joining()), type);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static String ConvertStringTohex(String s){
        return "0x"+s.chars().mapToObj(n -> Integer.toHexString(n)).collect(Collectors.joining());
    }

    public static String ConvertHtml(String s){
        String[] re = s.split("\n");
        StringBuilder sb = new StringBuilder();
        for(String str : re)
            sb.append(("'" + str.trim() + "'+\n"));
        sb.delete(sb.length()-2,sb.length()-1);
        return sb.toString();
    }
}
