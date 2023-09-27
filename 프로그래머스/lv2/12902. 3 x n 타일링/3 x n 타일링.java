class Solution {
    public int solution(int n) {
        // 효율적인 연산을 위해 직사각형을 90도 회전
        long[][] map = new long[n][8];    // 한 행에 나올 수 있는 경우의 수 8가지
        map[0][1] = map[0][4] = map[0][7] = 1;  // 1번째 행에서 가능한 경우
        // DP 연산
        for(int i = 0; i < n - 1; i ++) {
            for(int now = 0; now < 8; now ++) { // 현재 행
                map[i + 1][now ^ 7] += map[i][now] % 1000000007L; // 규칙 1
                if(now == 1 || now == 4) {  // 규칙 2
                    map[i + 1][0] += map[i][now] % 1000000007L;  
                }
                if(now == 0) {  // 규칙 2
                    map[i + 1][1] += map[i][0] % 1000000007L;
                    map[i + 1][4] += map[i][0] % 1000000007L;
                }
            }
        }
        // n행의 유일한 유효값인 000에 저장된 결과값 반환
        return (int) (map[n - 1][0] % 1000000007L);
    }
}