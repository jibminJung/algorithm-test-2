package P10830;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static long[][] arr, result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        long b = Long.parseLong(st.nextToken());
        arr = new long[n][n];
        result = new long[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
            result[i][i] = 1;
        }
        pow(b);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(result[i][j]).append(' ');
            }
            sb.append('\n');
        }
        System.out.println(sb);

    }

    static void pow(long b) {
        while (b > 0) {
            if (b % 2 == 1) {
                result = matMul(result, arr);
            }
            arr = matMul(arr, arr);
            b /= 2;
        }
    }

    static long[][] matMul(long[][] m1, long[][] m2) {
        int n, m, k;
        n = m1.length;
        m = m2.length;
        k = m2[0].length;
        long[][] result = new long[n][k];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < k; j++) {
                for (int l = 0; l < m; l++) {
                    result[i][j] += m1[i][l] * m2[l][j];
                    result[i][j] %= 1000;
                }
            }
        }
        return result;
    }
}
