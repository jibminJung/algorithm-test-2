package baekjoon1753;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(br.readLine());
        ArrayList<HashMap<Integer,Integer>> arr = new ArrayList<>();
        int[] ans = new int[V+1];
        for (int i = 0; i < V+1; i++) {
            ans[i] = Integer.MAX_VALUE;
        }
        for (int i = 0; i < V+1; i++) {
            arr.add(new HashMap<Integer,Integer>());
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            HashMap hm = arr.get(u);
            hm.put(v,Math.min((Integer)(hm.getOrDefault(v,Integer.MAX_VALUE)),w));
        }
        PriorityQueue<Pair> pq = new PriorityQueue<>(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return o1.getX() - o2.getX();
            }
        });
        pq.add(new Pair(K,0));
        ans[K] = 0;
        while(!pq.isEmpty()){
            Pair p = pq.poll();
            int cur = p.getX();
            int dist = -p.getY();
            if(ans[cur]<dist)continue;
            for (int key:
            arr.get(cur).keySet()) {
                int next = key;
                int nextDist = dist + arr.get(cur).get(key);
                if(nextDist<ans[next]){
                    ans[next] = nextDist;
                    pq.offer(new Pair(next, -nextDist));
                }
            }


        }
        for (int i = 1; i < V+1; i++) {
            System.out.println(ans[i]==Integer.MAX_VALUE?"INF":ans[i]);
        }



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

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}