package P17404;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] arr, dp;
    static final int INF = 1000*1000+1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n][3];
        dp = new int[n][3];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int ans = INF;
        for (int k = 0; k < 3; k++) {
            dp[0][k]=arr[0][k];
            dp[0][(k+1)%3]=INF;
            dp[0][(k+2)%3]=INF;
            for (int i = 1; i < n; i++) {
                dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + arr[i][0];
                dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + arr[i][1];
                dp[i][2] = Math.min(dp[i - 1][1], dp[i - 1][0]) + arr[i][2];
                if(i==n-1){
                    for (int j = 0; j < 3; j++) {
                        if(j==k)continue;
                        ans = Math.min(ans,dp[i-1][(j+1)%3]+arr[i][j]);
                        ans = Math.min(ans,dp[i-1][(j+2)%3]+arr[i][j]);
                    }
                }
            }
        }
        System.out.println(ans);
    }

}
