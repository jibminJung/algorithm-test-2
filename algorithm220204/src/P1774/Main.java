package P1774;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    static Pair[] pairs;
    static int[] parent;
    static ArrayList<Edge> edges = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        pairs = new Pair[n + 1];
        parent = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            parent[i] = i;
        }
        for (int i = 1; i < n + 1; i++) {
            st = new StringTokenizer(br.readLine());
            pairs[i] = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        for (int i = 1; i < n + 1; i++) {
            for (int j = i + 1; j < n + 1; j++) {
                if (find(i) == find(j)) continue;
                edges.add(new Edge(i, j, getDist(pairs[i], pairs[j])));
            }
        }
        Collections.sort(edges, Comparator.comparing(Edge::getCost));
        double answer = 0;
        int cnt = 0;
        for (Edge edge :
                edges) {
            if (find(edge.from) != find(edge.to)) {
                union(edge.from,edge.to);
                answer += edge.cost;
                if(++cnt==n-1) break;
            }
        }
        System.out.println(String.format("%.2f",answer));
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

    static double getDist(Pair a, Pair b) {
        return Math.sqrt(Math.pow(b.x - a.x, 2) + Math.pow(b.y - a.y, 2));
    }
}

class Pair {
    int x;
    int y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Edge {
    int from;
    int to;
    double cost;

    public Edge(int from, int to, double cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }

    public double getCost() {
        return cost;
    }
}