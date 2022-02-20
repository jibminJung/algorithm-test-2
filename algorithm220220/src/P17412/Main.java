package P17412;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static final int INF = Integer.MAX_VALUE;
    static int n, p;
    static ArrayList<ArrayList<Integer>> arr = new ArrayList<>();
    static int[][] capa;
    static int[][] flow;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());
        capa = new int[n + 1][n + 1];
        flow = new int[n + 1][n + 1];
        for (int i = 0; i < n + 1; i++) {
            arr.add(new ArrayList<>());
        }
        for (int i = 0; i < p; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            arr.get(from).add(to);
            arr.get(to).add(from);
            capa[from][to] = 1;
        }
        parent = new int[n + 1];
        int ans = 0;
        while (true) {
            Arrays.fill(parent, -1);
            Queue<Integer> q = new LinkedList<>();
            q.offer(1);
            while (!q.isEmpty()) {
                int now = q.poll();
                for (int next : arr.get(now)) {
                    if (parent[next] == -1) {
                        if (capa[now][next] - flow[now][next] > 0) {
                            q.offer(next);
                            parent[next] = now;
                            if (next == 2) break;
                        }
                    }
                }
            }//end while
            if (parent[2] == -1) {
                break;
            }
            int minFlow = INF;
            for (int i = 2; i != 1; i = parent[i]) {
                minFlow = Math.min(minFlow, capa[parent[i]][i] - flow[parent[i]][i]);
            }
            for (int i = 2; i != 1; i = parent[i]) {
                flow[parent[i]][i] += minFlow;
                flow[i][parent[i]] -= minFlow;
            }
            ans += minFlow;

        }//end while
        System.out.println(ans);
    }
}
