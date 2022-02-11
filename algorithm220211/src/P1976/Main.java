package P1976;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] group;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        group = new int[n];
        for (int i = 0; i < n; i++) {
            group[i] = i;
        }
        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = i+1; j <n; j++) {
                if(input[j].equals("1")){
                    union(i,j);
                }
            }
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        int prev = find(Integer.parseInt(st.nextToken())-1);
        for (int i = 0; i < m-1; i++) {
            if(prev!=find(Integer.parseInt(st.nextToken())-1)){
                System.out.println("NO");
                System.exit(0);
            }
        }
        System.out.println("YES");

    }

    static int find(int a) {
        if(group[a]==a) return a;
        return group[a]=find(group[a]);
    }

    static void union(int a, int b) {
        int repA = find(a);
        int repB = find(b);
        group[repA] = repB;
    }
}
