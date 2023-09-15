import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        int answer = 0;
        
        // 적군 수 우선순위 큐 (내림차순 정렬)
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        // 게임 진행
        for(int i = 0; i < enemy.length; i ++) {
            if(n < enemy[i] && k == 0) break;   // 병사가 부족하고 무적권도 없을 경우
            pq.offer(enemy[i]); // 적군 수 우선순위 큐에 추가
            if(n < enemy[i]) {  // 병사가 부족해서 무적권을 사용할 경우
                // if(pq.size() == 0) {    // 첫 판인 경우
                //     n += enemy[i];
                // } else {    // 첫 판이 아닐 경우
                //     n += pq.poll(); // 이 전에 가장 많았던 적군 수 만큼 채우기
                // }
                n += pq.poll(); // 이 전에 가장 많았던 적군 수 만큼 채우기
                k --;   // 무적권 사용
                // System.out.println("무적권 사용 " + i + "번째 " + k + " " + n + " " + enemy[i]);
            }
            n -= enemy[i];  // 병사 사용
            answer ++;  // 라운드 추가
            // System.out.println(i + "번째 " + k + "개의 무적권 " + n + "병사 " + enemy[i] + "현재적군 " + answer + "라운드");
        }
        
        return answer;
    }
}