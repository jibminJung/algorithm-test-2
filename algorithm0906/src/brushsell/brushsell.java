package brushsell;

import java.util.Arrays;
import java.util.HashMap;

public class brushsell {
    public static void main(String[] args) {
        String[] enroll = {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"};
        String[] referral = {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"};
        String[] seller = {"young", "john", "tod", "emily", "mary"};
        int[] amount = {12, 4, 2, 5, 10};
        for (int e :
                Solution.solution(enroll, referral, seller, amount)) {
            System.out.println(e);
        }
    }
}

class Solution {
    static int[] answer;
    static HashMap<String, Integer> idx = new HashMap<>();

    public static int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        answer = new int[enroll.length];
        Arrays.fill(answer, 0);

        for (int i = 0; i < enroll.length; i++) {
            idx.put(enroll[i], i);
        }
        idx.put("-", -1);


        for (int i = 0; i < seller.length; i++) {
            int sellerIdx = idx.get(seller[i]);
            int price = amount[i] * 100;
            divideProfit(sellerIdx, price, referral);
        }
        for (int e :
                answer) {
            System.out.println(e);
        }
        return answer;
    }

    static void divideProfit(int sellerIdx, int price, String[] referral) {
        int goUpProfit = (int) (price * 0.1);
        int myProfit = price - goUpProfit;
        answer[sellerIdx] += myProfit;

        int bossIdx = idx.get(referral[sellerIdx]);
        if (bossIdx == -1 || goUpProfit == 0) {
            return;
        }
        divideProfit(bossIdx, goUpProfit, referral);

    }
}