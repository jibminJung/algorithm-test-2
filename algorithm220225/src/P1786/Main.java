package P1786;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] text = br.readLine().toCharArray();
        char[] pattern = br.readLine().toCharArray();
        int[] lps = makeLpsTable(pattern);

        search(text, pattern, lps);

    }

    private static void search(char[] text, char[] pattern, int[] lps) {
        int i = 0;
        int j = 0;
        int cnt = 0;
        StringBuilder sb = new StringBuilder();
        while (i < text.length) {
            while (j > 0 && text[i] != pattern[j]) {
                j = lps[j - 1];
            }
            if (text[i] == pattern[j]) {
                if (j == pattern.length - 1) {
                    sb.append(i - pattern.length + 2).append(' ');
                    cnt++;
                    j = lps[j];
                } else {
                    j++;
                }
            }
            i++;
        }
        System.out.println(cnt);
        System.out.println(sb);
    }

    private static int[] makeLpsTable(char[] pattern) {
        int[] lps = new int[pattern.length];
        int j =0;
        for (int i = 1; i < pattern.length; i++) {
            while (j>0&&pattern[j]!=pattern[i]){
                j=lps[j-1];
            }
            if(pattern[j]==pattern[i]){
                lps[i] = ++j;
            }
        }
        return lps;
    }
}
