package programmers3077484;

class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        int miss =0;
        int cnt = 0;
        for (int i = 0; i < lottos.length; i++) {
            if(lottos[i]==0){
                miss++;
                continue;
            }
            for (int j = 0; j < win_nums.length; j++) {
                if(lottos[i]==win_nums[j]){
                    cnt++;
                }
            }
        }
        int max = Math.min(7 - (cnt + miss), 6);
        int min = Math.min(7 - cnt, 6);

        return new int[]{max,min};
    }
}
class Main{
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] arr = sol.solution(new int[]{44, 1, 0, 0, 31, 25},new int[]{31, 10, 45, 1, 6, 19});
        System.out.println(arr[0]);
        System.out.println(arr[1]);
    }
}