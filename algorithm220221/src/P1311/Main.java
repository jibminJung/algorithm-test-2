package P1311;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, visitAll;
    static int[][] arr, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        visitAll = (1 << n) - 1;
        arr = new int[n + 1][n + 1];
        dp = new int[n + 1][visitAll + 1];
        for (int i = 1; i < n + 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j < n + 1; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dfs(1, 0);
        System.out.println(dp[1][0]);

    }

    static int dfs(int person, int status) {
        if (status == visitAll) {
            return 0;
        }
        if(dp[person][status]!=0){
            return dp[person][status];
        }
        int rtn = Integer.MAX_VALUE;
        for (int i = 1; i < n + 1; i++) {
            int nextStatus = status | (1 << i-1);
            if (status == nextStatus) continue;
            rtn = Math.min(rtn, dfs(person + 1, nextStatus)+arr[person][i]);
        }
        return dp[person][status] = rtn;
    }
}
