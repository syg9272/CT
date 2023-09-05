import java.util.*;

class Solution {
    public int solution(int[][] scores) {
        int answer = -1;
        
        // 완호 점수 저장
        int wanhoA = scores[0][0];
        int wanhoB = scores[0][1];
        int wanhoSum = scores[0][0] + scores[0][1];
        
        // 점수별 내림차순 정렬
        Arrays.sort(scores, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if(a[0] == b[0]) {
                    return b[1] - a[1];
                } else {
                    return b[0] - a[0];
                }
            }
            
        });
        
        // 최대값 저장
        int maxA = scores[0][0];
        
        // 근무 태도 점수별 최대 동료 평가 점수
        int score = maxA + 1;    // 근무 태도 최대값
        int[] maxGroup = new int[score];
        for(int i = 0; i < scores.length; i ++) {
            if(scores[i][0] < score) {
                maxGroup[scores[i][0]] = scores[i][1];
                score = scores[i][0];
            }
        }
        
        // 완호가 인센티브를 받지 못하는 경우
        boolean flag = true;
        if(wanhoA == maxA) {
            flag = true;
        } else {
            for(int i = wanhoA + 1; i < maxGroup.length; i ++) {
                if(maxGroup[i] > wanhoB) {
                    flag = false;
                    break;
                }
            }
        }
        if(!flag) return -1;
        
        // 점수합 정렬
        Arrays.sort(scores, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return (b[0] + b[1]) - (a[0] + a[1]);
            }
            
        });
        
        // 배열 순회하며 인센티브 & 등수 검사하기
        int rank = 0;   // 등수
        int sum = Integer.MAX_VALUE;    // 점수합
        for(int i = 0; i < scores.length; i ++) {
            // 인센티브가 없는 사원 pass
            boolean isInsen = true;
            if(scores[i][0] == maxA) {
                isInsen = true;
            } else {
                for(int j = scores[i][0] + 1; j < maxGroup.length; j ++) {
                    if(maxGroup[j] > scores[i][1]) {
                        isInsen = false;
                        break;
                    }
                }
            }
            if(!isInsen) continue;
            // 이번 차례 사원의 점수
            int temp = scores[i][0] + scores[i][1];
            // 이전 점수와 비교하며 등수 늘리기
            if(temp <= sum) {
                rank++;
                sum = temp;
            }
            // 완호 차례가 왔을 때 break
            if(temp == wanhoSum) break;
        }
        
        
        return rank;
    }
}