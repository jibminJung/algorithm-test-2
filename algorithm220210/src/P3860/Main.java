package P3860;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int w, h, g, e;
    static int[][] map;
    static ArrayList<Edge> edges = new ArrayList<>();
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        while (true) {
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            if (w == 0 && h == 0) {
                break;
            }
            map = new int[w][h];
            g = Integer.parseInt(br.readLine());
            for (int i = 0; i < g; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                map[x][y] = -1;
            }
            e = Integer.parseInt(br.readLine());
            for (int i = 0; i < e; i++) {
                st = new StringTokenizer(br.readLine());
                int x1 = Integer.parseInt(st.nextToken());
                int y1 = Integer.parseInt(st.nextToken());
                int x2 = Integer.parseInt(st.nextToken());
                int y2 = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken());
                edges.add(new Edge(new Point(x1, y1), new Point(x2, y2), t));
                map[x1][y1] = -1;
            }
            map[w - 1][h - 1] = -1;
            for (int i = 0; i < w; i++) {
                for (int j = 0; j < h; j++) {
                    if (map[i][j] == 0) {
                        for (int k = 0; k < 4; k++) {
                            int nx = i + dx[k];
                            int ny = j + dy[k];
                            if (nx < 0 || nx >= w || ny < 0 || ny >= h) continue;
                            if (map[nx][ny] == 0) {
                                edges.add(new Edge(new Point(i, j), new Point(nx, ny), 1));
                            }
                        }
                    }
                }
            }
            //bellman-ford
            int[][] dist = new int[w][h];
            for (int i = 0; i < w; i++) {
                Arrays.fill(dist[i], Integer.MAX_VALUE);
            }
            for (int i = 0; i < w * h; i++) {
                for (Edge edge : edges) {
                    if (dist[edge.from.x][edge.from.y] != Integer.MAX_VALUE) {
                        if (dist[edge.to.x][edge.to.y] > dist[edge.from.x][edge.from.y] + edge.cost) {
                            dist[edge.to.x][edge.to.y] = dist[edge.from.x][edge.from.y] + edge.cost;
                        }
                    }
                }
            }
            boolean flag = false;

            for (Edge edge :
                    edges) {
                if (dist[edge.from.x][edge.from.y] != Integer.MAX_VALUE) {
                    if (dist[edge.to.x][edge.to.y] > dist[edge.from.x][edge.from.y] + edge.cost) {
                        dist[edge.to.x][edge.to.y] = dist[edge.from.x][edge.from.y] + edge.cost;
                        flag = true;
                        break;
                    }
                }
            }

            if (flag) {
                sb.append("Never");
            }else if(dist[w-1][h-1]==Integer.MAX_VALUE){
                sb.append("Impossible");
            }else{
                sb.append(dist[w-1][h-1]);
            }
        }
        System.out.println(sb);

    }
}

class Edge {
    Point from, to;
    int cost;

    public Edge(Point from, Point to, int cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }
}

class Point {
    int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}