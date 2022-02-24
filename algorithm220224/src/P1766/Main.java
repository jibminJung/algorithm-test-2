package P1766;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] inDegree = new int[n + 1];
        ArrayList<ArrayList<Integer>> arr = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            arr.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken());
            int then = Integer.parseInt(st.nextToken());
            arr.get(first).add(then);
            inDegree[then]++;
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 1; i < n + 1; i++) {
            if (inDegree[i] == 0) pq.offer(i);
        }
        while (!pq.isEmpty()) {
            int now = pq.poll();
            sb.append(now).append(' ');
            for (int next : arr.get(now)) {
                if(--inDegree[next]==0){
                    pq.offer(next);
                }
            }
        }
        System.out.println(sb);
    }
}

