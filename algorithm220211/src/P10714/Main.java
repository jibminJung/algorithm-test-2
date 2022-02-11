package P10714;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static long[][] dp;
    static int[] arr;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n+1];
        dp = new long[n + 1][n + 1];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i],-1);
        }
        long answer = 0;
        for (int i = 0; i < n; i++) {
            answer = Math.max(answer,arr[i]+dp(0,(i+n-1)%n,(i+1)%n));
        }
        System.out.println(answer);
    }

    static long dp(int turn, int a, int b) {
        if (a == b) {
            if (turn == 1) {
                return arr[a];
            } else {
                return 0;
            }
        }
        if (dp[a][b] != -1) {
            return dp[a][b];
        }else{
            dp[a][b] =0;
        }
        if (turn == 1) {//조각 선택 후 큰 것 저장
            return dp[a][b] = dp[a][b] + Math.max(arr[a] + dp(0, (a+n-1)%n, b), arr[b] + dp(0, a, (b+1)%n));
        } else {
            if (arr[a] > arr[b]) {
                return dp[a][b] = dp[a][b] +dp(1,(a+n-1)%n,b);
            } else {
                return dp[a][b] = dp[a][b] + + dp(1,a,(b+1)%n);
            }
        }
    }

}
