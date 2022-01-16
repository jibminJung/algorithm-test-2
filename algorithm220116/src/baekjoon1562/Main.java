package baekjoon1562;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static long[][][] dp;
    static final int MOD = 1000000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        dp = new long[10][n + 1][1 << 10];
        for (int i = 1; i < 10; i++) {
            dp[i][1][1 << i] = 1;
        }
        for (int i = 2; i < n + 1; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < (1 << 10); k++) {
                    int newState = k | (1 << j);
                    if (j == 0) {
                        dp[j][i][newState] = (dp[1][i-1][k] + dp[j][i][newState])%MOD;
                    } else if (j == 9) {
                        dp[j][i][newState] = (dp[8][i-1][k] + dp[j][i][newState])%MOD;
                    } else {
                        dp[j][i][newState] = (dp[j-1][i-1][k]+ dp[j+1][i-1][k] + dp[j][i][newState])%MOD;
                    }
                }
            }
        }
        long sum = 0;
        for (int i = 0; i < 10; i++) {
            sum = (sum + dp[i][n][(1<<10) -1])%MOD;
        }
        System.out.println(sum);
    }
}
