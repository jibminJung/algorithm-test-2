package P1197;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        ArrayList<Edge> edges = new ArrayList<>();
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            edges.add(new Edge(from, to, cost));
        }
        Collections.sort(edges, Comparator.comparingInt(Edge::getCost));
        parent = new int[v + 1];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
        long answer = 0;
        int count = 0;
        for (Edge now :
                edges) {
            if (find(now.from) != find(now.to)) {
                answer += now.cost;
                union(now.from, now.to);
                if (++count == v - 1) break;
            }
        }
        System.out.println(answer);
    }

    static void union(int a, int b) {
        int repA = find(a);
        int repB = find(b);
        parent[repA] = repB;
    }

    static int find(int a) {
        if (parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }
}

class Edge {
    int from;
    int to;
    int cost;

    public int getCost() {
        return cost;
    }

    public Edge(int from, int to, int cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }
}
