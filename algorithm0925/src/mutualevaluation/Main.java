package mutualevaluation;

public class Main {
    public static void main(String[] args){
        //test case answer : FBABD
        int[][] scores = {{100,90,98,88,65},{50,45,99,85,77},{47,88,95,80,67},{61,57,100,80,65},{24,90,94,75,65}};
        Solution solution = new Solution();
        System.out.println(solution.solution(scores));
    }
}
class Solution {
    public String solution(int[][] scores) {
        int numberOfStudent =scores.length;
        for(int i = 0;i<numberOfStudent;i++){
            int upCnt =0;
            int downCnt =0;
            for(int j = 0;j<numberOfStudent;j++){
                if(i==j) continue;
                if(scores[i][i]>scores[j][i]){
                    upCnt++;
                }else if(scores[i][i]<scores[j][i]){
                    downCnt++;
                }else{
                    break;
                }
            }
            if(upCnt==downCnt)continue;
            if(upCnt==numberOfStudent-1 || downCnt==numberOfStudent-1){
                scores[i][i] = -1;
            }
        }

        float[] avg = new float[numberOfStudent];
        for(int i =0;i<numberOfStudent;i++){
            int sum =0;
            int k = 0;
            for(int j=0;j<numberOfStudent;j++){
                if(scores[j][i] != -1){
                    sum += scores[j][i];
                    k++;
                }
            }
            if(k!=0){
                avg[i] = (float)sum / (float)k;
            }else{
                avg[i] = 0f;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(float score:avg){
            if(score>=90f){
                sb.append('A');
            }else if(score>=80f){
                sb.append('B');
            }else if(score>=70f){
                sb.append('C');
            }else if(score>=50f){
                sb.append('D');
            }else{
                sb.append('F');
            }
        }

        return sb.toString();
    }
}