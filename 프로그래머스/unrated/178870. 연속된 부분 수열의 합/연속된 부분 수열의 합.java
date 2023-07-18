import java.util.*;

class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[2];
        
        // 부분수열 길이
        int nowLen = Integer.MAX_VALUE;
        
        // 누적합
        int sum = sequence[0];
        
        // 포인터
        int left = 0;
        int right = 0;
        
        // 투 포인터
        // sum > k  ->  left ++
        // sum < k  ->  right ++
        // sum == k ->  포인터 위치 저장, 부분 수열 길이 체크
        while(true) {
            if(sum == k) {
                if(nowLen > right - left) {   
                    answer[0] = left;
                    answer[1] = right;
                    nowLen = right - left;
                }
                sum -= sequence[left];
                left++;
                right++;
                if(right < sequence.length) sum += sequence[right];
                else break;
            } else if(sum < k) {
                right++;
                if(right < sequence.length) sum += sequence[right];
                else break;
            } else if(sum > k) {
                if(right < sequence.length) sum -= sequence[left];
                else break;
                left++;
            }
        }
        return answer;
    }
}