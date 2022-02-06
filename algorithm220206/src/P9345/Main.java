package P9345;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n,k,s=1;
    static final int INF = Integer.MAX_VALUE;
    static final int MINF = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int tc = Integer.parseInt(br.readLine());
        while(tc-->0){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken())+1;
            k = Integer.parseInt(st.nextToken());
            getS();
            MinTree minTree = new MinTree(s);
            MaxTree maxTree = new MaxTree(s);
            minTree.init(1,1,s);
            maxTree.init(1,1,s);
            while(k-->0){
                st = new StringTokenizer(br.readLine());
                int q = Integer.parseInt(st.nextToken());
                int a = Integer.parseInt(st.nextToken())+1;
                int b = Integer.parseInt(st.nextToken())+1;
                if(q==0){//swap
                    int temp1 = minTree.valueByIndex(a);
                    int temp2 = minTree.valueByIndex(b);
                    minTree.input(1,1,s,a,temp2);
                    minTree.input(1,1,s,b,temp1);
                    maxTree.input(1,1,s,a,temp2);
                    maxTree.input(1,1,s,b,temp1);
                }else{//query
                    int min = minTree.query(1,1,s,a,b);
                    int max = maxTree.query(1,1,s,a,b);
                    if(a==min&&max==b){
                        sb.append("YES").append('\n');
                    }else{
                        sb.append("NO").append('\n');
                    }
                }
            }
        }
        System.out.println(sb);
    }
    static void getS(){
        s=1;
        while(s<n){
            s<<=1;
        }
    }

}

class MinTree{
    int[] tree;
    int s;
    public MinTree(int s) {
        tree= new int[s*2];
        Arrays.fill(tree,Main.INF);
        this.s =s;
    }
    int init(int node, int left, int right){
        if(left==right){
            return tree[node]= left;
        }
        int mid = (left+right)/2;
        int leftSide = init(node*2,left,mid);
        int rightSide = init(node*2+1,mid+1,right);
        return tree[node] = Math.min(leftSide,rightSide);
    }
    int input(int node, int left, int right, int targetIndex, int value) {
        if(left==right){
            if(left==targetIndex) tree[node] = value;
            return tree[node];
        }
        if(targetIndex<left||right<targetIndex){
            return tree[node];
        }
        int mid = (left+right)/2;
        int leftSide = input(node*2,left,mid,targetIndex,value);
        int rightSide = input(node*2+1,mid+1,right,targetIndex,value);
        return tree[node] = Math.min(leftSide,rightSide);
    }
    int query(int node, int start, int end, int left, int right){
        if(right<start||left>end){
            return Main.INF;
        }
        if(left<=start&&end<=right){
            return tree[node];
        }
        int mid = (start+end)/2;
        int leftSide = query(node*2,start,mid,left,right);
        int rightSide = query(node*2+1,mid+1,end,left,right);
        return Math.min(leftSide,rightSide);
    }
    int valueByIndex(int a){
        return tree[s+a-1];
    }
}
class MaxTree{
    int[] tree;
    int s;
    public MaxTree(int s) {
        tree= new int[s*2];
        Arrays.fill(tree,Main.MINF);
        this.s = s;
    }
    int init(int node, int left, int right){
        if(left==right){
            return tree[node]= left;
        }
        int mid = (left+right)/2;
        int leftSide = init(node*2,left,mid);
        int rightSide = init(node*2+1,mid+1,right);
        return tree[node] = Math.max(leftSide,rightSide);
    }
    int input(int node, int left, int right, int targetIndex, int value) {
        if(left==right){
            if(left==targetIndex) tree[node] = value;
            return tree[node];
        }
        if(targetIndex<left||right<targetIndex){
            return tree[node];
        }
        int mid = (left+right)/2;
        int leftSide = input(node*2,left,mid,targetIndex,value);
        int rightSide = input(node*2+1,mid+1,right,targetIndex,value);
        return tree[node] = Math.max(leftSide,rightSide);
    }
    int query(int node, int start, int end, int left, int right){
        if(right<start||left>end){
            return Main.MINF;
        }
        if(left<=start&&end<=right){
            return tree[node];
        }
        int mid = (start+end)/2;
        int leftSide = query(node*2,start,mid,left,right);
        int rightSide = query(node*2+1,mid+1,end,left,right);
        return Math.max(leftSide,rightSide);
    }
}
