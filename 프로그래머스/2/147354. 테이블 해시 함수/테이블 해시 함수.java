import java.util.*;

class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        int answer = 0;
        
        // data 배열 정렬 (col번째 원소 기준 오름차순, 0번째 원소 기준 내림차순)
        Arrays.sort(data, new Comparator<int[]>() {
           
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[col - 1] == o1[col - 1] ? o2[0] - o1[0] : o1[col - 1] - o2[col - 1];
            }
        });
        
        // S_i 계산 후 누적 XOR 연산
        for(int i = row_begin - 1; i < row_end; i ++) {
            int sum = 0;
            for(int j = 0; j < data[i].length; j ++) {
                sum += data[i][j] % (i + 1);    // S_i 계산
            }
            answer ^= sum;  // XOR 연산
        }
        
        return answer;
    }
}