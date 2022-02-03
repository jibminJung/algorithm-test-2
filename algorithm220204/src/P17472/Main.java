package P17472;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static ArrayList<Edge> edges = new ArrayList<>();
    static int[][] map;
    static int n, m, number = 1;
    static int[] parent = new int[8];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
        //Data Input
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        //number the island using bfs
        //islands have number 2~x
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 1) {
                    bfs(i, j);
                }
            }
        }
        //check 4 directions and make Edges
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] != 0) {
                    check4way(i, j);
                }
            }
        }
        Collections.sort(edges, Comparator.comparingInt(Edge::getCost));
        int answer = 0;
        int cnt = 0;
        for (Edge edge :
                edges) {
            if (find(edge.from) != find(edge.to)) {
                union(edge.from, edge.to);
                answer += edge.cost;
                if (++cnt == number - 2) break;
            }
        }
        if (cnt < number - 2) {
            System.out.println("-1");
        } else {
            System.out.println(answer);
        }
    }

    static void bfs(int i, int j) {//섬에 번호 붙히기
        number++;
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(i, j));
        map[i][j] = number;
        while (!q.isEmpty()) {
            Point now = q.poll();
            for (int k = 0; k < 4; k++) {
                int nx = now.x + dx[k];
                int ny = now.y + dy[k];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if (map[nx][ny] == 1) {
                    q.offer(new Point(nx, ny));
                    map[nx][ny] = number;
                }
            }
        }
    }

    static void check4way(int i, int j) {
        for (int k = 0; k < 4; k++) {
            int nx = i + dx[k];
            int ny = j + dy[k];
            if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
            dfs(nx, ny, k, 0, map[i][j]);
        }
    }

    static void dfs(int x, int y, int dir, int length, int startIsland) {//점에서 사방으로 곧게 뻗어나가서 만나는 섬이 있으면 간선으로 추가
        if (map[x][y] != 0) {//섬을 만난 경우
            if (length < 2) {//길이가 2보다 짧다
                return;
            } else if (startIsland == map[x][y]) {
                return;
            } else {//간선에 목록에 추가
                edges.add(new Edge(startIsland, map[x][y], length));
                return;
            }
        }
        if (x + dx[dir] < 0 || x + dx[dir] >= n || y + dy[dir] < 0 || y + dy[dir] >= m) return;
        dfs(x + dx[dir], y + dy[dir], dir, length + 1, startIsland);
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

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
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
