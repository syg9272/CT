import java.util.*;

class Solution {
    static int N, goal, cnt = 0;
    static int[] num;
    public int solution(int[] numbers, int target) {
        N = numbers.length;
        goal = target;
        num = Arrays.copyOf(numbers, numbers.length);
        dfs(0, 0);
        return cnt;
    }
    
    public void dfs(int n, int sum) {
        if(n == N) {
            if(sum == goal) cnt++;
            return;
        }
        dfs(n + 1, sum + num[n]);
        dfs(n + 1, sum - num[n]);
    }
}