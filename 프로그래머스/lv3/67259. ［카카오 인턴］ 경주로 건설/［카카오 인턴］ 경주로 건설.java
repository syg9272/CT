import java.util.*;

class Solution {
    // 좌 : 0, 상 : 1, 우 : 2, 하: 3
    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, -1, 0, 1};
    public int solution(int[][] board) {
        // 누적 가격을 저장할 2차원 배열
        int[][][] price = new int[board.length][board.length][4];
        // 최대 가격으로 초기화
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board.length; j++) {
                Arrays.fill(price[i][j], Integer.MAX_VALUE);
            }
        }
        
        Queue<int[]> q = new ArrayDeque();
        // 처음 좌표, 갈 수 있는 방향 큐에 넣기 (우, 하)
        // {x좌표, y좌표, 방향, 누적가격}
        q.offer(new int[] {0, 0, 2, 0});
        q.offer(new int[] {0, 0, 3, 0});
        
        while(!q.isEmpty()) {
            int size = q.size();
            for(int t = 0; t < size; t++) {
                int[] temp = q.poll();
                for(int d = 0; d < 4; d++) {
                    int rx = temp[0] + dx[d];
                    int ry = temp[1] + dy[d];
                    // 인덱스 유효하지 않거나 벽일 경우 continue
                    if(rx < 0 || rx >= board.length || ry < 0 || ry >= board.length || board[rx][ry] == 1) continue;
                    // 같은 방향일 경우
                    if(temp[2] == d){
                        // 해당 방향으로 이미 저장된 가격이 현재 가격보다 비싸지 않을 경우만 저장
                        if(price[rx][ry][d] >= temp[3] + 100) {
                            price[rx][ry][d] = temp[3] + 100;
                            q.offer(new int[] {rx, ry, d, temp[3] + 100});
                        }
                    }
                    // 코너일 경우
                    if(temp[2] != d){
                        // 해당 방향으로 이미 저장된 가격이 현재 가격보다 비싸지 않을 경우만 저장
                        if(price[rx][ry][d] >= temp[3] + 600) {
                            price[rx][ry][d] = temp[3] + 600;
                            q.offer(new int[] {rx, ry, d, temp[3] + 600});
                        }
                    }
                }
            }
        }
        // 도착지 4방향 중 가장 저렴한 가격 return
        int minPrice = Integer.MAX_VALUE;
        for(int d = 0; d < 4; d++) {
            minPrice = minPrice <= price[board.length - 1][board.length - 1][d] ? minPrice : price[board.length - 1][board.length - 1][d];
        }
        return minPrice;
    }
}