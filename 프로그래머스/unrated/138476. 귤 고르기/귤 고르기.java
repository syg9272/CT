import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        // 크기별 카운트 저장할 해시맵
        Map<Integer, Integer> map = new HashMap<>();
        // 정렬된 귤 배열 카운트
        for(int i = 0; i < tangerine.length; i ++) {
            if(map.containsKey(tangerine[i])) {
                map.replace(tangerine[i], map.get(tangerine[i]) + 1);
            } else {
                map.put(tangerine[i], 1);
            }
        }
        // 카운트 값 정렬
        List<Integer> list = new ArrayList<>(map.values()); // 리스트에 value 옮기기
        Collections.sort(list, Collections.reverseOrder()); // 내림차순 정렬
        // 리스트 돌면서 종류 구하기
        int cnt = 1;    // 종류
        int sum = 0;    // 귤 개수
        for(int i = 0; i < list.size(); i ++) {
            if(sum + list.get(i) >= k) {
                break;
            } else {
                sum += list.get(i);
                cnt ++;
            }
        }
        
        return cnt;
    }
}