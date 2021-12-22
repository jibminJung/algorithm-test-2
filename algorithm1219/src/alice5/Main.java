package alice5;

import javax.sql.rowset.serial.SerialStruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static final int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int tc = Integer.parseInt(br.readLine());
        for (int k = 1; k <= tc; k++) {
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st;
            int[][] map = new int[n + 1][n + 1];
            for (int i = 1; i < n + 1; i++) {
                Arrays.fill(map[i], INF);
                map[i][i] = 0;
            }
            for (int i = 0; i < n - 1; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                map[a][b] = c;
                map[b][a] = c;
            }
            for (int x = 1; x < n + 1; x++) {
                for (int y = 1; y < n + 1; y++) {
                    for (int z = 1; z < n + 1; z++) {
                        if (y == z || y == x || z == x) continue;
                        map[y][z] = Math.min(map[y][z], map[y][x] + map[x][z]);
                    }
                }
            }
            int minimum =  INF;
            int count=0;
            for (int i = 1; i < n+1; i++) {
                int temp = 0;
                for (int j = 1; j < n+1; j++) {
                    temp += map[j][i];
                }
                if(temp<minimum){
                    minimum=temp;
                    count=1;
                }else if(temp==minimum){
                    count++;
                }
            }
            sb.append('#').append(k).append(' ').append(count).append(' ').append(minimum).append('\n');

        }
        System.out.println(sb);
    }

}
