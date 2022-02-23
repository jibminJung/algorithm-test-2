package P3665;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int tc = Integer.parseInt(br.readLine());
        while (tc-- > 0) {
            int n = Integer.parseInt(br.readLine());
            ArrayList<HashSet<Integer>> list = new ArrayList<>();
            int[] inDegree = new int[n];
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                list.add(new HashSet<>());
            }
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken())-1;
                inDegree[arr[i]] = i;
                for (int j = i - 1; j >= 0; j--) {
                    list.get(arr[j]).add(arr[i]);
                }
            }
            int m = Integer.parseInt(br.readLine());
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int first = Integer.parseInt(st.nextToken())-1;
                int second = Integer.parseInt(st.nextToken())-1;
                if (list.get(first).contains(second)) {
                    list.get(second).add(first);
                    list.get(first).remove(second);
                    inDegree[second]--;
                    inDegree[first]++;
                } else {
                    list.get(second).remove(first);
                    list.get(first).add(second);
                    inDegree[second]++;
                    inDegree[first]--;
                }
            }
            Queue<Integer> q = new LinkedList<>();
            Queue<Integer> printQ = new LinkedList<>();
            for (int i = 0; i < n; i++) {
                if (inDegree[i] == 0) {
                    q.offer(i);
                }
            }
            boolean flag = false;
            if (q.size() > 1) {
                sb.append("?");
                flag = true;
            }
            if (flag) continue;
            for (int i = 0; i < n; i++) {
                if(q.isEmpty()){
                    flag=true;
                    sb.append("IMPOSSIBLE\n");
                    break;
                }
                if (q.size() > 1) {
                    flag = true;
                    sb.append("?");
                    break;
                }
                int now = q.poll();
                printQ.offer(now+1);
                for (int next :
                        list.get(now)) {
                    inDegree[next]--;
                    if(inDegree[next]==0) q.offer(next);
                }
            }
            if(flag)continue;
            while(!printQ.isEmpty()){
                sb.append(printQ.poll()).append(' ');
            }
            sb.append('\n');

        }
        System.out.println(sb);
    }
}
