package swexpertacademy13229;

import java.util.Scanner;
import java.util.HashMap;

class Solution
{
    static String[] day = {"SUN","MON","TUE","WED","THU","FRI","SAT"};
    public static void main(String args[]) throws Exception
    {

        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();

        StringBuilder sb = new StringBuilder();

        HashMap<String,Integer> hm = new HashMap<>();
        for (int i = 0; i < day.length; i++) {
            hm.put(day[i],i);
        }

        for(int test_case = 1; test_case <= T; test_case++)
        {

            String _day = sc.next();
            sb.append("#").append(test_case).append(" ").append(7-hm.get(_day)).append('\n');

        }
        System.out.print(sb);
    }
}