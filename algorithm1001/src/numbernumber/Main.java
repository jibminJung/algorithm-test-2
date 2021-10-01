package numbernumber;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int[] cnt = new int[10];
        for (int i = a+1; i < b; i++) {
            int cur = i;
            while(cur!=0){
                cnt[cur%10]++;
                cur /= 10;
            }
        }
        printArr(cnt);
    }
    static void printArr(int[] arr){
        StringBuilder sb = new StringBuilder();
        for (int a :
                arr) {
            sb.append(a).append(" ");
        }
        System.out.println(sb);
    }
}
