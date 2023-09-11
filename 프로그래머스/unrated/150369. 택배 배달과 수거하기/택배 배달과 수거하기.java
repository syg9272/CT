class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        // 배달, 수거 완료 개수 누적합 저장 배열
        long[] d = new long[n + 1];   // 배달
        long[] p = new long[n + 1];   // 수거
        // 가장 먼 곳부터 처리한다 (효율)
        int idx = 1;    // 누적합 인덱스
        for(int i = n - 1; i >= 0; i --) {
            // 배달 || 수거 자리가 부족한 경우
            if(deliveries[i] > d[idx] + d[idx - 1] || pickups[i] > p[idx] + p[idx - 1]) {
                // 최대 박스 수에 따른 왕복 추가 거리 계산
                long x = Math.abs(Math.min(d[idx] + d[idx - 1] - deliveries[i], p[idx] + p[idx - 1] - pickups[i]));
                if(x % cap != 0) x = x / cap + 1;
                else x /= cap;
                answer += (i + 1) * 2 * x; // 왕복거리 추가
                d[idx] += cap * x;
                p[idx] += cap * x;
            }
            // 누적합 갱신
            d[idx] += d[idx - 1] - deliveries[i];
            p[idx] += p[idx - 1] - pickups[i];
            // System.out.println(d[idx] + " " + p[idx] + " " + answer);
            idx ++;
        }
        
        return answer;
    }
}