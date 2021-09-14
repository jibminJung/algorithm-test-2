package kakaopractice;

import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args){

    }
}
class Solution {
    public int solution(String[] lines) {
        ArrayList<float[]> arr = new ArrayList<>();
        for (String line : lines) {
            arr.add(convert(line));
        }

        Collections.sort(arr, (o1, o2) -> {
            if(o1[1]!=o2[1]){
                if(o1[1]>o2[1]){
                    return 1;
                }else{
                    return -1;
                }
            }else{
                if(o1[0]>o2[0]){
                    return 1;
                }else{
                    return -1;
                }
            }
        });
        int max =0;
        int size = arr.size();
        for (int i = 0; i < size; i++) {
            float[] current = arr.get(i);
            float range = current[1] + (float)0.999;
            int cnt =0;
            for (int j = i; j < size; j++) {
                if(arr.get(j)[0]<=range){
                    cnt++;
                }else{
                    break;
                }
            }
            max= Math.max(max,cnt);
        }

        return max;
    }
    static float[] convert(String x) {
        String[] temp = x.split(" ");
        int h =Integer.parseInt(temp[1].substring(0, 2));
        int m =Integer.parseInt(temp[1].substring(3, 5));
        float s = Float.parseFloat(temp[1].substring(6,12));
        float p = Float.parseFloat(temp[2].replace("s",""));

        float[] result = new float[2];
        result[1] = (float)(h*60*60)+(float)(m*60)+s;
        result[0] = result[1]-p+(float)(0.001);
        return result;
    }
}