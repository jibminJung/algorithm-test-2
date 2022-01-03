package baekjoon11066;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        while (tc-- > 0) {
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            int[] arr = new int[n + 1];
            int[][] dp = new int[n + 1][n + 1];
            int[] sum = new int[n + 1];

            for (int i = 1; i <= n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
                sum[i] = sum[i - 1] + arr[i];
            }

            for (int range = 1; range <= n; range++) {
                for (int from = 1; from+range <= n; from++) {
                    int to = from + range;
                    dp[from][to] =Integer.MAX_VALUE;
                    for (int mid = from; mid < to; mid++) {
                        System.out.println(dp[from][to]);
                        dp[from][to]= Math.min(dp[from][to],dp[from][mid]+dp[mid+1][to]+sum[to]-sum[from-1]);
                        System.out.print(dp[from][mid]+" ");
                        System.out.print(dp[mid+1][to]+" ");
                        System.out.println(sum[to]-sum[from-1]+" ");
                        System.out.println(dp[from][to]);
                        System.out.println("----");
//                        dp[from][to]= Math.min(dp[from][to],dp[from][mid]+dp[mid+1][to]);
                    }
                }
            }
            System.out.println(dp[1][n]);

        }

    }
}
