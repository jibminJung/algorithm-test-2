package kakao2;

public class Main {

    public static void main(String[] args){
        long supermax = 0;
        for (int n = 0; n < 1000000; n++) {
            for (int k = 3; k < 11; k++) {
                supermax = Math.max(supermax,Solution.solution(n,k));
            }

        }
        System.out.println("super");
        System.out.println(supermax);

    }
}
class Solution {
    boolean[] chk;

    static public long solution(int n, int k) {
        int answer = -1;
        long max =0;

        String[] number = convert(n,k).split("0");
        if(number.length==1){
            return 0;
        }
        for (String num :
                number) {
            try{
                max = Math.max(max,Long.parseLong(num));
            }catch(Exception e){
                continue;
            }

        }
        System.out.println(max);


        return max;
    }



    static String convert(int n, int k){
        StringBuilder sb = new StringBuilder();
        while(n>0){

            sb.append(String.valueOf(n%k));
            n = n/k;
        }
        return  sb.reverse().toString();
    }
}