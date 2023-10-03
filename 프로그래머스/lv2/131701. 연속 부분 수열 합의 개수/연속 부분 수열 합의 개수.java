class Solution {
    public int solution(int[] elements) {
        // 누적합 저장 배열 선언 (원형 배열 구현을 위해 elements.length * 2, 누적합 저장을 위해 + elements.length)
        long[] prefixSum = new long[elements.length * 3 + 1];
        // 누적합 저장
        for(int i = elements.length; i < prefixSum.length; i ++) {
            prefixSum[i] = prefixSum[i - 1] + elements[i % elements.length];
        }
        // 중복 제거를 위해 해시셋에 부분 수열 합 저장
        boolean[] checked = new boolean[1000 * 1000 + 1];
        // 1 - element.length 까지 부분 수열 합 구하기
        for(int i = 1; i <= elements.length; i ++) {
            for(int j = elements.length; j < prefixSum.length; j ++) {
                checked[(int) (prefixSum[j] - prefixSum[j - i])] = true;
            }
        }
        // 체크된 경우 카운트
        int answer = 0;
        for(int i = 0; i < checked.length; i ++) {
            if(checked[i]) answer ++;
        }
        // 해시셋 사이즈 반환
        return answer;
    }
}