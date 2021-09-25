package puzzlepiece;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static void main(String[] args){
        //test case
        int[][] game_board = {{1,1,0,0,1,0},{0,0,1,0,1,0},{0,1,1,0,0,1},{1,1,0,1,1,1},{1,0,0,0,1,0},{0,1,1,1,0,0}};
        int[][] table = {{1,0,0,1,1,0},{1,0,1,0,1,0},{0,1,1,0,1,1},{0,0,1,0,0,0},{1,1,0,1,1,0},{0,1,0,0,0,0}};
        Solution solution = new Solution();
        System.out.println(solution.solution(game_board,table));
    }
}
class Solution{
    static int minx,miny;
    public int solution(int[][] game_board, int[][] table) {
        int answer = 0;
        int n = game_board.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                game_board[i][j] = game_board[i][j]==1?0:1;
            }
        }
        ArrayList<int[][]> blanks = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(game_board[i][j]==1){
                    int[][] blank = bfs(game_board,i,j);
                    blanks.add(blank);
                }
            }
        }

        for (int k = 0; k < 4; k++) {
            table = rotateNN(table);
            int[][] tempTable = new int[n][n];
            for (int i = 0; i < n; i++) {
                tempTable[i] = table[i].clone();
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if(tempTable[i][j] == 1){
                        int[][] piece = bfs(tempTable,i,j);
                        for (int l = 0; l < blanks.size(); l++) {
                            if(Arrays.deepEquals(blanks.get(l),piece)){
                                blanks.remove(l);
                                for (int m = 0; m < piece.length; m++) {
                                    for (int o = 0; o < piece[m].length;o++) {
                                        if(piece[m][o]==1){
                                            answer++;
                                            table[minx+m][miny+o] = 0;
                                        }
                                    }
                                }
                                break;
                            }
                        }
                    }
                }
            }
        }


        return answer;
    }

    int[][] rotateNN(int[][] table){
        int n = table.length;
        int[][] newTable = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                newTable[i][j] = table[n-1-j][i];
            }
        }
        return newTable;
    }
    int[][] bfs(int[][] board,int x,int y){
        Queue<Integer[]> q = new LinkedList<>();
        int[] dx = {1,-1,0,0};
        int[] dy = {0,0,1,-1};
        int minx = Integer.MAX_VALUE, miny = Integer.MAX_VALUE;
        int maxx = 0, maxy = 0;
        ArrayList<Integer[]> blocks = new ArrayList<>();
        q.offer(new Integer[]{x,y});
        blocks.add(new Integer[]{x,y});

        board[x][y] = 0;
        while(!q.isEmpty()){
            Integer[] temp = q.poll();
            int tx = temp[0];
            int ty = temp[1];
            minx = Math.min(minx,tx);
            miny = Math.min(miny,ty);
            maxx = Math.max(maxx,tx);
            maxy = Math.max(maxy,ty);
            for (int i = 0; i < 4; i++) {
                int ntx = tx+dx[i];
                int nty = ty+dy[i];
                if(ntx<0||nty<0||ntx>= board.length||nty>= board.length){
                    continue;
                }
                if(board[ntx][nty] == 1){
                    board[ntx][nty] = 0;
                    blocks.add(new Integer[]{ntx,nty});
                    q.offer(new Integer[]{ntx,nty});
                }
            }

        }

        return makePuzzlePiece(minx,miny,maxx,maxy,blocks);
    }

    int[][] makePuzzlePiece(int minx,int miny,int maxx,int maxy,ArrayList<Integer[]> blocks){
        int[][] newPiece = new int[maxx-minx+1][maxy-miny+1];
        for (Integer[] point :
                blocks) {
            newPiece[point[0]-minx][point[1]-miny] = 1;
        }
        Solution.minx = minx;
        Solution.miny = miny;
        return newPiece;
    }

}
