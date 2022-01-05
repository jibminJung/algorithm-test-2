package baekjoon0104;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {
    static String[] day = {"SUN","MON","TUE","WED","THU","FRI","SAT"};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        HashMap<String,Integer> hm = new HashMap<>();
        for (int i = 0; i < day.length; i++) {
            hm.put(day[i],i);
        }

    }
}
