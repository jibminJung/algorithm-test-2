package P9370;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static final int INF = Integer.MAX_VALUE/2*2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int tc = Integer.parseInt(br.readLine());
        while (tc-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n, m, t;
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            t = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            int s, g, h;
            s = Integer.parseInt(st.nextToken());
            g = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            ArrayList<ArrayList<Edge>> arr = new ArrayList<>();
            for (int i = 0; i < n + 1; i++) {
                arr.add(new ArrayList<>());
            }
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                if (!(from == g && to == h) && !(from == h && to == g)) {
                    arr.get(from).add(new Edge(to, cost*2));
                    arr.get(to).add(new Edge(from, cost*2));
                } else {
                    arr.get(from).add(new Edge(to, cost*2-1));
                    arr.get(to).add(new Edge(from, cost*2-1));
                }
            }
            PriorityQueue<Edge> pq = new PriorityQueue<>();
            int[] dist2 = new int[n + 1];
            boolean[] chk = new boolean[n+1];
            Arrays.fill(dist2, INF);
            pq.offer(new Edge(s, 0));
            dist2[s] = 0;
            while (!pq.isEmpty()) {
                Edge now = pq.poll();
                if (now.cost > dist2[now.to]) continue;
                if(chk[now.to]) continue;
                chk[now.to] = true;
                for (Edge next : arr.get(now.to)) {
                    if (!chk[next.to] && dist2[next.to] > now.cost + next.cost) {
                        dist2[next.to] = now.cost + next.cost;
                        pq.offer(new Edge(next.to, now.cost + next.cost));
                    }
                }
            }
            ArrayList<Integer> candidate = new ArrayList<>();
            for (int i = 0; i < t; i++) {
                candidate.add(Integer.parseInt(br.readLine()));
            }
            Collections.sort(candidate);
            for (int candi :
                    candidate) {
                if (dist2[candi]%2==1) {
                    sb.append(candi).append(' ');
                }
            }
            sb.append('\n');

        }
        System.out.println(sb);

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
