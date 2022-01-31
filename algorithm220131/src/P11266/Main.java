package P11266;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int V, E;
    static ArrayList<ArrayList<Integer>> arr = new ArrayList<>();
    static boolean[] isCutVer;
    static int[] orders;
    static int order;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        isCutVer = new boolean[V + 1];
        orders = new int[V + 1];
        for (int i = 0; i < V + 1; i++) {
            arr.add(new ArrayList<>());
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            arr.get(from).add(to);
            arr.get(to).add(from);
        }
        order = 1;
        for (int i = 1; i < V + 1; i++) {
            if (orders[i] == 0) {
                dfs(i, true);
            }
        }
        int cnt = 0;
        StringBuilder sb= new StringBuilder();
        for (int i = 1; i < isCutVer.length; i++) {
            if(isCutVer[i]) {
                cnt++;
                sb.append(i).append(" ");
            }
        }
        System.out.println(cnt);
        System.out.println(sb);

    }

    static int dfs(int node, boolean isRoot) {//만나는 최소 order를 리턴하도록
        orders[node] = order++;
        int rtn = orders[node];
        int child = 0;

        for (int i = 0; i < arr.get(node).size(); i++) {
            int to = arr.get(node).get(i);
            if (orders[to] == 0) {
                child++;
                int low = dfs(to,false);
                if (!isRoot && low >= orders[node]) {
                    isCutVer[node] = true;
                }
                rtn = Math.min(low,rtn);
            } else {
                rtn = Math.min(rtn, orders[to]);
            }
        }
        if (isRoot && child > 1) {
            isCutVer[node] = true;
        }

        return rtn;
    }
}
