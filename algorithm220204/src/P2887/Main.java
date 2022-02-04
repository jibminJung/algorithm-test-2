package P2887;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<Planet> planets = new ArrayList<>();
    static ArrayList<Edge> edges = new ArrayList<>();
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        parent = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            planets.add(new Planet(i, x, y, z));
        }
        int numberOfEdges = 0;
        Collections.sort(planets, Comparator.comparing(Planet::getX));
        int i = 0;
        int j = i + 1;
        while (i < n && j < n) {
            Planet from = planets.get(i);
            Planet to = planets.get(j);
            if (from.x == to.x) {
                if (find(from.id) != find(to.id)) {
                    union(from.id, to.id);
                    numberOfEdges++;
                }
                j++;
            } else {
                edges.add(new Edge(from.id, to.id, Math.min(Math.abs(from.x - to.x), Math.min(Math.abs(from.y - to.y), Math.abs(from.z - to.z)))));
                i = j;
                j = i + 1;
            }
        }
        Collections.sort(planets, Comparator.comparing(Planet::getY));
        i = 0;
        j = i + 1;
        while (i < n && j < n) {
            Planet from = planets.get(i);
            Planet to = planets.get(j);
            if (from.y == to.y) {
                if (find(from.id) != find(to.id)) {
                    union(from.id, to.id);
                    numberOfEdges++;
                }
                j++;
            } else {
                edges.add(new Edge(from.id, to.id, Math.min(Math.abs(from.x - to.x), Math.min(Math.abs(from.y - to.y), Math.abs(from.z - to.z)))));
                i = j;
                j = i + 1;
            }
        }
        Collections.sort(planets, Comparator.comparing(Planet::getZ));
        i = 0;
        j = i + 1;
        while (i < n && j < n) {
            Planet from = planets.get(i);
            Planet to = planets.get(j);
            if (from.z == to.z) {
                if (find(from.id) != find(to.id)) {
                    union(from.id, to.id);
                    numberOfEdges++;
                }
                j++;
            } else {
                edges.add(new Edge(from.id, to.id, Math.min(Math.abs(from.x - to.x), Math.min(Math.abs(from.y - to.y), Math.abs(from.z - to.z)))));
                i = j;
                j = i + 1;
            }
        }
        if (numberOfEdges >= n - 1) {
            System.out.println("0");
            return;
        }
        int answer = 0;
        Collections.sort(edges, Comparator.comparingInt(Edge::getCost));
        for (Edge edge :
                edges) {
            if (find(edge.from) != find(edge.to)) {
                union(edge.from,edge.to);
                answer += edge.cost;
                if(++numberOfEdges==n-1) break;
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

class Planet {
    int id, x, y, z;

    public Planet(int id, int x, int y, int z) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }
}

class Edge {
    int from;
    int to;
    int cost;

    public Edge(int from, int to, int cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }
}