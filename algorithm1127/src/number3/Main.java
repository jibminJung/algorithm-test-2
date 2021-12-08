package number3;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {

        Solution sol = new Solution();
        sol.solution(6,13);
    }


}

class Solution {
    static int[][] dist;
    static int[][] deploy;
    static int n;

    static int[] dx = new int[]{1,0,-1,0};
    static int[] dy = new int[]{0,1,0,-1};

    public int[] solution(int n, int k) {
        int[] answer = {};

        this.n =n;
        dist = new int[n+1][n+1];
        for (int i = 0; i < dist.length; i++) {
            for (int j = 0; j < dist[i].length; j++) {
                dist[i][j] = 2*n;
            }
        }
        deploy = new int[n+1][n+1];

        deploy[1][1] = 1;
        bfs(1,1);
        for (int i = 2; i <= k; i++) {
            Integer[] loc = findLocation();
            deploy[loc[0]][loc[1]] = i;
            bfs(loc[0],loc[1]);
        }
        for (int i = 0; i < deploy.length; i++) {
            for (int j = 0; j < deploy[i].length; j++) {
                if(deploy[i][j]==k){
                    answer = new int[]{i,j};
                }
            }
        }
        return answer;
    }

    static Integer[] findLocation(){
        int far = Integer.MIN_VALUE;
        int x =0;
        int y =0;
        for (int i = 1; i < dist.length; i++) {
            for (int j = 1; j < dist[i].length; j++) {
                if(dist[j][i]>far) {
                    far = dist[j][i];
                    x = j;
                    y = i;
                }

            }
        }
        return new Integer[]{x,y};
    }

    static void bfs(int i,int j){
        Queue<Integer[]> q = new LinkedList<>();
        q.offer(new Integer[]{i,j});
        dist[i][j] =  0;
        boolean[][] check = new boolean[n+1][n+1];
        while(!q.isEmpty()){
            Integer[] temp = q.poll();
            int tx = temp[0];
            int ty = temp[1];
            check[tx][ty] = true;
            for (int k = 0; k < 4; k++) {
                int ntx = tx+dx[k];
                int nty = ty+dy[k];
                if(ntx<=0||nty<=0||ntx>n||nty>n||check[ntx][nty]){
                    continue;
                }
                if(dist[ntx][nty]<=dist[tx][ty]+1){
                    continue;
                }
                dist[ntx][nty] = dist[tx][ty] +1;
                q.offer(new Integer[]{ntx,nty});
            }
        }
    }
}
