package baekjoon1920;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

    static HashSet<Integer> hs = new HashSet<>();
    static ArrayList<Integer> arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            arr.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(arr);
        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            int target = Integer.parseInt(st.nextToken());
            if (hs.contains(target)||find(target)) {
                sb.append(1).append('\n');
            }else{
                sb.append(0).append('\n');
            }
        }
        System.out.println(sb);

    }

    static boolean find(int target) {
        int l = 0;
        int r = arr.size() - 1;
        while (l < r) {
            int mid = (l + r+1) / 2;
            if (arr.get(mid) > target) {
                r = mid-1;
            } else {
                l = mid;
            }
        }
        if (arr.get(l) == target) {
            hs.add(target);
            return true;
        }
        return false;
    }
}
