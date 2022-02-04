package P4386;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    static Star[] stars;
    static int[] parent;
    static ArrayList<Edge> edges = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        stars = new Star[n];
        parent = new int[n];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            stars[i] = new Star(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double dist = Math.sqrt(Math.pow(stars[j].x - stars[i].x, 2) + Math.pow(stars[j].y - stars[i].y, 2));
                edges.add(new Edge(i, j, dist));
            }
        }
        double answer = 0;
        int cnt=0;
        Collections.sort(edges, Comparator.comparing(Edge::getCost));
        for (Edge edge :
                edges) {
            if (find(edge.from) != find(edge.to)) {
                union(edge.from,edge.to);
                answer += edge.cost;
                if(++cnt==n-1) break;
            }
        }
        System.out.println(Math.round(answer*100)/100.0);
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

class Star {
    double x;
    double y;

    public Star(double x, double y) {
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
