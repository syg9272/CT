import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        // 크기별 카운트 저장할 배열
        int[] count = new int[10000001];
        // 귤 개수 카운트
        for(int i = 0; i < tangerine.length; i ++) {
            count[tangerine[i]] ++;
        }
        // 카운트 값 정렬
        Arrays.sort(count); // 내림차순 정렬
        // 리스트 돌면서 종류 구하기
        int cnt = 1;    // 종류
        int sum = 0;    // 귤 개수
        for(int i = count.length - 1; i >= 0; i --) {
            if(sum + count[i] >= k) {
                break;
            } else {
                sum += count[i];
                cnt ++;
            }
        }
        
        return cnt;
    }
}