package minimumnumber;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args){

    }

}
class Solution {
    public int[] solution(int[] arr) {
        if(arr.length == 1) return new int[]{-1};
        int min = Integer.MAX_VALUE;
        int minIdx = -1;
        for(int i = 0; i<arr.length;i++){
            if(arr[i]<min){
                min = arr[i];
                minIdx = i;
            }
        }
        int[] answer = new int[arr.length-1];
        boolean after = false;
        for(int i=0;i<arr.length;i++){
            if(i==minIdx){
                after = true;
                continue;
            }
            if(!after){
                answer[i] = arr[i];
            }else{
                answer[i-1] = arr[i];
            }

        }

        return answer;
    }
}