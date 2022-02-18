package P11779;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static ArrayList<ArrayList<Bus>> arr = new ArrayList<>();
    static int[] visit, parent;
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        visit = new int[n + 1];
        parent = new int[n + 1];
        Arrays.fill(visit, INF);
        for (int i = 0; i < n + 1; i++) {
            arr.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            arr.get(from).add(new Bus(to, cost));
        }
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        PriorityQueue<Bus> pq = new PriorityQueue<>();
        pq.offer(new Bus(start, 0));
        visit[start]=0;
        while (!pq.isEmpty()) {
            Bus now = pq.poll();
            if(visit[now.to]<now.cost){
                continue;
            }
            for (Bus next :
                    arr.get(now.to)) {
                if (visit[next.to] > now.cost + next.cost) {
                    pq.offer(new Bus(next.to,now.cost+next.cost));
                    visit[next.to] = now.cost+next.cost;
                    parent[next.to] = now.to;
                }
            }
        }
        Stack<Integer> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        sb.append(visit[end]).append('\n');
        while(end!=start){
            stack.push(end);
            end = parent[end];
        }
        stack.push(start);
        sb.append(stack.size()).append('\n');
        while (!stack.isEmpty()){
            sb.append(stack.pop()).append(" ");
        }
        System.out.println(sb);
    }
}

class Bus implements Comparable<Bus> {
    int to;
    int cost;

    public Bus(int to, int cost) {
        this.to = to;
        this.cost = cost;
    }

    @Override
    public int compareTo(Bus o) {
        return Integer.compare(this.cost, o.cost);
    }
}
