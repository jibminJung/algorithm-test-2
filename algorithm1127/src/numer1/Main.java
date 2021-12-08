package numer1;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Main {
}
class Solution {
    static Queue<Integer> fifo = new LinkedList <Integer>();
    static Stack<Integer> lifo = new Stack<>();
    public int[] solution(String[] record) {

        int lifoCost =0;
        int fifoCost=0;
        for (String info :
                record) {
            String[] parse = info.split(" ");
            if(parse[0].equals("P")){
                int price = Integer.parseInt(parse[1]);
                int amount = Integer.parseInt(parse[2]);
                for (int i = 0; i < amount; i++) {
                    fifo.offer(price);
                    lifo.push(price);
                }
            }else{
                int amount = Integer.parseInt(parse[2]);
                for (int i = 0; i < amount; i++) {
                    fifoCost += fifo.poll();
                    lifoCost +=lifo.pop();
                }
            }
        }
        int[] answer = {fifoCost,lifoCost};
        return answer;
    }
}
