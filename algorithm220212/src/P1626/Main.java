package P1626;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int v, e, k,mstCost;
    static int[] group, depth;
    static int[][] parent;
    static Pair[][] max;
    static ArrayList<Edge> edges = new ArrayList<>();
    static ArrayList<ArrayList<Node>> mst = new ArrayList<>();
    static ArrayList<Edge> notUsed = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        // Input Edges
        for (int i = 0; i < e ; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            edges.add(new Edge(from, to, cost));
        }
        Collections.sort(edges, Comparator.comparingInt(Edge::getCost));

        // init group array for union-find, init mst array
        group = new int[v + 1];
        for (int i = 0; i < v + 1; i++) {
            group[i] = i;
            mst.add(new ArrayList<>());
        }
        int cnt = 0;
        // kruskal for MST
        for (Edge edge : edges) {
            if (find(edge.from) != find(edge.to)) {
                union(edge.from, edge.to);
                mstCost+=edge.cost;
                cnt++;
                mst.get(edge.from).add(new Node(edge.to, edge.cost));
                mst.get(edge.to).add(new Node(edge.from, edge.cost));
            } else {
                notUsed.add(edge);
            }
        }
        if(cnt!=v-1) {
            System.out.println("-1");
            System.exit(0);
        }

        // make tables for lca
        getK();
        depth = new int[v + 1];
        parent = new int[v + 1][k + 1];
        max = new Pair[v + 1][k + 1];
        for(int i=0;i<v+1;i++) {
            Arrays.fill(max[i], new Pair(-1,-1));
        }
        Queue<Integer> q = new LinkedList<>();
        q.offer(1);
        depth[1] = 1;
        while (!q.isEmpty()) {
            int now = q.poll();
            for (Node next : mst.get(now)) {
                if (depth[next.to] == 0) {
                    depth[next.to] = depth[now] + 1;
                    parent[next.to][0] = now;
                    max[next.to][0] = new Pair(next.cost, -1);
                    q.offer(next.to);
                }
            }
        }

        for (int i = 1; i < k + 1; i++) {
            for (int j = 1; j < v + 1; j++) {
                parent[j][i] = parent[parent[j][i - 1]][i - 1];
                // one step upper
                Pair one = max[j][i - 1];
                // two step upper
                Pair two = max[parent[j][i - 1]][i - 1];
                Pair now = Pair.compare(one, two);
                max[j][i] = now;
            }
        }

        int diff = Integer.MAX_VALUE;
        //use unused edges
        for (Edge edge : notUsed) {
            int minus = getLca(edge.from,edge.to,edge.cost);
            if(minus==-1) continue;
            diff = Math.min(diff, edge.cost-minus);
        }
        if(diff==Integer.MAX_VALUE) {
            System.out.print("-1");
        }else {
            System.out.println(mstCost+diff);
        }

    }

    static void getK() {
        k = 0;
        for (int i = 1; i < v; i *= 2) {
            k++;
        }
    }

    static int getLca(int a, int b, int cost) {
        if (depth[a] < depth[b]) {
            return getLca(b, a, cost);
        }
        int firstMax = Integer.MIN_VALUE;
        int secondMax = -1;
        Pair rtn = new Pair(Integer.MIN_VALUE, -1);
        for (int i = 0; i <= k; i++) {
            if (((depth[a] - depth[b]) & (1 << i)) >= 1) {
                rtn = Pair.compare(rtn, max[a][i]);
                a = parent[a][i];
            }
        }
        if (a == b) {
            if (rtn.first == cost) {
                return rtn.second;
            } else {
                return rtn.first;
            }
        }

        for (int i = k; i >= 0; i--) {
            if (parent[a][i] != parent[b][i]) {
                rtn = Pair.compare(rtn, max[a][i]);
                rtn = Pair.compare(rtn, max[b][i]);
                a = parent[a][i];
                b = parent[b][i];
            }
        }
        rtn = Pair.compare(rtn, max[a][0]);
        rtn = Pair.compare(rtn, max[b][0]);
        if (rtn.first == cost) {
            return rtn.second;
        } else {
            return rtn.first;
        }

    }

    static int find(int a) {
        if (group[a] == a)
            return a;
        return group[a] = find(group[a]);
    }

    static void union(int a, int b) {
        int repA = find(a);
        int repB = find(b);
        group[repA] = repB;
    }

}

class Edge {
    int from, to, cost;

    public Edge(int from, int to, int cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

}

class Node {
    int to, cost;

    public Node(int to, int cost) {
        this.to = to;
        this.cost = cost;
    }

}

class Pair {
    int first, second;

    public Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }

    static Pair compare(Pair p1, Pair p2) {
        int[] temp = { p1.first, p1.second, p2.first, p2.second };
        int tempMax = Integer.MIN_VALUE;
        int secondMax = -1;
        for (int k = 0; k < 4; k++) {
            tempMax = Math.max(tempMax, temp[k]);
        }
        for (int k = 0; k < 4; k++) {
            if (temp[k] == tempMax)
                continue;
            secondMax = Math.max(secondMax, temp[k]);
        }
        return new Pair(tempMax, secondMax);
    }
}