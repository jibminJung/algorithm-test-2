package P1517;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    static int n, s = 1;
    static int[] tree;
    static ArrayList<Node> arr = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i < n + 1; i++) {
            arr.add(new Node(i, Integer.parseInt(st.nextToken()), 0));
        }
        Collections.sort(arr, Comparator.comparingInt(Node::getValue));
        int prev = 0;
        int prevValue = Integer.MAX_VALUE;
        for (Node node : arr) {
            if (node.value != prevValue) {
                prev++;
                prevValue = node.value;
            }
            node.relativeValue = prev;
        }
        Collections.sort(arr, Comparator.comparingInt(Node::getId));
        while (s < prev) {
            s <<= 1;
        }
        tree = new int[s * 2];
        long answer = 0;
        for (Node node :
                arr) {
           update(node.relativeValue);
            answer += query(node.relativeValue + 1,prev);
        }
        System.out.println(answer);

    }

    static void update(int index) {
        int treeIndex = s + index - 1;
        while (treeIndex > 0) {
            tree[treeIndex]++;
            treeIndex /= 2;
        }
    }

    static int query(int start, int end) {
        int left = s + start - 1;
        int right = s + end - 1;
        int sum = 0;
        while (left < right) {
            if (left % 2 == 1) {
                sum += tree[left];
                left++;
            }
            if (right % 2 == 0) {
                sum += tree[right];
                right--;
            }
            left /= 2;
            right /= 2;
        }
        if (left == right) {
            sum += tree[left];
        }
        return sum;
    }
}

class Node {
    int id;
    int value;
    int relativeValue;

    public Node(int id, int value, int relativeValue) {
        this.id = id;
        this.value = value;
        this.relativeValue = relativeValue;
    }

    public int getId() {
        return id;
    }

    public int getValue() {
        return value;
    }
}