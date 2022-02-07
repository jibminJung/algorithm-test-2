package P12899;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] tree;
    static int s = 1;
    static final int maxValue = 2000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        getS();
        tree = new int[s * 2 + 1];
        StringTokenizer st;
        while (n-- > 0) {
            st = new StringTokenizer(br.readLine());
            int o = Integer.parseInt(st.nextToken());
            int number = Integer.parseInt(st.nextToken());
            if (o == 1) {
                update(number,true);
            } else {
                int ans = query(1,number);
                sb.append(ans).append('\n');
                update(ans,false);
            }
        }
        System.out.println(sb);
    }

    static void getS() {
        while (s < maxValue) {
            s <<= 1;
        }
    }

    static void update(int number, boolean isInput) {
        int index = s + number - 1;
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
