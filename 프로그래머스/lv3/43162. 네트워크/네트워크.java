import java.util.*;

class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        boolean[] visited = new boolean[n];
        Queue<Integer> q = new ArrayDeque<>();
        for(int i = 0; i < n; i ++) {
            if(visited[i]) continue;
            q.offer(i);
            visited[i] = true;
            while(!q.isEmpty()) {
                int time = q.size();
                for(int t = 0; t < time; t ++) {
                    int temp = q.poll();
                    for(int j = 0; j < n; j ++) {
                        if(j == temp || visited[j] || computers[temp][j] != 1) continue;
                        visited[j] = true;
                        q.offer(j);
                    }
                }
            }
            answer++;
        }
        return answer;
    }
}