import java.util.*;

class Solution {
    public int solution(String[] maps) {
        int answer = -1;
        
        int M = maps[0].length();
        int N = maps.length;
        
        // 미로 맵 생성
        char[][] map = new char[N][M];
        
        // 출발지점
        int startX = 0;
        int startY = 0;
        
        // 1차원 maps를 2차원 맵에 옮기기
        for(int i = 0; i < maps.length; i ++) {
            for(int j = 0; j < maps[0].length(); j ++) {
                map[i][j] = maps[i].charAt(j);
                if(map[i][j] == 'S') {
                    startX = i;
                    startY = j;
                }
            }
        }
        
        // 4방탐색
        int[] dx = {0, -1, 0, 1};
        int[] dy = {-1, 0, 1, 0};
        
        // bfs
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {startX, startY, 0, 0}); // x좌표, y좌표, 레버 여부, time
        boolean[][][] visited = new boolean[N][M][2]; // 방문체크 2type (레버 당기기 전, 레버 당긴 후)
        visited[startX][startY][0] = true; // 시작지점 방문체크
        
        outer : while(!q.isEmpty()) {
            int time = q.size(); // 1 depth
            for(int t = 0; t < time; t ++) {
                int[] temp = q.poll();
                for(int d = 0; d < 4; d ++) { // 4방 탐색
                    int nx = temp[0] + dx[d];
                    int ny = temp[1] + dy[d];
                    
                    // 맵을 벗어나거나 벽인 경우
                    if(nx < 0 || nx >= N || ny < 0 || ny >= M || map[nx][ny] == 'X') continue; 
                    
                    // 레버인 경우
                    if(map[nx][ny] == 'L' && !visited[nx][ny][1] && temp[2] == 0) {
                        // 당기기 전 첫 방문
                        q.offer(new int[] {nx, ny, 1, temp[3] + 1});
                        visited[nx][ny][1] = true;
                    }
                    
                    // 출구인 경우
                    else if(map[nx][ny] == 'E') {
                        // 레버 당기기 전
                        if(temp[2] == 0 && !visited[nx][ny][0]) {
                            q.offer(new int[] {nx, ny, temp[2], temp[3] + 1});
                            visited[nx][ny][0] = true;
                        }
                        // 레버 당긴 후
                        if(temp[2] == 1) {
                            answer = temp[3] + 1;
                            break outer;
                        }
                    }
                    
                    // 시작 지점 || 통로
                    else if(map[nx][ny] == 'S' || map[nx][ny] == 'O') {
                        if(!visited[nx][ny][temp[2]]) { // 첫 방문
                            q.offer(new int[] {nx, ny, temp[2], temp[3] + 1});
                            visited[nx][ny][temp[2]] = true;
                        }
                    }
                }
            }
        }
        
        return answer;
    }
}