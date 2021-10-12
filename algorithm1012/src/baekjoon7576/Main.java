package baekjoon7576;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    static int[][] box, date;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        box = new int[n][m];
        date = new int[n][m];
        Queue<Integer[]> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                box[i][j] = Integer.parseInt(st.nextToken());
                if(box[i][j] == 1){
                    q.offer(new Integer[]{i,j});
                    date[i][j] =0;
                }
            }
        }
        while(!q.isEmpty()){
            Integer[] t = q.poll();
            int tx = t[0];
            int ty = t[1];
            for (int i = 0; i < 4; i++) {
                int ntx = tx + dx[i];
                int nty = ty + dy[i];
                if(ntx<0||ntx>=n||nty<0||nty>=m){
                    continue;
                }
                if(box[ntx][nty] == 0 && date[ntx][nty] == 0){
                    q.offer(new Integer[]{ntx,nty});
                    date[ntx][nty] = date[tx][ty] +1;
                    box[ntx][nty] = 1;
                }
            }
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (box[i][j]==0){
                    System.out.println("-1");
                    return;
                }
                max = Math.max(max,date[i][j]);
            }
        }
        System.out.println(max);



    }
}
