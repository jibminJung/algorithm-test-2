package baekjoon1753;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static final int INF = 987654321;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(br.readLine());
        ArrayList<ArrayList<Pair>> arr = new ArrayList<>();
        int[] ans = new int[V+1];
        for (int i = 0; i < V+1; i++) {
            ans[i] = INF;
            arr.add(new ArrayList<Pair>());
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            arr.get(u).add(new Pair(v,w));
        }
        PriorityQueue<Pair> pq = new PriorityQueue<>((o1, o2) -> o2.getY() - o1.getY());
        pq.add(new Pair(K,0));
        ans[K] = 0;
        boolean[] chk = new boolean[V+1];
        while(!pq.isEmpty()){
            Pair p = pq.poll();
            int cur = p.getX();
            if(chk[cur])continue;
            chk[cur] = true;
            for (Pair pair: arr.get(cur)) {
                int next = pair.getX();
                int nextDist = ans[cur]+ pair.getY();
                if(nextDist<ans[next]){
                    ans[next] = nextDist;
                    pq.offer(new Pair(next, nextDist));
                }
            }
        }
        StringBuilder sb= new StringBuilder();
        for (int i = 1; i < V+1; i++) {
            if(ans[i]==INF){
                sb.append("INF").append('\n');
            }else{
                sb.append(ans[i]).append('\n');
            }
        }
        System.out.println(sb);


    }
}
class Pair{
    private int x;
    private int y;
    Pair(int x,int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}