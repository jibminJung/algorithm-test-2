package n2tile;

public class n2tile {
    public static void main(String[] args){
        System.out.println(Solution.solution(4));
    }
}
class Solution {
    public static int solution(int n) {
        int[] temp = new int[n+3];
        /*
        when n is
        1 -> 1
        2 -> 2
        3 -> 3
        4 -> 5
        5 -> 8
        ...
         */
        temp[1]=1;
        temp[2]=2;
        for (int i = 3; i < n+1; i++) {
            temp[i] = (temp[i-1] + temp[i-2])%1000000007;
        }



        return temp[n];
    }
}
