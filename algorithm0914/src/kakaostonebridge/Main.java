package kakaostonebridge;

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println(sol.solution(new int[]{2, 4, 5, 3, 2, 1, 4, 2, 5, 1},3));
    }
}

class Solution {
    public int solution(int[] stones, int k) {
        int answer = 0;

        int l = 0;
        int r = 200000000;

        while (l < r) {

            int mid = (l + r+1) / 2;
            if (pos(stones,k,mid)) {
                l = mid;
            } else {
                r = mid - 1;
            }

        }
         answer =l;

        return answer;
    }

    static boolean pos(int[] stones, int k, int mid){
        int cnt =0;
        for (int stone :
                stones) {
            if(stone-mid<0){
                cnt++;
            }else{
                cnt =0;
            }
            if(cnt==k) return false;
        }
        return true;
    }
}