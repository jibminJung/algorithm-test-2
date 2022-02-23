package P2629;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < n+1; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        boolean[][] dp = new boolean[40001][n+1];
        dp[0][1] = true;
        dp[arr[1]][1] = true;
        for (int i = 2; i < n+1; i++) {
            for (int j = 0; j < 40001; j++) {
                if(dp[j][i-1]){
                    dp[j][i] = true;
                    if(j+arr[i]<40001){
                        dp[j+arr[i]][i] = true;
                    }
                    if(j-arr[i]>=0){
                        dp[j-arr[i]][i] = true;
                    }
                }
            }
        }
        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            int bid = Integer.parseInt(st.nextToken());
            if(dp[bid][n]) sb.append("Y ");
            else sb.append("N ");
        }
        System.out.println(sb);

    }
}
