import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
}

class Solution {
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    static char[][] cake;
    static boolean[][] check;
    static HashSet<Integer> cut_rows = new HashSet<>();
    static HashSet<Integer> cut_columns = new HashSet<>();
    static int answer = -1;
    public int solution(String[] cakes, int[] cut_rows, int[] cut_columns) {
        cake = new char[cakes.length][cakes.length];
        check = new boolean[cakes.length][cakes.length];
        for (int i :
                cut_rows) {
            this.cut_rows.add(i);
        }
        for (int i :
                cut_columns) {
            this.cut_columns.add(i);
        }

        for (int i = 0; i < cakes.length; i++) {
            cake[i] = cakes[i].toCharArray();
        }
        for (int i = 0; i < cake.length; i++) {
            for (int j = 0; j < cake[i].length; j++) {
                if(!check[i][j]){
                    bfs(i,j);
                }
            }
        }

        return answer;
    }
    static void bfs(int i,int j){
        HashSet<Character> hs = new HashSet<>();
        Integer[] start = {i,j};
        Queue<Integer[]> q = new LinkedList<>();
        q.offer(start);
        while(!q.isEmpty()){
            Integer[] temp = q.poll();
            int tx = temp[0];
            int ty = temp[1];
            check[i][j] = true;
            hs.add(cake[tx][ty]);
            if(tx+1>=0&&tx+1<cake.length&&!check[tx+1][ty]&&!cut_columns.contains(tx+1)){
                q.offer(new Integer[]{tx+1,ty});
            }
            if(ty+1>=0&&ty+1< cake.length&&!check[tx][ty+1]&&!cut_rows.contains(ty+1)){
                q.offer(new Integer[]{tx,ty+1});
            }
        }
        answer = Math.max(answer,hs.size());

    }
}