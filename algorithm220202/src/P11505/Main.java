package P11505;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int s=1;
    static long[] tree;
    static final int MOD = 1000000007;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int i = m+k;
        while(s<n){
            s<<=1;
        }
        tree = new long[s*2];
        for (int j = 1; j < n + 1; j++) {
            int input = Integer.parseInt(br.readLine());
            update(1,1,s,j,input);
        }

        while (i-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if(a==1){
                update(1,1,s,b,c);
            }else{
                sb.append(query(1,1,s,b,c)).append('\n');
            }
        }
        System.out.println(sb);

    }

    static long update(int node,int left, int right, int index,int value){
        //left,right는 1~s의 범위
        //index와 left,right를 비교하고, left,right범위를 좁혀나간다.
        if(index<left||right<index){
            return tree[node];
        }
        if(left==right){
            return tree[node] = value;
        }
        int mid = (left+right)/2;
        return tree[node] = (update(node*2,left,mid,index,value) * update(node*2+1,mid+1,right,index,value))%MOD;

    }
    static long query(int node,int lRange, int rRange,int left,int right){
        if(rRange<left||right<lRange){//완전히 범위 밖
            return 1;
        }
        if(lRange>=left&&right>=rRange){
            return tree[node];
        }
        int mid = (lRange+rRange)/2;
        return (query(node*2,lRange,mid,left,right) * query(node*2+1,mid+1,rRange,left,right))%MOD;
    }
}
