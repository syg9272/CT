import java.util.*;

class Solution {
    public static int[] sales = {10, 20, 30, 40};   // 할인율
    public static int[] num;                        // 순열 저장
    public static PriorityQueue<int[]> pq;          // 우선순위 큐
    public int[] solution(int[][] users, int[] emoticons) {
        // 순열 저장할 배열
        num = new int[emoticons.length];
        // 우선순위 큐 정렬조건 설정
        pq = new PriorityQueue<>(new Comparator<int[]>() {
            // 가입자 내림차순 -> 구매 비용 내림차순
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] == o2[0] ? o2[1] - o1[1] : o2[0] - o1[0];
            }
        });
        // 할인율 중복순열
        perm(0, users, emoticons);
        // 우선순위 가장 높은 상태 return
        return pq.poll();
    }
    
    public void perm(int r, int[][] users, int[] emoticons) {
        // 재귀함수 종료조건
        if(r == num.length) {
            // 구매 비용 및 서비스 가입자 계산
            int service = 0;    // 서비스 가입자
            int money = 0;      // 판매 비용
            
            for(int i = 0; i < users.length; i ++) {
                // 해당 사용자 구매 비용 합
                int sum = 0;
                for(int e = 0; e < emoticons.length; e ++) {
                    // 할인율 기준 확인
                    if(num[e] >= users[i][0]) {
                        sum += emoticons[e] * (100 - num[e]) / 100; // 할인율 적용
                    }
                }
                if(sum >= users[i][1]) service ++;
                else money += sum;
            }
            
            pq.offer(new int[] {service, money});
            // System.out.println(service + " " + money);
            return;
        }
        
        for(int i = 0; i < sales.length; i ++) {
            num[r] = sales[i];
            perm(r + 1, users, emoticons);
        }
        
        // if(start == num.length) return;
        // num[r] = sales[start];
        // comb(r + 1, start, users, emoticons);
        // comb(r, start + 1, users, emoticons);
    }
}