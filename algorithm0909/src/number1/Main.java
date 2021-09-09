package number1;

public class Main {
    public static void main(String[] args) {
        System.out.println(Solution.solution(new int[]{1,2,3,4}));
    }
}

class Solution {
    static public int solution(int[] numbers) {
        boolean[] arr = new boolean[10];
        for (int e :
                numbers) {
            arr[e] = true;
        }
        int answer = 0;
        for (int i=0; i<arr.length;i++) {
            if (!arr[i]) {
                answer += i;
            }
        }
        return answer;
    }


}