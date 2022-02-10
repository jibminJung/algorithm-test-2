package P1520;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n,m;
    static int[][] arr,dp;
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n+1][m+1];
        dp = new int[n+1][m+1];
        for (int i = 1; i < n+1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < m + 1; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }
        System.out.println(dfs(1,1));

    }
    static int dfs(int i, int j){
        if (i==n&&j==m){
            return 1;
        }
        if(dp[i][j]!=-1){
            return dp[i][j];
        }

        dp[i][j] = 0;
        for (int k = 0; k < 4; k++) {
            int nx = i+dx[k];
            int ny = j+dy[k];
            if(nx<1||nx>n||ny<1||ny>m) continue;
            if(arr[i][j]>arr[nx][ny]){
                dp[i][j] += dfs(nx,ny);
            }
        }
        return dp[i][j];
    }
}
