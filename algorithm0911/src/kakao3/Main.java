package kakao3;

import java.util.Arrays;
import java.util.HashMap;

public class Main {

    public static void main(String[] args){
        System.out.println(Solution.timeBeforeEnd("05:34"));
        System.out.println(Solution.timeBeforeEnd("18:59"));
    }
}
class Solution {
    public int[] solution(int[] fees, String[] records) {


        HashMap<Integer,Integer> hm = new HashMap<>();
        for (String record :
                records) {
            String[] info = record.split(" ");
            int time = timeBeforeEnd(info[0]);
            int car = Integer.parseInt(info[1]);
            if(info[2].equals("IN")){//coming in..
                hm.put(car,hm.getOrDefault(car,0)+time);
            }else {
                hm.put(car,hm.get(car)-time);
            }
        }

        Integer[] orderedKeySet = hm.keySet().toArray(new Integer[0]);
        Arrays.sort(orderedKeySet);

        int[] answer = new int[orderedKeySet.length];
        for (int i=0;i<orderedKeySet.length;i++) {
            int usedTime = hm.get(orderedKeySet[i]);

            if(usedTime<=fees[0]){
                answer[i] = fees[1];
            }else{
                int overTime = usedTime - fees[0];
                answer[i] = fees[1] + (int)(Math.ceil((double) (overTime)/(double) (fees[2])))* fees[3];
            }

        }


        return answer;
    }


    static int timeBeforeEnd(String record){
        String[] time = record.split(":");
        int h = Integer.parseInt(time[0]);
        int m = Integer.parseInt(time[1]);

        return 1439 - ((h*60) + m);

    }


}