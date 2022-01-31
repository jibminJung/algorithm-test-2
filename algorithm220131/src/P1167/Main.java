package P1167;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static ArrayList<ArrayList<Edge>> arr = new ArrayList<>();
    static int[] cost;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n + 1; i++) {
            arr.add(new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            while (st.hasMoreTokens()) {
                int to = Integer.parseInt(st.nextToken());
                if (to == -1) continue;
                int cost = Integer.parseInt(st.nextToken());
                arr.get(from).add(new Edge(to, cost));
            }
        }
        bfs(1);
        int far = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < cost.length; i++) {
            if(cost[i]>max) {
                max = cost[i];
                far = i;
            }
        }
        bfs(far);
        max = Integer.MIN_VALUE;
        for (int i = 0; i < cost.length; i++) {
            if(cost[i]>max) {
                max = cost[i];
            }
        }
        System.out.println(max);

    }

    static void bfs(int start) {
        cost = new int[n + 1];
        Arrays.fill(cost, -1);
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        cost[start] = 0;
        while (!q.isEmpty()) {
            int now = q.poll();
            for (Edge next :
                    arr.get(now)) {
                if (cost[next.to] == -1) {
                    cost[next.to] = cost[now] + next.cost;
                    q.offer(next.to);
                }
            }
        }


    }
}

class Edge {
    int to;
    int cost;

    public Edge(int to, int cost) {
        this.to = to;
        this.cost = cost;
    }
}
