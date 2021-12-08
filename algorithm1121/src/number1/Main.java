package number1;

import java.util.Arrays;

public class Main {


    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] ans = sol.solution(4, 3, new int[][]{{1, 1, 2, 1}, {1, 2, 1, 3}, {1, 3, 2, 3}, {2, 2, 2, 3}, {2, 2, 3, 2}, {2, 3, 3, 3}, {3, 2, 3, 3}, {3, 2, 4, 2}, {4, 1, 4, 2}},
                new int[][]{{2, 2, 3, 1}, {1, 2, 4, 2},});
        for (int a :
                ans) {
            System.out.println(a);
        }
    }

}

class Solution {
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static boolean[][][] map;

    public int[] solution(int rows, int columns, int[][] connections, int[][] queries) {
        int[] answer = new int[queries.length];
        Arrays.fill(answer, 0);
        map = new boolean[rows + 1][columns + 1][4];
        for (int[] connection : connections) {
            int ax = connection[0];
            int ay = connection[1];
            int bx = connection[2];
            int by = connection[3];
            int tx = ax - bx;
            int ty = ay - by;
            for (int j = 0; j < 4; j++) {
                if (tx == dx[j] && ty == dy[j]) map[ax][ay][j] = true;
                if (-(tx) == dx[j] && -(ty) == dy[j]) map[bx][by][j] = true;
            }
        }
        for (int k = 0; k < queries.length; k++) {
            int sx = Math.min(queries[k][0], queries[k][2]);
            int sy = Math.min(queries[k][1], queries[k][3]);
            int ex = Math.max(queries[k][0], queries[k][2]);
            int ey = Math.max(queries[k][1], queries[k][3]);
            for (int i = sy; i <= ey; i++) {
                if (map[sx][i][0]) {
                    answer[k]++;
                    map[sx][i][0] = false;
                    if (sx + dx[0] <=rows) {
                        map[sx + dx[0]][i + dy[0]][1] = false;

                    }
                }
                if (map[ex][i][1]) {
                    answer[k]++;
                    map[ex][i][1] = false;
                    if (ex + dx[1] >0) {
                        map[ex + dx[1]][i + dy[1]][0] = false;
                    }
                }
            }
            for (int i = sx; i <= ex; i++) {
                if (map[i][sy][2]) {
                    answer[k]++;
                    map[i][sy][2] = false;
                    if (sy + dy[2] <=columns) {
                        map[i + dx[2]][sy + dy[2]][3] = false;
                    }
                }
                if (map[i][ey][3]) {
                    answer[k]++;
                    map[i][ey][3] = false;
                    if (ey + dy[3] >0) {
                        map[i + dx[3]][ey + dy[3]][2] = false;
                    }
                }
            }
        }

        return answer;
    }
}