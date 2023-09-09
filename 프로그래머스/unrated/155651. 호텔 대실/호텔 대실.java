import java.util.*;

class Solution {
    public int solution(String[][] book_time) {
        int answer = 0;
        
        // 끝나는 시각 오름차순 정렬
        PriorityQueue<String> pq = new PriorityQueue<>(new Comparator<String>() {
            
            @Override
            public int compare(String o1, String o2) {
                return Integer.parseInt(o1) - Integer.parseInt(o2);
            }
        });
        
        // 예약시간 배열 정렬 (시작 시각 오름차순)
        Arrays.sort(book_time, new Comparator<String[]>() {
           
            @Override
            public int compare(String[] o1, String[] o2) {
                String left = o1[0].substring(0, 2) + o1[0].substring(3);
                String right = o2[0].substring(0, 2) + o2[0].substring(3);
                
                return Integer.parseInt(left) - Integer.parseInt(right);
            }
        });
        
        
        // 예약시간 차례대로 확인하기
        for(int i = 0; i < book_time.length; i ++) {
            // 현재 예약 시작 시각
            String myTime = book_time[i][0].substring(0, 2) + book_time[i][0].substring(3);
            String endTime = book_time[i][1].substring(0, 2) + book_time[i][1].substring(3);
            // 초기상태
            if(pq.isEmpty() && answer == 0) {
                answer ++;
                pq.offer(endTime);
                continue;
            }
            // 남는 방이 있을 경우
            if(pq.size() < answer) {
                pq.offer(endTime);
                continue;
            }
            // 사용 중인 방 밖에 없을 경우
            int size = pq.size();
            int cnt = 0;    // 퇴실 방 개수
            for(int j = 0; j < size; j ++) {
                String temp = pq.peek();
                
                // 청소시간 더하기
                int time = Integer.parseInt(temp) + 10;
                if(time % 100 >= 60) time += 40;
                // 내 예약 시작 시각이 현재 방의 청소 끝난 이후일 때
                if(time <= Integer.parseInt(myTime)) {
                    pq.poll();
                    cnt ++;
                } else break;
            }
            // 나간 방이 있을 경우
            if(cnt > 0) {
                pq.offer(endTime);
            } else {    // 없을 경우
                answer ++;
                pq.offer(endTime);
            }
        }
        
        return answer;
    }
}