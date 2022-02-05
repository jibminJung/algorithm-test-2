package P2357;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] arr;
    static int s = 1;
    static final int INF = Integer.MAX_VALUE;
    static final int MINF = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        arr = new int[n + 1];
        while (s < n) {
            s <<= 1;
        }
        MinTree minTree = new MinTree(s);
        MaxTree maxTree = new MaxTree(s);
        for (int i = 1; i < n + 1; i++) {
            int data = Integer.parseInt(br.readLine());
            minTree.input(1,1,s,i,data);
            maxTree.input(1,1,s,i,data);
        }
        StringBuilder sb= new StringBuilder();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            sb.append(minTree.query(1,1,s,from,to)).append(' ');
            sb.append(maxTree.query(1,1,s,from,to)).append('\n');
        }
        System.out.println(sb);

    }
}

class MinTree{
    int[] tree;
    public MinTree(int s) {
        tree= new int[s*2];
        Arrays.fill(tree,Main.INF);
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
}
class MaxTree{
    int[] tree;
    public MaxTree(int s) {
        tree= new int[s*2];
        Arrays.fill(tree,Main.MINF);
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