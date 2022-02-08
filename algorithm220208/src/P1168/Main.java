package P1168;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken())-1;
        int index = 0;
        ArrayList<Integer> arr= new ArrayList<>();
        for (int i = 1; i < n + 1; i++) {
            arr.add(i);
        }
        sb.append('<');
        for (int i = 0; i < n-1; i++) {
            index += k;
            if(index>=arr.size()){
                index %= arr.size();
            }
            sb.append(arr.remove(index)).append(", ");
        }
        sb.append(arr.remove(0)).append('>');
        System.out.println(sb);

    }
}
