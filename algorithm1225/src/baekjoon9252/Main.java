package baekjoon9252;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String A = " "+br.readLine();
        String B = " "+br.readLine();

        int[][] arr= new int[A.length()][B.length()];
        for (int i = 0; i < A.length(); i++) {
            for (int j = 0; j < B.length(); j++) {
                if(i==0||j==0){
                    arr[i][j]=0;
                }else if(A.charAt(i)==B.charAt(j)){
                    arr[i][j]=arr[i-1][j-1]+1;
                }else{
                    arr[i][j]=Math.max(arr[i][j-1],arr[i-1][j]);
                }
            }
        }
        int maxLength = arr[A.length()-1][B.length()-1];

        StringBuilder sb = new StringBuilder();
        for (int i = A.length()-1,j= B.length()-1; arr[i][j]>0; ) {
            if(arr[i][j]==arr[i][j-1]){
                j--;
            }else if(arr[i][j]==arr[i-1][j]){
                i--;
            }else{
                sb.append(A.charAt(i));
                i--;
                j--;
            }
        }
        System.out.println(maxLength);
        if(maxLength!=0){
            System.out.println(sb.reverse());
        }

    }
}
