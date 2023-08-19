import java.util.*;

class Solution {
    public int solution(String[] board) {
        int answer = -1;
        
        int startX = 0;
        int startY = 0;
        
        int endX = 0;
        int endY = 0;
        
        // 게임판 생성
        char[][] map = new char[board.length][board[0].length()];
        for(int i = 0; i < board.length; i ++) {
            for(int j = 0; j < board[0].length(); j ++) {
                map[i][j] = board[i].charAt(j);
                if(map[i][j] == 'R') {
                    startX = i;
                    startY = j;
                }
                if(map[i][j] == 'G') {
                    endX = i;
                    endY = j;
                }
            }
        }
        
        int[] dx = {0, -1, 0, 1};
        int[] dy = {-1, 0, 1, 0};
        
        boolean flag = false;
        // 도착지점 상하좌우에 장애물 혹은 벽이 없을 경우 -1 return
        for(int i = 0; i < 4; i ++) {
            int rx = endX + dx[i];
            int ry = endY + dy[i];
            if(rx < 0 || rx >= board.length || ry < 0 || ry >= board[0].length()) {
                flag = true;
                break;
            }
            if(map[rx][ry] == 'D') {
                flag = true;
                break;
            }
        }
        if(!flag) return -1;
        
        
        // 큐 생성 후 시작 지점 설정
        Queue<int[]> q = new ArrayDeque<>();
        // 방문체크 4방향
        boolean[][][] visited = new boolean[board.length][board[0].length()][4];
        visited[startX][startY][0] = true;
        visited[startX][startY][1] = true;
        visited[startX][startY][2] = true;
        visited[startX][startY][3] = true;
        q.offer(new int[] {startX, startY, 0, 1});
        q.offer(new int[] {startX, startY, 1, 1});
        q.offer(new int[] {startX, startY, 2, 1});
        q.offer(new int[] {startX, startY, 3, 1});
        
        // bfs
        outer : while(!q.isEmpty()) {
            int time = q.size();
            for(int t = 0; t < time; t ++) {
                int[] temp = q.poll();
                for(int d = 0; d < 4; d ++) {
                    int nx = temp[0] + dx[d];
                    int ny = temp[1] + dy[d];
                    // 해당 방향으로 끝까지 이동
                    while(true) {
                        // 맵을 벗어나거나 장애물일 경우 한칸 되돌아가기
                        if(nx < 0 || nx >= board.length || 
                           ny < 0 || ny >= board[0].length() || map[nx][ny] == 'D') {
                           nx -= dx[d];
                            ny -= dy[d];
                            break;
                        }
                        nx += dx[d];
                        ny += dy[d];
                    }
                    // 도착지일 경우 탐색 끝 (bfs여서 해당 depth가 최소값)
                    if(map[nx][ny] == 'G') {
                        answer = temp[3];
                        break outer;
                    }
                    // 도착지가 아닐 경우 해당 방향으로 방문하지 않은 곳일 경우 큐에 삽입
                    if(!visited[nx][ny][d]){
                        visited[nx][ny][d] = true;
                        q.offer(new int[] {nx, ny, 0, temp[3] + 1});
                        q.offer(new int[] {nx, ny, 1, temp[3] + 1});
                        q.offer(new int[] {nx, ny, 2, temp[3] + 1});
                        q.offer(new int[] {nx, ny, 3, temp[3] + 1});
                    }
                }
            }
        }
        
        return answer;
    }
}