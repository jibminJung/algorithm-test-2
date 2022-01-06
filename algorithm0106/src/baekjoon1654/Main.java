package baekjoon1654;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int k,n;
    static int[] cable;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        k = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        cable = new int[k];

        long l = 1;
        long r = 0;

        for (int i = 0; i < k; i++) {
            cable[i] = Integer.parseInt(br.readLine());
            r = Math.max(r, cable[i]);
        }
        while (l < r) {
            long mid = (l + r+1) / 2;
            if (pos(mid)) {
                l = mid;
            } else {
                r = mid -1;
            }
        }
        System.out.println(l);
    }

    static boolean pos(long length) {
        int cnt = 0;
        for (int cable : cable) {
            cnt += cable / length;
            if(cnt>=n) return true;
        }
        return false;
    }
}
