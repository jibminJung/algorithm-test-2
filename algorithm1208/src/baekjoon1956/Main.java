package baekjoon1956;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        int[][] arr = new int[V+1][V+1];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if(i==j)continue;
                arr[i][j] = INF;
            }
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            arr[u][v] = w;
        }

        for (int k = 1; k < V+1; k++) {
            for (int i = 1; i < V+1; i++) {
                for (int j = 1; j < V+1; j++) {
                    if(i==j)continue;
                    if(arr[i][k]+arr[k][j] < arr[i][j]){
                        arr[i][j] = arr[i][k]+arr[k][j];
                    }
                }
            }
        }
        int ans = INF;
        for (int i = 1; i < V + 1; i++) {
            for (int j = 1; j < V + 1; j++) {
                if(i==j)continue;
                if(arr[i][j] != INF && arr[j][i]!=INF){
                    ans = Math.min(arr[i][j]+arr[j][i],ans);
                }
            }
        }
        System.out.println(ans==INF?-1:ans);

    }
}
