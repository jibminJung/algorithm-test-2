package baekjoon12852;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static final int INF = 987654321;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n+1];
        Arrays.fill(dp,INF);
        dp[1]=0;
        for (int i = 2; i <= n; i++) {
            if(i%3==0){
                dp[i] = Math.min(dp[i],dp[i/3]+1);
            }
            if(i%2==0){
                dp[i] = Math.min(dp[i],dp[i/2]+1);
            }
            dp[i] = Math.min(dp[i],dp[i-1]+1);
        }
        System.out.println(dp[n]);
        int cnt = dp[n]-1;
        int prev = n;
        StringBuilder sb = new StringBuilder();
        sb.append(n).append(" ");
        for (int i = n-1; i > 0; i--) {
            if(dp[i]==cnt){
                if((prev%3==0&&prev/3==i)||(prev%2==0&&prev/2==i)||(prev-1==i)){
                    sb.append(i).append(" ");
                    prev = i;
                    cnt -=1;
                }
            }
        }
        System.out.println(sb);


    }
}
