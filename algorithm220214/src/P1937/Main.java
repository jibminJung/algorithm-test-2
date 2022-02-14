package P1937;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] arr, dp;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ans = Math.max(ans,dfs(i,j));
            }
        }
        System.out.println(ans);


    }

    static int dfs(int i, int j) {

        if(dp[i][j]!=0){
            return dp[i][j];
        }
        int rtn = 1;
        for (int k = 0; k < 4; k++) {
            int nx = i + dx[k];
            int ny = j + dy[k];
            if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                continue;
            }
            if (arr[nx][ny] > arr[i][j]) {
                rtn = Math.max(dfs(nx, ny)+1,rtn);
            }
        }
        return dp[i][j] = rtn;
    }
}
