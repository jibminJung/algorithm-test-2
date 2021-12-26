package baekjoon14003;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        ArrayList<Integer> list = new ArrayList<>();
        list.add(Integer.MIN_VALUE);
        int[] index = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < n; i++) {
            if (list.get(list.size() - 1) < arr[i]) {
                list.add(arr[i]);
                index[i] = list.size() - 1;
            } else {
                int l = 0;
                int r = list.size() - 1;
                while (l < r) {
                    int mid = (l + r) / 2;
                    if (list.get(mid) < arr[i]) {
                        l = mid + 1;
                    } else {
                        r = mid;
                    }
                }
                list.set(r, arr[i]);
                index[i] = r;
            }
        }
        int li = list.size()-1;
        System.out.println(li);
        Stack<Integer> stack = new Stack<>();
        for (int i = arr.length-1; i >= 0; i--) {
            if(index[i]==li){
                stack.push(arr[i]);
                li--;
            }
        }
        StringBuilder sb=  new StringBuilder();
        while (!stack.isEmpty()){
            sb.append(stack.pop()).append(' ');
        }
        System.out.println(sb);

    }
}
