package baekjoon7579;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int ans = Integer.MAX_VALUE;
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] memory = new int[n];
        int[] cost = new int[n];
        st = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            memory[i] = Integer.parseInt(st.nextToken());
            cost[i] = Integer.parseInt(st2.nextToken());
        }
        int[][] dp = new int[n][10001];
        for (int i = 0; i < 10001; i++) {
            dp[0][i] = cost[0] <= i ? memory[0] : 0;
            if(dp[0][i]>=m) ans = Math.min(ans,i);
        }
        for (int i = 1; i < n; i++) {

            for (int j = 0; j < 10001; j++) {
                if (j >= cost[i]) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - cost[i]] + memory[i]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
                if (dp[i][j] >= m) ans = Math.min(ans, j);
            }

        }
        System.out.println(ans);

    }
}
