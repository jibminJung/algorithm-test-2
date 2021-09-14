package kakao1;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class Main {

}
class Solution{
    static HashMap<String, HashSet<String>> reported = new HashMap<>();
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];

        LinkedHashMap<String,Integer> hm = new LinkedHashMap<>();
        for (String id :
                id_list) {
            reported.put(id,new HashSet<String>());
            hm.put(id,0);
        }

        for (String report_record :
                report) {
            String[] report_ = report_record.split(" ");
            String reporter = report_[0];
            String reportee = report_[1];
            
            reported.get(reportee).add(reporter);
        }

        for (String reportee :
                reported.keySet()) {
            HashSet<String> temp = reported.get(reportee);
            if(temp.size()>=k){
                for (String reporter :
                        temp) {
                    hm.put(reporter,hm.get(reporter)+1);
                }
            }
        }
        Iterator<String> iter = hm.keySet().iterator();
        for (int i = 0; i < answer.length; i++) {

            answer[i] = hm.get(iter.next());
        }


        return answer;
    }
}
