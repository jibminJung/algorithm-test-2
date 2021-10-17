package baekjoon11657;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static final int INF = 987654321;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        ArrayList<ArrayList<Pair>> arr = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            arr.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            arr.get(Integer.parseInt(st.nextToken())).add(new Pair(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
        }
        //long 주의!
        long[] dist = new long[N+1];
        for (int i = 1; i < N + 1; i++) {
            dist[i] = INF;
        }
        boolean cycle = false;
        dist[1]= 0;
        for (int i = 1; i < N+1; i++) {//경로 길, N보다 작아야한다.
            for (int j = 1; j < N+1; j++) {//출발지점
                for (Pair p : arr.get(j)) {
                    if(dist[j]!=INF&& dist[p.x]>dist[j] + p.y){
                        dist[p.x] = dist[j] + p.y;
                        if(i==N){
                            cycle = true;
                        }
                    }
                }

            }
        }
        if(cycle){
            System.out.println("-1");
        }else{
            StringBuilder sb= new StringBuilder();
            for (int i = 2; i < N+1; i++) {
                if(dist[i]==INF){
                    sb.append("-1").append('\n');
                }else{
                    sb.append(dist[i]).append('\n');
                }
            }
            System.out.println(sb);
        }

    }
}
class Pair{
    int x,y;
    Pair(int x,int y){
        this.x =x;
        this.y =y;
    }
}