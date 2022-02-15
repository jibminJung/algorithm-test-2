package P20040;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n,m;
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] =i;
        }
        boolean flag = false;
        int i =1;
        for (; i < m+1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if(find(a)==find(b)){
                flag = true;
                break;
            }else{
                union(a,b);
            }
        }
        if(flag){
            System.out.println(i);
        }else{
            System.out.println(0);
        }


    }
    static void union(int a, int b){
        int repA = find(a);
        int repB = find(b);
        parent[repA] = repB;

    }
    static int find(int a){
        if(parent[a] ==a) return a;
        return parent[a] = find(parent[a]);
    }
}
