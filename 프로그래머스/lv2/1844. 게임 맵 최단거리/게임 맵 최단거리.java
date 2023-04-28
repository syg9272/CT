import java.util.*;

class Solution {
    public int solution(int[][] maps) {
        int answer = Integer.MAX_VALUE;
        
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};
        int n = maps.length;
        int m = maps[0].length;
        boolean[][] visited = new boolean[n][m];
        
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {0, 0, 1});
        visited[0][0] = true;
        
        while(!q.isEmpty()) {
            int time = q.size();
            for(int i = 0; i < time; i ++) {
                int[] temp = q.poll();
                if(temp[0] == n - 1 && temp[1] == m - 1) answer = temp[2];
                for(int d = 0; d < 4; d ++) {
                    int nx = temp[0] + dx[d];
                    int ny = temp[1] + dy[d];
                    if(nx < 0 || nx >= n || ny < 0 || ny >= m || visited[nx][ny] || maps[nx][ny] == 0) continue;
                    q.offer(new int[] {nx, ny, temp[2] + 1});
                    visited[nx][ny] = true;  
                }
            }
        }
        
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
}