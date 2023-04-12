import java.util.*;

class Solution {
    // 좌 : 0, 상 : 1, 우 : 2, 하: 3
    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, -1, 0, 1};
    public int solution(int[][] board) {
        // 누적 가격을 저장할 2차원 배열
        int[][][] price = new int[board.length][board.length][4];
        // 4방향 방문체크를 위한 3차원 배열
        boolean[][][] visited = new boolean[board.length][board.length][4];
        
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board.length; j++) {
                Arrays.fill(price[i][j], Integer.MAX_VALUE);
            }
        }
        
        Queue<int[]> q = new ArrayDeque();
        // 처음 좌표, 갈 수 있는 방향 큐에 넣기 (우, 하)
        // {x좌표, y좌표, 방향, 누적가격}
        // q.offer(new int[] {0, 0, 0, 0});
        // q.offer(new int[] {0, 0, 1, 0});
        q.offer(new int[] {0, 0, 2, 0});
        q.offer(new int[] {0, 0, 3, 0});
        visited[0][0][0] = true;
        visited[0][0][1] = true;
        visited[0][0][2] = true;
        visited[0][0][3] = true;
        
        while(!q.isEmpty()) {
            int size = q.size();
            for(int t = 0; t < size; t++) {
                int[] temp = q.poll();
                for(int d = 0; d < 4; d++) {
                    int rx = temp[0] + dx[d];
                    int ry = temp[1] + dy[d];
                    if(rx < 0 || rx >= board.length || ry < 0 || ry >= board.length || board[rx][ry] == 1) continue;
                    // if((d == 0 && visited[rx][ry][2]) || (d == 0 && visited[rx][ry][2]) || (d == 0 && visited[rx][ry][2]) || (d == 0 && visited[rx][ry][2])) continue;
                    // if((d == 0 && price[rx][ry][2] < temp[3] + 100) || (d == 0 && visited[rx][ry][2]) || (d == 0 && visited[rx][ry][2]) || (d == 0 && visited[rx][ry][2])) continue;
                    if(temp[2] == d){
                        if(price[rx][ry][d] >= temp[3] + 100) {
                            price[rx][ry][d] = temp[3] + 100;
                            visited[rx][ry][d] = true;
                            q.offer(new int[] {rx, ry, d, temp[3] + 100});
                        }
                    }
                    if(temp[2] != d){
                        if(price[rx][ry][d] >= temp[3] + 600) {
                            price[rx][ry][d] = temp[3] + 600;
                            visited[rx][ry][d] = true;
                            q.offer(new int[] {rx, ry, d, temp[3] + 600});
                        }
                    }
                }
            }
        }
        int minPrice = Integer.MAX_VALUE;
        for(int d = 0; d < 4; d++) {
            minPrice = minPrice <= price[board.length - 1][board.length - 1][d] ? minPrice : price[board.length - 1][board.length - 1][d];
        }
        return minPrice;
    }
}