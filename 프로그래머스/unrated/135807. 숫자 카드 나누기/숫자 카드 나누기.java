import java.util.*;

class Solution {
    public static List<Integer> listDivisor;
    public int solution(int[] arrayA, int[] arrayB) {
        int answer = 0;
        // 배열 정렬 (오름차순)
        Arrays.sort(arrayA);
        Arrays.sort(arrayB);
        
        // 조건1 최대값 찾기
        answer = Math.max(answer, findMaxValue(arrayA, arrayB));
        // 조건2 최대값 찾기
        answer = Math.max(answer, findMaxValue(arrayB, arrayA));
        
        // 결과값 반환
        return answer;
    }
    
    // 공약수 찾기
    public void findDivisor(int[] card) {
        for(int j = 2; j * j <= card[0]; j ++) {  // 0번째 수(가장 작은 수)의 약수 list에 넣기
            if(card[0] % j == 0) {  // 약수일 경우, 나누는 요소와 몫 동시에 담기
                listDivisor.add(j);
                listDivisor.add(card[0] / j);
            }
        }
        listDivisor.add(card[0]);   // 자기 자신 약수로 넣기
        Collections.sort(listDivisor, Collections.reverseOrder());  // 내림차순 정렬 (최대값 찾아야 하므로)
        return;
    }
    
    // 모든 카드가 나누어 떨어져야 하는 경우
    public boolean findCanDiv(int[] canDiv, int divisor) {
        for(int num : canDiv) {
            if(num % divisor != 0) return false;
        }
        return true;
    }
    
    // 모든 카드가 나누어 떨어지면 안되는 경우
    public boolean findCantDiv(int[] cantDiv, int divisor) {
        for(int num : cantDiv) {
            if(num % divisor == 0) return false;
        }
        return true;
    }
    
    // 조건 만족하는 최대값 찾기
    public int findMaxValue(int[] canDiv, int[] cantDiv) {
        listDivisor = new ArrayList<>();    // 공약수 저장 리스트
        findDivisor(canDiv);    // 공약수 찾기
        
        for(Integer num : listDivisor) {    // 조건 만족하는 공약수 반환
            if(findCanDiv(canDiv, num) && findCantDiv(cantDiv, num)) return num;
        }
        return 0;   // 만족하는 공약수가 없을 경우 0 반환
    }
}