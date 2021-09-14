package kakao4;

public class Main {

    public static void main(String[] args) {
        Solution.backtrack(10, 10, new int[11], new int[]{0,0,0,0,0,0,0,0,3,4,3});
    }
}

class Solution {
    static int max = 0;
    static int[] maxArray = new int[11];

    public int[] solution(int n, int[] info) {
        int[] ryan = new int[11];
        backtrack(n, 10, ryan, info);
        int ncnt =0;
        for (int i = 0; i < maxArray.length; i++) {
            ncnt += maxArray[i];
        }
        if(ncnt ==0){
            return new int[]{-1};
        }
        if(ncnt<n){
            maxArray[10] += (n-ncnt);
        }

        return maxArray;
    }


    static void backtrack(int n, int depth, int[] ryan, int[] info) {
        if (depth < 0) {
            int cnt = 0;
            int apeach = 0;
            for (int i = 0; i < ryan.length; i++) {
                if(ryan[i]==0&&info[i]==00) continue;
                if (ryan[i] > info[i]) {
                    cnt += (10 - i);
                } else {
                    apeach += (10 - i);
                }

            }
            if (cnt > apeach && cnt > max) {
                max = cnt;
                for (int i = 0; i < ryan.length; i++) {
                    maxArray[i] = ryan[i];
                }
            }
            return;
        }


        int i = depth;
        if (n > info[i]) {
            ryan[i] = info[i] + 1;
            backtrack(n - ryan[i], depth - 1, ryan, info);
            ryan[i] = 0;
        }
            ryan[i]=0;
        backtrack(n, depth - 1, ryan, info);
        ryan[i] = 0;


    }

}