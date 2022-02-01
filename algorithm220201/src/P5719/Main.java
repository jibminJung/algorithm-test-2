package P5719;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int[] records;
    static ArrayList<ArrayList<Integer>> parent;
    static boolean[][] isShortestPath;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        while (true) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            if (v == 0 && e == 0) break;
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            ArrayList<ArrayList<Edge>> arr = new ArrayList<>();
            parent = new ArrayList<>();
            records = new int[v + 1];
            parent.clear();
            isShortestPath = new boolean[v + 1][v + 1];
            for (int i = 0; i < v + 1; i++) {
                arr.add(new ArrayList<>());
                parent.add(new ArrayList<>());
            }
            for (int i = 0; i < e; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                arr.get(from).add(new Edge(to, cost));
            }
            //find shortest path with dijkstra and leave trace
            PriorityQueue<Edge> pq = new PriorityQueue<>();
            pq.offer(new Edge(start, 0));
            Arrays.fill(records, Integer.MAX_VALUE);
            records[start] = 0;
            while (!pq.isEmpty()) {
                Edge now = pq.poll();
                for (Edge next :
                        arr.get(now.to)) {
                    if (records[next.to] == now.cost + next.cost) {
                        parent.get(next.to).add(now.to);
                    } else if (records[next.to] > now.cost + next.cost) {
                        records[next.to] = now.cost + next.cost;
                        parent.get(next.to).clear();
                        parent.get(next.to).add(now.to);
                        pq.offer(new Edge(next.to, now.cost + next.cost));
                    }
                }
            }
            //flag the shortest paths using dfs
            dfs(start, end);
            // run dijkstra
            records = new int[v + 1];
            Arrays.fill(records, Integer.MAX_VALUE);
            pq.clear();
            pq.offer(new Edge(start, 0));
            while (!pq.isEmpty()) {
                Edge now = pq.poll();
                if (now.cost > records[now.to]) {
                    continue;
                }
                for (Edge next :
                        arr.get(now.to)) {
                    if (!isShortestPath[now.to][next.to] && records[next.to] > now.cost + next.cost) {
                        pq.offer(new Edge(next.to, now.cost + next.cost));
                        records[next.to] = now.cost + next.cost;
                    }
                }
            }
            if (records[end] != Integer.MAX_VALUE) {
                sb.append(records[end]).append('\n');
            } else {
                sb.append("-1").append('\n');
            }
        }
        System.out.println(sb);

    }

    static void dfs(int start, int now) {
        if (start == now) return;
        for (int prev :
                parent.get(now)) {
            if (!isShortestPath[prev][now]) {
                isShortestPath[prev][now] = true;
                dfs(start, prev);
            }
        }
    }
}

class Edge implements Comparable<Edge> {
    int to;
    int cost;

    @Override
    public int compareTo(Edge o) {
        return Integer.compare(this.cost, o.cost);
    }

    public Edge(int to, int cost) {
        this.to = to;
        this.cost = cost;
    }
}