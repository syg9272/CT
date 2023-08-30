class Solution {
    public int solution(String[] board) {
        
        // 나오면 안되는 경우
        // 1. O 개수 < X 개수
        // 2. O 개수 - x 개수 >= 2
        // 3. O 빙고, X 빙고 둘 다 존재
        // 4. X 빙고인데 O != X
        // 5. O 빙고인데 O - X != 1
        int o = 0;      // O 개수
        int x = 0;      // X 개수
        int tttO = 0;   // O 빙고 개수
        int tttX = 0;   // X 빙고 개수
        
        // 표시 개수
        for(int i = 0; i < 3; i ++) {
            for(int j = 0; j < 3; j ++) {
                if(board[i].charAt(j) == 'O') {
                    o ++;
                }
                if(board[i].charAt(j) == 'X') {
                    x ++;
                }
            }
        }
        
        // 1, 2 조건 충족 -> 나올 수 없는 상황
        if(o < x) return 0;
        if(o - x >= 2) return 0;
        
        // 빙고 개수
        for(int i = 0; i < 3; i ++) {
            // 가로
            if(board[i].equals("OOO")) {
                tttO ++;
            }
            if(board[i].equals("XXX")) {
                tttX ++;
            }
            // 세로
            if(board[0].charAt(i) == board[1].charAt(i) &&  board[1].charAt(i) == board[2].charAt(i) && board[0].charAt(i) == board[2].charAt(i)) {
                if(board[0].charAt(i) == 'O') {
                    tttO ++;
                }
                if(board[0].charAt(i) == 'X') {
                    tttX ++;
                }
            }
        }
        // 대각선
        char center = board[1].charAt(1);
        if(board[0].charAt(0) == center && board[2].charAt(2) == center) {
            if(center == 'O') {
                tttO ++;
            }
            if(center == 'X') {
                tttX ++;
            }
        }
        if(board[0].charAt(2) == center && board[2].charAt(0) == center) {
            if(center == 'O') {
                tttO ++;
            }
            if(center == 'X') {
                tttX ++;
            }
        }
        
        // 3 조건 충족 -> 나올 수 없는 상황
        if(tttO > 0 && tttX > 0) return 0;
        
        // 4 조건 충족 -> 나올 수 없는 상황
        if(tttX == 1 && o != x) return 0;
        
        // 5 조건 충족 -> 나올 수 없는 상황
        if(tttO == 1 && o - x != 1) return 0;
        
        return 1;
    }
}