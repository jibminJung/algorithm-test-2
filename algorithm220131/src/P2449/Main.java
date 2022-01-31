package P2449;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] arr;
    static int[][] dp;
    static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        arr= new int[n];
        dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i],INF);
        }
        System.out.println(dnq(0,n-1));

    }
    static int dnq(int from, int to){
        if(from == to){
            return dp[from][to] = 0;
        }
        if(dp[from][to]!=INF){
            return dp[from][to];
        }
        int rtn = INF;
        for (int i = from; i < to; i++) {
            int left = dnq(from,i);
            int right = dnq(i+1,to);
            if(arr[from]!= arr[i+1]){
                rtn = Math.min(rtn,left+right+1);
            }else{
                rtn = Math.min(rtn,left+right);
            }
        }

        return dp[from][to]=rtn;
    }
}
