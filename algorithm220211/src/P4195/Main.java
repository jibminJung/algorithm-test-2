package P4195;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    static int[] parent, size;
    static int order;
    static HashMap<String, Integer> hm = new HashMap<>();

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int tc = Integer.parseInt(br.readLine());
        while (tc-- > 0) {
            int f = Integer.parseInt(br.readLine());
            parent = new int[f * 2];
            for(int i=0;i<parent.length;i++) {
                parent[i] =i;
            }
            size = new int[f * 2];
            Arrays.fill(size, 1);
            order = 0;
            for (int i = 0; i < f; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String f1 = st.nextToken();
                String f2 = st.nextToken();
                if (!hm.containsKey(f1)) {
                    hm.put(f1, order++);
                }
                if (!hm.containsKey(f2)) {
                    hm.put(f2, order++);
                }
                int a = hm.get(f1);
                int b = hm.get(f2);
                if(find(a)==find(b)) {
                    sb.append(getSize(a)).append('\n');
                }else {
                    union(a,b);
                    sb.append(getSize(a)).append('\n');
                }
            }
        }
        System.out.println(sb);

    }

    static int getSize(int a) {
        return size[find(a)];
    }

    static int find(int a) {
        if (parent[a] == a) {
            return a;
        }
        return parent[a] = find(parent[a]);
    }

    static void union(int a, int b) {
        int repA = find(a);
        int repB = find(b);
        size[repB] += size[repA];
        parent[repA] = repB;
    }

}
