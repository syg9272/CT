import java.util.*;

class Solution {
    public int solution(int x, int y, int n) {
        
        // bfs
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {y, 0});
        boolean[] visited = new boolean[y + 1];
        visited[y] = true;
        
        while(!q.isEmpty()) {
            int time = q.size();
            for(int t = 0; t < time; t ++) {
                int[] temp = q.poll();
                if(temp[0] == x) return temp[1];
                if(temp[0] - n >= x && !visited[temp[0] - n]) {
                    q.offer(new int[] {temp[0] - n, temp[1] + 1});
                }
                if(temp[0] % 2 == 0 && temp[0] * 2 >= x && !visited[temp[0] / 2]) {
                    q.offer(new int[] {temp[0] / 2, temp[1] + 1});
                }
                if(temp[0] % 3 == 0 && temp[0] * 3 >= x && !visited[temp[0] / 3]) {
                    q.offer(new int[] {temp[0] / 3, temp[1] + 1});
                }
            }
        }
        
        return -1;
    }
}