package P1005;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb =new StringBuilder();
        int tc = Integer.parseInt(br.readLine());
        while (tc-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            int[] arr = new int[n + 1];
            int[] inDegree = new int[n + 1];
            int[] dp = new int[n + 1];
            ArrayList<ArrayList<Integer>> orders = new ArrayList<>();
            orders.add(new ArrayList<>());
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i < n + 1; i++) {//건물 비용
                arr[i] = Integer.parseInt(st.nextToken());
                orders.add(new ArrayList<>());
            }
            for (int i = 0; i < k; i++) {//순서 조건
                st = new StringTokenizer(br.readLine());
                int first = Integer.parseInt(st.nextToken());
                int then = Integer.parseInt(st.nextToken());
                orders.get(first).add(then);
                inDegree[then]++;
            }
            int w = Integer.parseInt(br.readLine());
            Queue<Integer> q = new LinkedList<>();
            for (int i = 1; i < inDegree.length; i++) {
                if (inDegree[i] == 0) {
                    q.offer(i);
                    dp[i] = arr[i];
                }
            }
            while (!q.isEmpty()) {
                int now = q.poll();
                for (int next : orders.get(now)) {
                    dp[next] = Math.max(dp[next], dp[now] + arr[next]);
                    inDegree[next]--;
                    if (inDegree[next] == 0) {
                        q.offer(next);
                    }
                }
            }
            sb.append(dp[w]).append('\n');

        }
        System.out.println(sb);
    }
}