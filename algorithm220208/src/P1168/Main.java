package P1168;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int[] tree;
    static int n,s=1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        getS();
        tree = new int[s*2];
        int index = 1;
        for (int i = 1; i < n + 1; i++) {
            update(i,true);
        }
        sb.append('<');
        for (int i = 0; i < n-1; i++) {
            index += k-1;
            if(index%tree[1]==0){
                index = tree[1] ;
            }else if (index > tree[1]){
                index %= tree[1];
            }
            int temp = query(1,index);
            sb.append(temp).append(", ");
            update(temp,false);
        }
        sb.append(query(1,1)).append('>');
        System.out.println(sb);

    }

    static void getS() {
        while (s < n) {
            s <<= 1;
        }
    }

    static void update(int number, boolean isInput) {
        int index = s + number-1;
        int diff = isInput ? 1 : -1;
        while (index > 0) {
            tree[index] += diff;
            index /= 2;
        }
    }
    static int query(int node,int position){
        if(node>=s){
            return node-s+1;
        }
        int left = tree[node*2];
        if(position<=left){
            return query(node*2,position);
        }else{
            return query(node*2+1,position-left);
        }
    }
}
