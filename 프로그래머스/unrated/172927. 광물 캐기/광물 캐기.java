import java.util.*;

class Solution {
    public int[] cnt;        // 곡괭이 별로 캘 수 있는 광석 수
    public String[] str;     // 매개변수로 입력받은 minerals
    public int answer = Integer.MAX_VALUE;   // 출력값
    public Map<String, Integer> map;  // 간단한 피로도 계산을 위해 중요도 저장
    
    public int solution(int[] picks, String[] minerals) {
        // 중요도 저장
        map = new HashMap<>();
        map.put("diamond", 0);
        map.put("iron", 1);
        map.put("stone", 2);
        
        // 곡괭이 수에 따라 캘 수 있는 광석 수 저장
        cnt = new int[3];
        for(int i = 0; i < 3; i ++) {
            cnt[i] = picks[i] * 5;
        }
        
        // minerals 전역변수에 저장
        str = new String[minerals.length];
        str = Arrays.copyOf(minerals, minerals.length);
        
        // dfs 연산
        dfs(0, 0);
        
        return answer;
    }
    
    public void dfs(int idx, int sum) {
        // 더 이상 캘 광석이 없으면 피로도 최솟값 저장 후 return
        if(idx >= str.length || cnt[0] + cnt[1] + cnt[2] == 0) {
            answer = Math.min(answer, sum);
            return;
        }
        
        int tired = 0;      // 피로도 저장
        int index = idx;    // 인덱스 저장
        
        // 모둔 곡괭이 사용해보기
        outer : for(int i = 0; i < 3; i ++) {
            // 광물을 캘 수 없는 곡괭이는 continue
            if(cnt[i] <= 0) continue;
            int prev = cnt[i];  // 카운트 되돌리기 위해 저장
            // 5개 광물 캐기
            for(int j = index; j < idx + 5; j ++) {
                if(j >= str.length) break;
                if(cnt[i] <= 0) continue outer;
                int calc = i - map.get(str[j]);
                if(i - map.get(str[j]) < 0) calc = 0;
                tired += Math.pow(5, calc);
                cnt[i] --;
            }
            
            dfs(idx + 5, sum + tired);
            // 변수 초기화
            tired = 0;
            index = idx;
            cnt[i] = prev;
        }
    }
}