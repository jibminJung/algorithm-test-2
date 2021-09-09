package grid;

import java.util.ArrayList;
import java.util.Arrays;

public class Solution {
    static Grid[][] map;
    static int xlen;
    static int ylen;
    static int maxlen = 0;
    static int outs=0;

    int[] solution(String[] grid) {
        xlen = grid.length;
        ylen = grid[0].length();
        outs=xlen*ylen*4;
        map = new Grid[xlen][ylen];
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = new Grid(grid[i].charAt(j), i, j);
            }
        }
        while (outs>0) {
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    for (int k = 0; k < 4; k++) {
                        if(!map[i][j].out[k]){
                            traverse(map[i][j],(k+map[i][j].turn+2)%4,0);
                            if(maxlen!=0){
                                arr.add(maxlen);
                                maxlen=0;
                            }
                            resetGrids();
                        }
                    }
                }

            }
        }

        int[] answer = new int[arr.size()];
        for (int i =0;i<answer.length;i++) {
            answer[i] = arr.get(i);
        }
        Arrays.sort(answer);
        return answer;


    }

    static void traverse(Grid grid, int inD, int count) {

        int out = ((inD + grid.turn) + 2) % 4;

        if (!grid.visit && grid.out[out]) {
            maxlen = 0;
            return;
        }
        if (grid.visit && grid.out[out]) {
            maxlen = count - grid.firstTouch;
            return;
        }
        if(!grid.visit){
            grid.firstTouch = count;
        }
        grid.visit = true;
        grid.out[out] = true;
        outs--;

        int newIn = (inD + grid.turn+4) % 4;
        if (newIn == 0) {
            traverse(map[(grid.x + 1) % xlen][grid.y], newIn, count + 1);
        } else if (newIn == 1) {
            traverse(map[grid.x][(grid.y + 1) % ylen], newIn, count + 1);
        } else if (newIn == 2) {
            traverse(map[(grid.x-1+xlen)%xlen][grid.y], newIn, count + 1);
        } else if (newIn == 3) {
            traverse(map[grid.x][(grid.y-1+ylen)%ylen], newIn, count + 1);
        }
    }

    static void resetGrids() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j].visit = false;
            }

        }
    }



}
class Grid {
    int turn = 0;
    int x = 0;
    int y = 0;
    int firstTouch = 0;
    boolean[] out = new boolean[4];
    boolean visit = false;

    Grid(char c, int x, int y) {
        if (c == 'S') {
            turn = 0;
        } else if (c == 'R') {
            turn = 1;
        } else {
            turn = -1;
        }

        this.x = x;
        this.y = y;
    }
}