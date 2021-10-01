package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a =0;
        double b = 0.0;
        while (st.hasMoreTokens()){
            int number = Integer.parseInt(st.nextToken());
            a += number;
            b += 1/(double)number;
        }
        b = 1/b;
        b = Math.round(b*100)/100.0;
        System.out.println(a+ " " + b);
    }
}
