package dna;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        char t = st.nextToken().charAt(0);
        int n = Integer.parseInt(st.nextToken());
        char[] a = st.nextToken().toCharArray();
        int start = 0;
        int longest = 0;
        for (int i = 0; i < a.length; i++) {
            int cnt = -1;
            int len = 0;
            for (int j = i; j < a.length; j++) {
                if(a[j]==t) cnt++;
                if(cnt==n) break;
                len++;
                if(len>longest){
                    start = i+1;
                    longest = len;
                }

            }
        }
        System.out.println(start + " " + longest);

    }
}
