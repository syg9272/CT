class Solution {
    public long solution(int[] sequence) {
        
        // 누적합 배열 만들기 
        long[] prefix = new long[sequence.length + 1];
        long maxSum = 0;    // 최대합
        long minSum = 0;    // 최소합

        for(int i = 0; i < sequence.length; i ++) {
            // 누적합으로 저장
            if(i % 2 == 0) {
                prefix[i + 1] = sequence[i] + prefix[i];
            } else {
                prefix[i + 1] = sequence[i] * -1 + prefix[i];
            }
            maxSum = Math.max(maxSum, prefix[i + 1]);   // 최대합 저장
            minSum = Math.min(minSum, prefix[i + 1]);   // 최소합 저장
        }
        
        return maxSum - minSum;
    }
}