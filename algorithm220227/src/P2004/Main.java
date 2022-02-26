package P2004;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long n = Integer.parseInt(st.nextToken());
        long m = Integer.parseInt(st.nextToken());
        long count5 = count5(n)-count5(n-m)-count5(m);
        long count2 = count2(n)-count2(n-m)-count2(m);
        System.out.println(Math.min(count2,count5));
    }
    static long count5(long num){
        long count = 0;
        while(num>=5){
            count += num/5;
            num /=5;
        }
        return count;
    }
    static long count2(long num){
        long count = 0;
        while(num>=2){
            count += num/2;
            num /=2;
        }
        return count;
    }
}
