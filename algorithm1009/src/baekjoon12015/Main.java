package baekjoon12015;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int cnt = 0;
        int[] lis = new int[n+1];
        lis[cnt] = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            int temp = arr[i];
            if(temp>lis[cnt]){
                lis[++cnt] = temp;
            }else{
                lis[bs(lis,cnt,temp)] = temp;
            }
        }
        System.out.println(cnt);

    }
    static int bs(int[] lis,int r,int x){
        int l =0;
        while(l<r){
            int mid = (l+r)/2;
            if(lis[mid]<x){
                l = mid +1;
            }else{
                r = mid;
            }
        }
        return l;
    }

}
