package logistics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] w, v;
    static int N, K;
    static int[] cnt;
    static int answer = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] info = br.readLine().split("/");
        StringTokenizer st = new StringTokenizer(info[0]);
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        String[] parcels = info[1].split(",");
        w = new int[parcels.length];
        v = new int[parcels.length];
        cnt = new int[parcels.length];

        for (int i = 0; i < parcels.length; i++) {
            st = new StringTokenizer(parcels[i]);
            w[i] = Integer.parseInt(st.nextToken());
            v[i] = Integer.parseInt(st.nextToken());

        }
        dfs(0, 0);

        System.out.println(answer);

    }

    static void dfs(int weights, int d) {
        if (weights > K||d>N) {
            return;
        }

        int price = 0;
        for (int i = 0; i < cnt.length; i++) {
            price += cnt[i] * v[i];
        }
        answer = Math.max(answer, price);


        for (int i = 0; i < w.length; i++) {
            cnt[i]++;
            dfs(weights + w[i], d + 1);
            cnt[i]--;
        }
    }
}
