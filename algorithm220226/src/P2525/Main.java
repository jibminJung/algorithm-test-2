package P2525;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int hh = Integer.parseInt(st.nextToken());
        int mm = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(br.readLine());
        int h = m/60;
        m %=60;
        if(mm+m>=60){
            h++;
        }
        mm = mm+m;
        hh = hh+h;
        hh %=24;
        mm %= 60;
        System.out.println(hh+" "+mm);
    }
}
