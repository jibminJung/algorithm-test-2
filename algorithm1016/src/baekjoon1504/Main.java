package baekjoon1504;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static final int INF = 987654321;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int[][] arr = new int[N+1][N+1];
        for (int i = 1; i < N+1; i++) {
            Arrays.fill(arr[i],INF);
            arr[i][i] = 0;
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            arr[a][b] = Math.min(w,arr[a][b]);
            arr[b][a] = Math.min(w,arr[b][a]);
        }
        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());
        for (int k = 1; k < N+1; k++) {
            for (int i = 1; i < N + 1; i++) {
                for (int j = 1; j < N + 1; j++) {
                    if(i==j||i==k||j==k)continue;
                    arr[i][j] = Math.min(arr[i][j],arr[i][k]+arr[k][j]);
                }
            }
        }
        //1->v1->v2->N
        long ans1 =0;
        if(arr[1][v1]==INF||arr[v1][v2]==INF||arr[v2][N]==INF){
            ans1 =-1;
        }else{
            ans1 = arr[1][v1]+arr[v1][v2]+arr[v2][N];
        }
        //1->v2->v1->N
        long ans2 =0;
        if(arr[1][v2]==INF||arr[v2][v1]==INF||arr[v1][N]==INF){
            ans2 =-1;
        }else{
            ans2 = arr[1][v2]+arr[v2][v1]+arr[v1][N];
        }
        System.out.println(Math.min(ans1,ans2));

    }
}
