package P7569;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {0,1,0,-1,0,0};
    static int[] dy = {1,0,-1,0,0,0};
    static int[] dz = {0,0,0,0,1,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n,m,h;
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        int[][][] arr = new int[m+1][n+1][h+1];
        int[][][] visit = new int[m+1][n+1][h+1];
        int cnt= 0;
        int turn = 0;
        Queue<Node> q = new LinkedList<>();
        for (int i = 1; i < h+1; i++) {
            for (int j = 1; j < n+1; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 1; k < m+1; k++) {
                    arr[k][j][i] = Integer.parseInt(st.nextToken());
                    if(arr[k][j][i]==1){
                        q.offer(new Node(k,j,i));
                    }
                    if(arr[k][j][i]==0){
                        cnt++;
                    }
                }
            }
        }
        if(cnt==0){
            System.out.println(0);
            System.exit(0);
        }
        while(!q.isEmpty()){
            Node now = q.poll();
            int x = now.x;
            int y = now.y;
            int z = now.z;
            for (int i = 0; i < 6; i++) {
                int nx = x +dx[i];
                int ny = y +dy[i];
                int nz = z +dz[i];
                if(nx<1||nx>m||ny<1||ny>n||nz<1||nz>h){
                    continue;
                }
                if(arr[nx][ny][nz]==0&&(visit[nx][ny][nz]==0||visit[nx][ny][nz]>visit[x][y][z]+1)){
                    q.offer(new Node(nx,ny,nz));
                    visit[nx][ny][nz]=visit[x][y][z]+1;
                    cnt--;
                    turn = visit[nx][ny][nz];
                }
            }
        }
        if(cnt!=0){
            System.out.println(-1);
        }else{
            System.out.println(turn);
        }


    }
}
class Node{
    int x,y,z;

    public Node(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
