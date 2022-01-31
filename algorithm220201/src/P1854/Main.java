package P1854;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int v, e, k;
    static ArrayList<ArrayList<Edge>> roads = new ArrayList<>();
    static ArrayList<PriorityQueue<Integer>> records = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        for (int i = 0; i < v + 1; i++) {
            records.add(new PriorityQueue<>(k, Comparator.reverseOrder()));
            roads.add(new ArrayList<>());
        }
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            roads.get(from).add(new Edge(to, cost));
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(1, 0));
        records.get(1).offer(0);

        while (!pq.isEmpty()) {
            Edge now = pq.poll();
            for (Edge next :
                    roads.get(now.to)) {
                PriorityQueue<Integer> nextRecord = records.get(next.to);
                if (nextRecord.size() < k) {
                    pq.offer(new Edge(next.to, now.cost + next.cost));
                    nextRecord.offer(now.cost + next.cost);
                } else if (nextRecord.peek() > now.cost + next.cost) {
                    nextRecord.poll();
                    pq.offer(new Edge(next.to, now.cost + next.cost));
                    nextRecord.offer(now.cost + next.cost);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < records.size(); i++) {
            PriorityQueue<Integer> record = records.get(i);
            if (record.size() < k) {
                sb.append("-1").append('\n');
            } else {
                sb.append(record.peek()).append('\n');
            }
        }
        System.out.println(sb);

    }
}

class Edge implements Comparable<Edge> {
    int to;
    int cost;

    public Edge(int to, int cost) {
        this.to = to;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge o) {
        return this.cost - o.cost;
    }
}
