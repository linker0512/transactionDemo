package zj.entity;

import lombok.*;

import java.util.HashMap;

/**
 * Created by zj on 2017-3-13.
 */
//@AllArgsConstructor
//@Data
//@NoArgsConstructor

public class SpringResult {
    public static final boolean SUCCESS = true;
    public static final boolean FAIL = false;
    @Getter @Setter boolean flg;
    @Getter @Setter HashMap<String,String> result;
    public SpringResult(){
        result = new HashMap<String , String>();
    }
    public void addResult(String key , String value){
        result.put(key,value);
    }
}
