package alice4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static final int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Queue<Integer> q = new LinkedList<>();
        StringBuilder sb= new StringBuilder();
        int tc = Integer.parseInt(br.readLine());
        for (int k = 1; k <= tc; k++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            ArrayList<ArrayList<Integer>> arr = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                arr.add(new ArrayList<Integer>());
            }
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                arr.get(from).add(to);
                arr.get(to).add(from);
            }
            int[][] map = new int[n + 1][2];
            for (int i = 0; i < map.length; i++) {
                map[i][0] = INF;
            }
            q.offer(a);
            q.offer(b);
            map[a][0] = 1;
            map[b][0] = 1;
            map[a][1] = 1;
            map[b][1] = 2;

            HashSet<Integer> collide = new HashSet<>();

            while (!q.isEmpty()) {
                int temp = q.poll();
                for (int to :
                        arr.get(temp)) {
                    int time = map[temp][0] + 1;
                    if (map[to][0] > time) {
                        map[to][0] = time;
                        map[to][1] = map[temp][1];
                        q.offer(to);
                    } else if (map[to][0] == time) {
                        q.remove(to);
                        map[to][1] = 0;
                        collide.add(to);
                    }
                }
            }

            int confront = collide.size();
            int neutralConfront = -1;
            int at = 0;
            int bt = 0;

            for (int i = 0; i < map.length; i++) {
                if (map[i][1] == 1) {
                    at++;
                }
                if (map[i][1] == 2) {
                    bt++;
                }
                if (map[i][1] == 0) {
                    neutralConfront++;
                }
            }
            int extra;
            if(at>n/2){
                extra =0;
            }else if(at+neutralConfront>n/2){
                extra = at+neutralConfront-bt+1;
            }else{
                extra = -1;
            }
            sb.append('#').append(k).append(' ').append(confront).append(' ').append(extra).append('\n');

        }

        System.out.println(sb);
    }
}
