package alice1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int k = 1;k<=tc;k++){
            int n = Integer.parseInt(br.readLine());
            int score = 0;
            ArrayList<Integer> odd = new ArrayList<>();
            ArrayList<Integer> even = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                int temp = Integer.parseInt(st.nextToken());
                if(temp%2==0){
                    even.add(temp);
                }else{
                    odd.add(temp);
                }
            }
            Collections.sort(odd, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2-o1;
                }
            });
            Collections.sort(even, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2-o1;
                }
            });
            int os = odd.size();
            int es = even.size();
            if(os%2==1){
                int big = Math.max(odd.get(os/2),even.get(es/2));
                if(big%2==1){
                    score+=even.get(es/2);
                    even.remove(es/2);
                }else{
                    score += odd.get(os/2);
                    odd.remove(os/2);
                }
                os--;
                es--;
            }
            for (int i = 0; i < os/2; i++) {
                score += odd.get(i);
            }
            for (int i = 0; i < es/2; i++) {
                score += even.get(i);
            }
            sb.append('#').append(k).append(' ').append(score).append('\n');
        }
        System.out.println(sb);
    }

}
