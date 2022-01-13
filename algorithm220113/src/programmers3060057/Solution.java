package programmers3060057;

import java.util.Stack;

public class Solution {
    public int solution(String s) {
        int answer = Integer.MAX_VALUE;

        for (int i = 1; i <= s.length(); i++) {

            Stack<String> strs = new Stack<>();
            Stack<Integer> count = new Stack<>();

            int length = 0;

            for (int j = 0; j+i <= s.length(); j +=i) {
                String part = s.substring(j,j+i);
                if(strs.isEmpty()||!strs.peek().equals(part)){
                    strs.push(part);
                    count.push(1);
                }else{
                    System.out.println("hit!");
                    count.push(count.pop()+1);
                }
            }

            if(s.length()%i!=0){
                length += s.length()%i;
            }
            System.out.println("strs.size() = " + strs.size());

            while(!strs.isEmpty()){
                String temp = strs.pop();
                int dup = count.pop();
                if (dup==1){
                    length+= temp.length();
                }else{
                    length += String.valueOf(dup).length() + temp.length();
                }
            }
            System.out.println("i = " + i);
            System.out.println("length = " + length);
            answer = Math.min(length,answer);
        }
        return answer;
    }
}
class Main{
    public static void main(String[] args) {
        Solution sol = new Solution();
        int arr = sol.solution("ababcdcdababcdcd");
        System.out.println(arr);
    }
}