package number1;

public class Main {


}
class Solution {
    public String solution(String character, String[] monsters) {
        String answer = "";
        String[] characterStat = character.split(" ");
        int[] charStat = new int[3];
        //체력 공격력 방어력
        for (int i = 0; i < characterStat.length; i++) {
            charStat[i] = Integer.parseInt(characterStat[i]);
        }
        //경험치 체력 공격력 방어력
        int[][] monStat = new int[monsters.length][4];
        for (int i = 0; i < monsters.length; i++) {
            String[] temp = monsters[i].split(" ");
            monStat[i][0] = Integer.parseInt(temp[1]);
            monStat[i][1] = Integer.parseInt(temp[2]);
            monStat[i][2] = Integer.parseInt(temp[3]);
            monStat[i][3] = Integer.parseInt(temp[4]);
        }
        //exp per sec
        double[][] eps = new double[monsters.length][2];
        for (int i = 0; i < monStat.length; i++) {
            //양방향으로 공-방 <=0 이면 끝나지 않는다.
            if(charStat[1]-monStat[i][3]<=0 && monStat[i][2]-charStat[2]<=0){
                eps[i][0] = 0;
                eps[i][1] = -1;
                continue;
            }
            if(charStat[1]-monStat[i][3]<=0){
                eps[i][0] = 0;
                eps[i][1] = -1;
                continue;
            }
            if(monStat[i][2]-charStat[2]<=0){
                eps[i][0] = monStat[i][0];
                double monsterTakenSecond = (double)(monStat[i][1]+(charStat[1]-monStat[i][3]-1))/(double)(charStat[1]-monStat[i][3]);
                eps[i][1] = (double)monStat[i][0]/(double)monsterTakenSecond;
                continue;
            }
            //적에게는 (내 공격력 - 적 방어력) 나에게는 (적 공격력 - 내 방어력) 의 피해를 입는다.
            //1초마다 내가 먼저 공격하고 적이 나를 공격한다.
            // 체력/피해를 하면 몇초인지 알 수 있다.
            //내가 죽는 시간과 적이 죽는 시간
            double myTakenSecond = (charStat[0]+(monStat[i][2]-charStat[2]-1))/(monStat[i][2]-charStat[2]);
            double monsterTakenSecond = (monStat[i][1]+(charStat[1]-monStat[i][3]-1))/(charStat[1]-monStat[i][3]);
            if(myTakenSecond >= monsterTakenSecond){ //내가 적을 죽이는 경우
                eps[i][0] = monStat[i][0];
                eps[i][1] = (double)monStat[i][0]/(double)monsterTakenSecond;
            }else{
                eps[i][0] = 0;
                eps[i][1] = -1;
            }
        }
        double maxEps = -2;
        double maxExp =-2;
        int index = 0;

        for (int i = eps.length-1; i >=0; i--) {
            if(eps[i][0]==0) continue;
            if(maxEps<eps[i][1]) {
                maxEps = eps[i][1];
                maxExp = eps[i][0];
                index = i;
            }else if(maxEps==eps[i][1]&&eps[i][0]>=maxExp){
                index = i;
                maxExp = eps[i][0];
            }
        }
        return monsters[index].split(" ")[0];
    }
}