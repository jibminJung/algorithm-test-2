package kakao2021intern4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

class Solution {
    static final int INF = Integer.MAX_VALUE;
    static int[][] costArr;
    static HashMap<Integer, Integer> trapList;

    public int solution(int n, int start, int end, int[][] roads, int[] traps) {

        ArrayList<ArrayList<Node>> arr = new ArrayList<>();
        ArrayList<ArrayList<Node>> rArr = new ArrayList<>();
        trapList = new HashMap<>();
        for (int i = 0; i < traps.length; i++) {
            trapList.put(traps[i], 1 << i);
        }
        for (int i = 0; i <= n; i++) {
            arr.add(new ArrayList<>());
            rArr.add(new ArrayList<>());
        }
        for (int[] road : roads) {
            int from = road[0];
            int to = road[1];
            int cost = road[2];
            arr.get(from).add(new Node(to, cost, 0));
            rArr.get(to).add(new Node(from, cost, 0));
        }

        //Dijkstra setup
        int len = 1 << 11;
        costArr = new int[n + 1][len];
        for (int i = 0; i < costArr.length; i++) {
            Arrays.fill(costArr[i], INF);
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0, 0));
        costArr[start][0] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (cur.to == end) break;
            int from = cur.to;
            int cost = cur.cost;
            int trapStatus = cur.trapStatus;

            //현재 노드의 상태 ( 일반/비활성화 되어 있는 트랩 false , 활성화 된 트랩 true)
            boolean fromStatus = false;
            if (trapList.containsKey(from)) {//트랩리스트에 있으면 트랩
                if ((trapStatus & trapList.get(from)) == trapList.get(from)) {//활성상태 트랩인지 확인
                    fromStatus = true;
                }
            }

            for (Node next :
                    arr.get(from)) { //정방향 ff / tt
                boolean toStatus = false;
                if (trapList.containsKey(next.to)) { //trap?
                    if ((trapStatus & trapList.get(next.to)) == trapList.get(next.to)) {//activated?
                        toStatus = true;
                    }
                }

                if (fromStatus == toStatus) {
                    if (costArr[next.to][trapStatus] > cost + next.cost) {
                        costArr[next.to][trapStatus] = cost + next.cost;
                        pq.offer(new Node(next.to, costArr[next.to][trapStatus], updateStatus(next.to, trapStatus)));
                    }
                }

            }
            for (Node next :
                    rArr.get(from)) { //역방향 ft / tf
                boolean toStatus = false;
                if (trapList.containsKey(next.to)) { //trap?
                    if ((trapStatus & trapList.get(next.to)) == trapList.get(next.to)) {//activated?
                        toStatus = true;
                    }
                }

                if (fromStatus != toStatus) {
                    if (costArr[next.to][trapStatus] > cost + next.cost) {
                        costArr[next.to][trapStatus] = cost + next.cost;
                        pq.offer(new Node(next.to, costArr[next.to][trapStatus], updateStatus(next.to, trapStatus)));
                    }
                }

            }
        }

        int answer = INF;
        for (int cost : costArr[end]) {
            answer = Math.min(cost,answer);
        }
        return answer;
    }

    static class Node implements Comparable<Node> {
        int to;
        int cost;
        int trapStatus;

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }

        public Node(int to, int cost, int trapStatus) {
            this.to = to;
            this.cost = cost;
            this.trapStatus = trapStatus;
        }
    }

    static int updateStatus(int trap, int currentStatus) {
        if (trapList.containsKey(trap)) {
            return currentStatus ^ trapList.get(trap);
        } else {
            return currentStatus;
        }
    }
}