class Solution {
    public int solution(int n) {
        long[][] map = new long[n][4];
        map[0][0] = map[0][3] = 1;
        
        for(int row = 0; row < n - 1; row ++) {
            map[row + 1][0] += (map[row][3] + map[row][0]) % 1000000007L;
            map[row + 1][3] += map[row][0] % 1000000007L;
        }
        
        return (int) (map[n - 1][0] % 1000000007L);
    }
}