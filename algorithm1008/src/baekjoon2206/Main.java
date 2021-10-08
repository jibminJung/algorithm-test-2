package baekjoon2206;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n,m;
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    public static void main(String[] args) throws IOException {
        //맵 정보 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int[][] map = new int[n+1][m+1];
        for (int i = 1; i <= n; i++) {
            char[] in = br.readLine().toCharArray();
            for (int j = 1; j <= m; j++) {
                map[i][j] = in[j-1]-48;
            }
        }
        //(1,1)부터 각 좌표까지 bfs하고, 거리를 dist1에 저장
        Queue<Integer[]> q = new LinkedList<>();
        int[][] dist1 = bfs(1,1,map);
        //(n,m)에서 출발한 거리
        int[][] dist2 = bfs(n,m,map);
        //각 좌표에서 (1,1)에서 출발한 거리와 (n,m)에서 출발한 거리를 더하면 총 거리이고, 가장 짧은것을 찾는다.
        int answer = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if(dist1[i][j]==0||dist2[i][j]==0) continue;
                int temp = dist1[i][j] + dist2[i][j] -1;
                answer = Math.min(temp,answer);
            }
        }
        System.out.println(answer==Integer.MAX_VALUE?-1:answer);
    }
    static int[][] bfs(int x,int y, int[][] map){
        Queue<Integer[]> q = new LinkedList<>();
        int[][] dist = new int[n+1][m+1];
        q.offer(new Integer[]{x,y});
        dist[x][y] = 1;
        while(!q.isEmpty()){
            Integer[] cur = q.poll();
            int tx = cur[0]; int ty = cur[1];
            for (int i = 0; i < 4; i++) {
                int ntx = tx + dx[i];
                int nty = ty + dy[i];
                if(ntx>n||nty>m||ntx<1||nty<1) continue;
                if(dist[ntx][nty] == 0){//첫 방문
                    dist[ntx][nty] = dist[tx][ty] +1;
                    if(map[ntx][nty]==0){//벽이 아니고 갈 수 있다면, q에 넣기
                        q.offer(new Integer[]{ntx,nty});
                    }
                }
            }
        }
        return dist;
    }
}
