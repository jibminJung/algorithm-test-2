package P11054;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int[] arr;
    static ArrayList<Integer> dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        arr = new int[n];
        int[] memo1 = new int[n];
        int[] memo2 = new int[n];
        int[] memo3 = new int[n];
        int[] memo4 = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        dp = new ArrayList<>();
        dp.add(arr[0]);
        memo1[0] = 1;
        memo3[0] = arr[0];
        for (int i = 1; i < n; i++) {
            if (dp.get(dp.size() - 1) < arr[i]) {
                dp.add(arr[i]);
            } else {
                int index = binarySearch(arr[i]);
                dp.remove(index);
                dp.add(index, arr[i]);
            }
            memo1[i] = dp.size();
            memo3[i] = dp.get(dp.size() - 1);
        }
        dp = new ArrayList<>();
        dp.add(arr[n - 1]);
        memo2[n - 1] = 1;
        memo4[n - 1] = arr[n - 1];
        for (int i = n - 1; i >= 0; i--) {
            if (dp.get(dp.size() - 1) < arr[i]) {
                dp.add(arr[i]);
            } else {
                int index = binarySearch(arr[i]);
                dp.remove(index);
                dp.add(index, arr[i]);
            }
            memo2[i] = dp.size();
            memo4[i] = dp.get(dp.size() - 1);
        }
        int answer = 1;
        for (int i = 0; i < n-1; i++) {
            if (memo3[i] != memo4[i+1]) {
                answer = Math.max(answer, memo1[i] + memo2[i+1]);
            }
        }
        System.out.println(answer);


    }

    static int binarySearch(int target) {
        int l = 0;
        int r = dp.size();
        while (l < r) {
            int mid = (l + r) / 2;
            if (dp.get(mid) < target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }
}
