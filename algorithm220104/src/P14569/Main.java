package P14569;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static long[] subjects;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        subjects = new long[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            long temp = 0;
            for (int j = 0; j < k; j++) {
                int x = Integer.parseInt(st.nextToken());
                temp = temp | (1L <<x);
            }
            subjects[i] = temp;
        }
        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            long temp = 0;
            for (int j = 0; j < p; j++) {
                int x = Integer.parseInt(st.nextToken());
                temp = temp | (1L <<x);
            }
            int cnt =0;
            for (long subject :
                    subjects) {
                if((subject|temp)==temp ){
                    cnt++;
                }
            }
            sb.append(cnt).append('\n');
        }
        System.out.println(sb);


    }
}
