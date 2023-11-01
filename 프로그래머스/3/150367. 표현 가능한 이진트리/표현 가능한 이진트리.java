import java.util.*;

class Solution {
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        // 배열 순회하며 표현 가능한 이진트리 검사
        for(int i = 0; i < numbers.length; i ++) {
            String binaryNum = "";  // 변환한 이진수 저장     
            long num = numbers[i];  // 현재 수
            // 2로 나누면서 이진수로 변환
            while(num > 0) {        
                if(num % 2 == 0) binaryNum = "0" + binaryNum;
                else binaryNum = "1" + binaryNum;
                num /= 2;
            }
            // 포화 이진트리로 만들기 (2^n - 1)
            int n = 1;
            // 부족한 노드 개수 찾기
            while(n < binaryNum.length()){
                n = n * 2 + 1;
            }
            // 부족한 만큼 앞에 0 채우기 (값이 변하지 않으면서 노드 채우기)
            while(binaryNum.length() < n) {
                binaryNum = "0" + binaryNum;
            }
            // 리프노드까지 순회
            boolean flag = true;    // 이진트리 표현 가능 여부
            int parent = binaryNum.length() / 2;    // 부모노드 위치
            if(parent == 0) {   // 1자리 수 예외처리
                answer[i] = 1;
                continue;
            }
            Queue<String[]> q = new ArrayDeque<>(); // 부모노드, 자식 그룹 담을 큐
            q.offer(new String[] {binaryNum.substring(parent, parent + 1),  // 부모 노트
                                  binaryNum.substring(0, parent),           // 왼쪽 자식 그룹
                                  binaryNum.substring(parent + 1)});         // 오른쪽 자식 그룹
            outer : while(!q.isEmpty()) {
                // 자식노드가 1로 존재할 때 부모노드가 0인 경우 찾기
                int time = q.size();
                for(int t = 0; t < time; t ++) {
                    String[] temp = q.poll();   // 현재 그룹
                    for(int c = 1; c < temp.length; c ++) {
                        int idx = temp[c].length() / 2; // 자식그룹의 가운데 노드 (다음 자식들의 부모)
                        if(temp[0].equals("0") && temp[c].substring(idx, idx + 1).equals("1")) {    // 부모 0 자식 1
                            flag = false;
                            break outer;    // 종료
                        }
                        if(idx != 0) {  // 다음 자식그룹이 있다면
                            q.offer(new String[] {temp[c].substring(idx, idx + 1),  // 부모
                                                 temp[c].substring(0, idx),         // 왼쪽 자식그룹
                                                 temp[c].substring(idx + 1)});      // 오른쪽 자식그룹
                        }
                    }
                }
            }
            // 표현 가능 여부 저장
            if(flag) answer[i] = 1;
        }
        
        return answer;
    }
}