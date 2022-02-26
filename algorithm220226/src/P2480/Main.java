package P2480;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[3];
        int[] count = new int[7];
        int max = 0;
        for (int i = 0; i < 3; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max,arr[i]);
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(i==j)continue;
                if(arr[i]==arr[j]) {
                    count[arr[i]]++;
                }
            }
        }
        for (int i = 0; i < arr.length; i++) {
            if(count[arr[i]]==2){
                System.out.println(1000+(arr[i]*100));
                System.exit(0);
            }
            if(count[arr[i]]==6){
                System.out.println(10000+(arr[i]*1000));
                System.exit(0);
            }
        }
        System.out.println(max*100);

    }
}
