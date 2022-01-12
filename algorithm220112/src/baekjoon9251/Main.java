package baekjoon9251;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String first = " " + br.readLine();
        String second = " " + br.readLine();
        int[][] dp = new int[first.length()][second.length()];

        for (int i = 0; i < dp.length; i++) {
            for (int j = i; j < dp[i].length; j++) {
                boolean b = first.charAt(i) == second.charAt(j);
                if (i == 0) {
                    dp[i][j] = 0;

                } else if (b) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        System.out.println(dp[first.length()-1][second.length()-1]);
    }
}
