package voweldictionary;

public class Main {
    public static void main(String[] args){
        Solution solution = new Solution();
        System.out.println(solution.solution("EIO"));
    }
}
class Solution {
    static int cnt =0;
    static int answer =0;
    static String target = "";
    static char[] arr = {'A','E','I','O','U'};
    public int solution(String word) {
        target = word;

        dfs("",0);

        return answer;
    }

    static void dfs(String str,int depth){
        if(str.equals(target)){
            answer = cnt;
            return;
        }
        if(depth == 5){
            return;
        }
        for(int i =0;i<arr.length;i++){
            str += arr[i];
            cnt++;
            dfs(str,depth+1);
            str = str.substring(0,str.length()-1);
        }

    }
}