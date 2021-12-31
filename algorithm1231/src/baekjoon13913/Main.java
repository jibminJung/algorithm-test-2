package baekjoon13913;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    static int[] arr = new int[100001];
    static int[] trace = new int[100001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Queue<Integer> q = new LinkedList<>();
        q.offer(n);
        arr[n]=1;
        while (!q.isEmpty()) {
            int temp = q.poll();
            if (temp == k) break;

            for (int i = 0; i < 3; i++) {
                int next;

                if (i == 0) {
                    next = temp + 1;
                } else if (i == 1) {
                    next = temp - 1;
                } else {
                    next = temp * 2;
                }

                if (next < 0 || next > 100000) continue;

                if (arr[next] == 0) {
                    q.add(next);
                    arr[next] = arr[temp] + 1;
                    trace[next] = temp;
                }


            }
        }
        Stack<Integer> stack = new Stack<>();
        stack.push(k);
        int index = k;

        while (index != n) {
            stack.push(trace[index]);
            index = trace[index];
        }
        StringBuilder sb = new StringBuilder();
        sb.append(arr[k] - 1).append('\n');
        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }

        System.out.println(sb);
    }
}
