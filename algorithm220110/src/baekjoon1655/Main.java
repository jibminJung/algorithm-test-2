package baekjoon1655;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb= new StringBuilder();
        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> small = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> big = new PriorityQueue<>();
        for (int i = 1; i <= n; i++) {
            int target = Integer.parseInt(br.readLine());
            if(small.isEmpty()||small.peek()>target){
                small.offer(target);
            }else{
                big.offer(target);
            }

            if(i%2==1){
                while(small.size()-big.size()!=1){
                    small.offer(big.poll());
                }
                sb.append(small.peek()).append('\n');
            }else{
                while(small.size()!= big.size()){
                    if(small.size()> big.size()){
                        big.offer(small.poll());
                    }else{
                        small.offer(big.poll());
                    }
                }
                sb.append(Math.min(small.peek(),big.peek())).append('\n');
            }
        }
        System.out.println(sb);
    }
}
