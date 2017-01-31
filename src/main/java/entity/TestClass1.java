package entity;

import java.util.ArrayList;

/**
 * Created by zj on 2017-1-30.
 */
public class TestClass1 {
    public int a;
    public String b;
    public ArrayList<Character> characters;
    public TestClass1(){}
    public TestClass1(int _a, String _b){
        a = _a;
        b = _b;
        characters = new ArrayList<Character>();
        for(char a = '0' ; a <= '9' ; a++)
            characters.add(a);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(char ch : characters){
            sb.append(ch);
        }
        return String.format("a=%d,b=%s,characters=%s",a,b,sb.toString());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        return this.toString().equals(obj.toString());
    }
}
