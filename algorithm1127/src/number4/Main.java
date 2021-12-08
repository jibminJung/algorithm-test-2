package number4;

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();

        sol.solution(new int[][]{{0,2,1,3},{1,2,2,5},{3,3,4,4},{4,1,6,3},{1,6,5,7},{5,5,7,6},{5,8,6,10}});
    }
}

class Solution {
    static int maxX = 0;
    static int maxY = 0;
    static int[][] map = new int[20][20];

    public String[] solution(int[][] rectangles) {
        String[] answer = {};

        for (int i = 1; i < rectangles.length + 1; i++) {
            int[] rectangle = rectangles[i-1];
            fillRectangleMap(rectangle,i);
            if (maxX < rectangles[i-1][2]) maxX = rectangles[i-1][2];
            if (maxY < rectangles[i-1][3]) maxY = rectangles[i-1][3];
        }
        for (int x = 0; x < maxX; x++) {
            System.out.println("");
            for (int y = 0; y < maxY; y++) {
                System.out.print(map[x][y] + " ");
            }
        }
        System.out.println(" ");
        for (int i = 0; i < maxX; i++) {
            for (int j = 0; j < maxY; j++) {
                if (map[i][j] != 0) {
                    int target = map[i][j];
                    System.out.println(target);
                    int[] rectangle = rectangles[map[i][j]];
                    int dif = rectangle[1] - findMaxHeight(rectangle);
                    fillRectangleMap(rectangle,0);
                    rectangle[1] -= dif;
                    rectangle[3] -= dif;
                    fillRectangleMap(rectangle,target);

                    for (int x = 0; x < maxX; x++) {
                        System.out.println("");
                        for (int y = 0; y < maxY; y++) {
                            System.out.print(map[x][y] + " ");
                        }
                    }
                    System.out.println(" ");

                }
            }
        }
        return answer;
    }
    void fillRectangleMap(int[] rectangle, int target){
        for (int a = rectangle[0]; a < rectangle[2]; a++) {
            for (int b = rectangle[1]; b < rectangle[3]; b++) {
                map[a][b] = target;
            }
        }
    }

    int findMaxHeight(int[] rectangle){//한 사각형 밑의 가장 큰 높이 리턴
        int leftDownX = rectangle[0];
        int leftDownY = rectangle[1];
        int rightUpX = rectangle[2];
        int rightUpY = rectangle[3];
        int maxHeight =0;
        for (int i = leftDownX; i < rightUpX; i++) {
            for (int j = leftDownY-1; j >=0; j++) {
                if(map[i][j]!=0) return j;
            }
        }
        return 0;

    }

    boolean check(int[] rectangle, int i, int j){
        int leftDownX = rectangle[0];
        int leftDownY = rectangle[1];
        int rightUpX = rectangle[2];
        int rightUpY = rectangle[3];
        if(i>=leftDownX&&i<=rightUpX&&j>=leftDownY&&j<=rightUpY){
            return true;
        }
        return false;
    }
}