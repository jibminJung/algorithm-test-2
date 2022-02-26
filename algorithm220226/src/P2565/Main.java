package P2565;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<Integer> dp = new ArrayList<>();
    static ArrayList<Line> lines = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr= new int[n+1];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            lines.add(new Line(a,b));
        }
        Collections.sort(lines);
        int a=1;
        for (Iterator<Line> iterator = lines.iterator(); iterator.hasNext(); ) {
            Line next = iterator.next();
            arr[a++] = next.to;
        }
        dp.add(arr[1]);
        for (int i = 2; i < n + 1; i++) {
            if(arr[i]>dp.get(dp.size()-1)){
                dp.add(arr[i]);
            }else{
                int index = binarySearch(arr[i]);
                dp.remove(index);
                dp.add(index,arr[i]);
            }
        }
        System.out.println(n-dp.size());

    }
    static int binarySearch(int target){
        int l =0;
        int r = dp.size();
        while(l<r){
            int mid = (l+r)/2;
            if(dp.get(mid)>target){
                r = mid;
            }else{
                l = mid+1;
            }
        }
        return l;

    }
}
class Line implements Comparable<Line>{
    int from,to;

    public Line(int from, int to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public int compareTo(Line o) {
        return Integer.compare(this.from,o.from);
    }
}
