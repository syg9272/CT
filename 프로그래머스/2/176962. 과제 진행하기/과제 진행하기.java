import java.util.*;

class Solution {
    public String[] solution(String[][] plans) {
        
        // stack 사용 --> LIFO
        List<String> list = new ArrayList<>();
        Stack<String[]> stack = new Stack<>();
        
        // 시간 순으로 정렬
        Arrays.sort(plans, new Comparator<String[]>() {
            public int compare(String[] s1, String[] s2) {
                int time1 = Integer.parseInt(s1[1].substring(0, 2) + s1[1].substring(3));
                int time2 = Integer.parseInt(s2[1].substring(0, 2) + s2[1].substring(3));
                return time1 - time2;
            }
        });
        
        // 조건 1) 다음 숙제 시작 시각 - 시작 시각 = 과제 소요 시간 ---> list에 완료
        // 조건 2) 다음 숙제 시작 시각 - 시작 시각 > 과제 소요 시간 ---> list에 완료, stack에서 꺼내서 완료
        // 조건 3) 다음 숙제 시작 시각 - 시작 시각 < 과제 소요 시간 ---> stack에 담기
        // plans 배열 한 바퀴 돈 뒤 stack에서 하나씩 꺼내서 list에 담기
        for(int i = 0; i < plans.length - 1; i++ ) {
            // 시간 저장 변수
            int time = 0;
            
            // 시간 계산 (다음 숙제 시작 시각 - 시작 시각)
            int hour = Integer.parseInt(plans[i + 1][1].substring(0, 2)) - Integer.parseInt(plans[i][1].substring(0, 2));
            if(hour == 0) {
                time += Integer.parseInt(plans[i + 1][1].substring(3)) - Integer.parseInt(plans[i][1].substring(3));
            } else {
                time += 60 * hour;
                if(Integer.parseInt(plans[i][1].substring(3)) > Integer.parseInt(plans[i + 1][1].substring(3))) {
                    time -= 60;
                    time += 60 - Integer.parseInt(plans[i][1].substring(3)) + Integer.parseInt(plans[i + 1][1].substring(3));
                } else {
                    time += Integer.parseInt(plans[i + 1][1].substring(3)) - Integer.parseInt(plans[i][1].substring(3));
                }
            }
            
            // 조건 1, 2
            if(time >= Integer.parseInt(plans[i][2])) {
                list.add(plans[i][0]);
                int subTime = time - Integer.parseInt(plans[i][2]);
                while(subTime > 0) {
                    if(stack.isEmpty()) break;
                    String[] temp = stack.pop();
                    int tempTime = Integer.parseInt(temp[1]);
                    if(tempTime <= subTime) {
                        list.add(temp[0]);
                    } else {
                        stack.push(new String[] {temp[0], Integer.toString(tempTime - subTime)});
                    }
                    subTime -= tempTime;
                }
            } else { // 조건 3
                stack.push(new String[] {plans[i][0], Integer.toString(Integer.parseInt(plans[i][2]) - time)});
            }
        }
        // 마지막 숙제 넣기
        list.add(plans[plans.length - 1][0]);
        
        while(!stack.isEmpty()) {
            String[] temp = stack.pop();
            list.add(temp[0]);
        }
        
        // 결과 옮기기
        String[] answer = new String[plans.length];
        for(int i = 0; i < list.size(); i++ ) {
            answer[i] = list.get(i);
        }
        
        return answer;
    }
}