import java.util.*;

class Solution {
    public int[] solution(String[] maps) {
        
        // 2차원 맵에 옮기기
        int N = maps.length;            // 행
        int M = maps[0].length();       // 열
        char[][] map = new char[N][M];  // 맵
        
        for(int i = 0; i < N; i ++) {
            for(int j = 0; j < M; j ++) {
                map[i][j] = maps[i].charAt(j);
            }
        }
        
        // bfs돌며 무인도 머무는 날짜 체크
        boolean[][] visited = new boolean[N][M];    // 방문체크
        PriorityQueue<Integer> pq = new PriorityQueue<>();   // 오름차순 저장할 우선순위 큐
        Queue<int[]> q = new ArrayDeque<>();  // 각 위치마다 bfs에 쓰이는 큐
        
        int[] dx = {0, 1, 0, -1};   // 4방탐색 이동할 위치
        int[] dy = {1, 0, -1, 0};
        
        
        for(int i = 0; i < N; i ++) {
            for(int j = 0; j < M; j ++) {
                if(visited[i][j] || map[i][j] == 'X') continue; // 이미 방문했거나 바다면 continue
                q.offer(new int[] {i, j});   // 현재좌표
                visited[i][j] = true;   // 방문체크
                
                int sum = map[i][j] - '0';
                
                // bfs
                while(!q.isEmpty()) {
                    int time = q.size();
                    for(int t = 0; t < time; t ++) {
                        int[] temp = q.poll();
                        for(int d = 0; d < 4; d ++) {
                            int nx = temp[0] + dx[d];
                            int ny = temp[1] + dy[d];
                            // 맵의 범위를 벗어나거나 이미 방문한 곳
                            if(nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny]) continue;
                            // 바다
                            if(map[nx][ny] == 'X') continue;
                            // 섬일 경우
                            sum += map[nx][ny] - '0';       // 날짜 더하기
                            q.offer(new int[] {nx, ny});    // 다음 좌표 넣기
                            visited[nx][ny] = true;         // 방문체크
                        }
                    }
                }
                // 우선순위 큐에 날짜 합 추가하기
                pq.offer(sum);
            }
        }
        
        // 반환값 저장
        int len = pq.size();
        int[] answer = new int[len];
        if(len == 0) return new int[] {-1}; // 섬이 없으면 -1 반환
        for(int i = 0; i < len; i ++) {
            answer[i] = pq.poll();
        }
        
        return answer;
    }
}