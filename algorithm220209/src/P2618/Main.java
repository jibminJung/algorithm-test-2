package P2618;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n,w;
    static Event[] events;
    static int[][] dp;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        w = Integer.parseInt(br.readLine());
        events= new Event[w+1];
        dp = new int[w+1][w+1];
        for (int i = 1; i < w + 1; i++) {
            st = new StringTokenizer(br.readLine());
            events[i] = new Event(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
        }
        for (int i = 0; i < w + 1; i++) {
            for (int j = 0; j < w + 1; j++) {
                dp[i][j] = -1;
            }
        }
        sb.append(minimumMoveAfterThis(0,0)).append('\n');
        trace(0,0,dp[0][0]);
        System.out.println(sb);
    }
    static int minimumMoveAfterThis(int pol1,int pol2){
        if(pol1==w||pol2==w){
            return dp[pol1][pol2]= 0;
        }
        if(dp[pol1][pol2]!=-1){
            return dp[pol1][pol2];
        }
        int progress = Math.max(pol1,pol2);

        int pol1Go = minimumMoveAfterThis(progress+1,pol2) + getDist(1,pol1,progress+1);
        int pol2Go = minimumMoveAfterThis(pol1,progress+1)+ getDist(2,pol2,progress+1);
        return dp[pol1][pol2] = Math.min(pol1Go,pol2Go);
    }
    static int getDist(int pol,int from,int to){
        if(from==0){
            if(pol==1){
                return Math.abs(events[to].x-1) + Math.abs(events[to].y-1);
            }else{
                return Math.abs(events[to].x-n) + Math.abs(events[to].y-n);
            }
        }
        return Math.abs(events[to].x-events[from].x) + Math.abs(events[to].y-events[from].y);
    }
    static void trace(int pol1, int pol2, int cost){
        int progress = Math.max(pol1,pol2);
        if(progress>=w) return;
        if(dp[progress+1][pol2]==cost-getDist(1,pol1,progress+1)){
            sb.append(1).append('\n');
            trace(progress+1,pol2,cost-getDist(1,pol1,progress+1));
        }else if(dp[pol1][progress+1]==cost-getDist(2,pol2,progress+1)){
            sb.append(2).append('\n');
            trace(pol1,progress+1,cost-getDist(2,pol2,progress+1));
        }
    }
}
class Event{
    int x;
    int y;

    public Event(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
