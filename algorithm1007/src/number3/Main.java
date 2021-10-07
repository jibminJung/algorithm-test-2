package number3;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution(2,5,0,1,new int[][]{{3,2},{2,2},{1,1},{2,3},{0,1},{2,1}}));
    }
}

class Solution {
    static HashSet<Integer>[][] arr;

    public long solution(int n, int m, int x, int y, int[][] queries) {
        long answer = 0;
        arr = initHashSet(n, m);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Point p = new Point(i, j);
                ArrayList<Integer>[][] record = initArrList(n, m);
                for (int k = 0; k < queries.length; k++) {
                    int dir = queries[k][0];
                    int dx = queries[k][1];
                    if (arr[p.x][p.y].contains(k)) {
                        saveRecord(record, n, m);
                        record = null;
                        answer++;
                        break;
                    }
                    if (dir == 0) {
                        p.y = Math.max(p.y - dx, 0);
                        if (p.y == 0) {
                            for (int l = 0; l <= dx && p.y + l < m; l++) {
                                record[p.x][p.y + l].add(k);
                            }
                        } else {
                            record[p.x][p.y + dx].add(k);
                        }
                    } else if (dir == 1) {
                        p.y = Math.min(p.y + dx, m - 1);
                        if (p.y == m - 1) {
                            for (int l = 0; l <= dx && p.y - l >= 0; l++) {
                                record[p.x][p.y - l].add(k);
                            }
                        } else {
                            record[p.x][p.y - dx].add(k);
                        }
                    } else if (dir == 2) {
                        p.x = Math.max(p.x - dx, 0);
                        if (p.x == 0) {
                            for (int l = 0; l <= dx && p.x + l < n; l++) {
                                record[p.x + l][p.y].add(k);
                            }
                        } else {
                            record[p.x + dx][p.y].add(k);
                        }
                    } else if (dir == 3) {
                        p.x = Math.min(p.x + dx, n - 1);
                        if (p.x == n - 1) {
                            for (int l = 0; l <= dx && p.x - l >= 0; l++) {
                                record[p.x - l][p.y].add(k);
                            }
                        } else {
                            record[p.x - dx][p.y].add(k);
                        }
                    }

                }
                //if point equal goal after queries, save record
                if (p.x == x && p.y == y) {
                    saveRecord(record, n, m);
                    record = null;
                    answer++;
                }


            }
        }


        return answer;
    }

    private ArrayList<Integer>[][] initArrList(int n, int m) {
        ArrayList<Integer>[][] arr = new ArrayList[n][m];
        for (int p = 0; p < n; p++) {
            for (int q = 0; q < m; q++) {
                arr[p][q] = new ArrayList<>();
            }
        }
        return arr;
    }

    private HashSet<Integer>[][] initHashSet(int n, int m) {
        HashSet<Integer>[][] arr = new HashSet[n][m];
        for (int p = 0; p < n; p++) {
            for (int q = 0; q < m; q++) {
                arr[p][q] = new HashSet<>();
            }
        }
        return arr;
    }

    private void saveRecord(ArrayList<Integer>[][] record, int n, int m) {
        for (int r = 0; r < n; r++) {
            for (int q = 0; q < m; q++) {
                for (int e :
                        record[r][q]) {
                    arr[r][q].add(e);
                }
            }
        }
    }
}