package kakao2021intern1;

import java.util.HashMap;

public class Solution {
    HashMap<String,String> hm = new HashMap<>(10){{
        put("zero","0");
        put("one","1");
        put("two","2");
        put("three","3");
        put("four","4");
        put("five","5");
        put("six","6");
        put("seven","7");
        put("eight","8");
        put("nine","9");
    }

    };
    public int solution(String s) {
        for (String num :
                hm.keySet()) {
            s = s.replace(num,hm.get(num));
        }
        return Integer.parseInt(s);
    }
}
