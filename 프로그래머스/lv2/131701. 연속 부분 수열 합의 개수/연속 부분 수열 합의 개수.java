import java.util.*;

class Solution {
    public int solution(int[] elements) {
        // int answer = 0;
        
        // long[] num = new long[elements.length * 2];
        long[] prefixSum = new long[elements.length * 3 + 1];
        
        // // 원형 배열을 구현하기 위해 길이 두 배 배열 생성
        // for(int i = 0; i < num.length; i ++) {
        //     num[i] = elements[i % elements.length];
        // }
        // 누적합 저장
        for(int i = elements.length; i < prefixSum.length; i ++) {
            prefixSum[i] = prefixSum[i - 1] + elements[i % elements.length];
        }
        
        // System.out.println(Arrays.toString(prefixSum));
        
        // boolean[] checked = new boolean[1000 * 1000 + 1];
        HashSet<Long> hSet = new HashSet<>();
        
        // 1 - element.length 까지 부분 수열 합 구하기
        for(int i = 1; i <= elements.length; i ++) {
            for(int j = elements.length; j < prefixSum.length; j ++) {
                // System.out.println(j + " " + (j - i));
                // System.out.print(prefixSum[j] - prefixSum[j - i] + " ");
                // checked[(int) (prefixSum[j] - prefixSum[j - i])] = true;
                hSet.add(prefixSum[j] - prefixSum[j - i]);
            }
        }
        
        return hSet.size();
    }
}