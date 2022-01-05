package swexpertacademy13038;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
    static int[] day = new int[7];

    public static void main(String args[]) throws Exception {

        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            int target = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int classPerWeek = 0;

            for (int i = 0; i < day.length; i++) {
                day[i] = Integer.parseInt(st.nextToken());
                if (day[i] == 1) classPerWeek++;
            }

            int week = target%classPerWeek==0?(target/classPerWeek-1):(target/classPerWeek);
            target = target - (week * classPerWeek);

            int[] count = new int[7];
            for (int i = 0; i < count.length; i++) {
                int cnt = 0;
                int j = i;
                for (; j < i+7; j++) {
                    if (day[j%7] == 1) cnt++;
                    if (cnt == target) break;
                }
                count[i] = j-i+1;
            }
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < count.length; i++) {
                if (count[i] < min) min = count[i];
            }
            sb.append("#").append(tc).append(" ").append(week * 7 + min).append('\n');
        }
        System.out.println(sb);
    }
}