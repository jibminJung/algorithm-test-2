import java.util.Arrays;
import java.util.Stack;

public class stonebridge {
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution(25, new int[]{2, 14, 11, 21, 17}, 2));
    }
}

class Solution {
    static int[] st_rocks;
    static int destination;
    public int solution(int distance, int[] rocks, int n) {
        st_rocks = rocks;
        destination = distance;
        Arrays.sort(st_rocks);
        int l = 0;
        int r = distance;
        while(l<r){
            int mid = (l+r+1)/2;
            if(pos(mid,n)){
                l=mid;
            }else{
                r = mid-1;
            }
        }

        return l;
    }

    static boolean pos(int x, int n) {
        int prev = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(prev);
        for (int i = 0; i < st_rocks.length; i++) {
            int interval = st_rocks[i] - prev;
            if(interval>=x){
                prev = st_rocks[i];
                stack.push(prev);
            }else{
                if(!(n-->0)){
                    return false;
                }
            }
        }

        while(destination-stack.pop()<x){
            if(!(n-->0)){
                return false;
            }
        }

        return true;
    }
}