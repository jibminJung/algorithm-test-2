package number2;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] arr = sol.solution(3, 2, 5);
        for (int e : arr) {
            System.out.print(e+ " ");
        }
        System.out.println();
        arr = sol.solution(4, 7, 14);
        for (int e : arr) {
            System.out.print(e+ " ");
        }

    }
}

class Solution {
    public int[] solution(int n, long left, long right) {
        ArrayList<Integer> arr = new ArrayList<>();
        for (long i = left; i <= right; i++) {
            int temp = (int) (i / n);
            int t2 = (int) (i % n);
            if (t2 <= temp) {
                arr.add(temp + 1);
            } else {
                arr.add(t2 + 1);
            }
        }
        int[] answer = new int[arr.size()];
        int i = 0;
        for (int e :
                arr) {
            answer[i++] = e;
        }

        return answer;
    }
}
