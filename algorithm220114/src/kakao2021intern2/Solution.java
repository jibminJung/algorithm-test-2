package kakao2021intern2;


class Solution {
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        for (int i = 0; i < places.length; i++) {
            char[][] room = new char[5][5];
            for (int j = 0; j < places[i].length; j++) {
                room[j] = places[i][j].toCharArray();
            }
            answer[i] = checkRoom(room)?1:0;
        }
        return answer;
    }
    boolean checkRoom(char[][] room){
        for (int i = 0; i < room.length; i++) {
            for (int j = 0; j < room[i].length; j++) {
                if (room[i][j]=='P'){
                    if(countP(room, i, j)>0) return false;
                }else if(room[i][j]=='O'){
                    if(countP(room, i, j)>1) return false;
                }
            }
        }
        return true;
    }

    private int countP(char[][] room, int i, int j) {
        int cnt =0;
        for (int k = 0; k < 4; k++) {
            int ni = i+dx[k];
            int nj = j+dy[k];
            if(ni<0||ni>4||nj<0||nj>4) continue;
            if('P'==room[ni][nj]) cnt++;
        }
        return cnt;
    }

}