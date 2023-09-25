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
            if(card[0] % j == 0) {
                listDivisor.add(j);
                listDivisor.add(card[0] / j);
            }
        }
        listDivisor.add(card[0]);
        Collections.sort(listDivisor, Collections.reverseOrder());
        return;
    }
    
    public boolean findCanDiv(int[] canDiv, int divisor) {
        for(int num : canDiv) {
            if(num % divisor != 0) return false;
        }
        
        return true;
    }
    
    public boolean findCantDiv(int[] cantDiv, int divisor) {
        for(int num : cantDiv) {
            if(num % divisor == 0) return false;
        }
        
        return true;
    }
    
    // 조건 만족하는 최대값 찾기
    public int findMaxValue(int[] canDiv, int[] cantDiv) {
        // 공약수 구하기
        listDivisor = new ArrayList<>();
        // 각 카드셋의 공약수 찾기
        findDivisor(canDiv);
        // 조건 만족하는 공약수 반환
        for(Integer num : listDivisor) {
            if(findCanDiv(canDiv, num) && findCantDiv(cantDiv, num)) return num;
        }
        
        return 0;
    }
}