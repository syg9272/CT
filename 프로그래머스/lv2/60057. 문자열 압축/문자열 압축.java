class Solution {
    public int solution(String s) {
        String temp1 = "";	// 현재 문자
		String temp2 = "";	// 다음 문자
		String temp3 = "";	// 비교하지 못하고 마지막에 남은 짜투리
		
		int answer = s.length();	// 최악의 경우로 초기화
		
		int cnt;	// 같은 문자 반복된 개수
		String cup = "";	// 반복처리한 결과 문자열
		
		for (int i = 1; i <= s.length()/2; i++) {	// 반복 문자는 최대 n/2개 단위
			// 단위가 바뀔 때마다 사용한 변수 초기화
			cnt = 1;	// 최악의 경우 1개 단위이기 때문에 1로 초기화
			cup = "";
			for (int j = 0; j <= s.length() - (i*2); j += i) {	// 다음 문자를 미리 받아오기 때문에 cup.length() - (i*2) 까지 돌아야 함
				temp1 = s.substring(j, j+i);	// 현재 문자 잘라서 temp1에 저장
				temp2 = s.substring(j+i, j+(i*2));	// 다음 문자 잘라서 temp2에 저장
				temp3 = s.substring(j+(i*2), s.length());	// temp3은 마지막에 비교되지 못하고 남은 문자열
				if(temp1.equals(temp2)) {	// 만약 현재 문자와 다음 문자가 같다면
					cnt ++;	// cnt를 1 더하고 다음 비교하러 가기
				}else {	// 현재 문자와 다음 문자가 다르다면
					if(cnt > 1) {	// 근데 만약 앞에까지 같은 문자가 있었다면 ( ex) a a b c ) 에서 a a 비교 후 cnt++ -> a b 비교인 상황
						cup += cnt + temp1;	//  결과 저장하는 문자열에 같은 문자 개수와 해당 문자 추가
					}else {	// 만약 다른 문자라면 ( ex) a b c ) 에서 a b 비교 후 b c 비교하는 상황
						cup += temp1;	// 결과 저장하는 문자열에 현재 문자 저장
					}
					cnt = 1;	// 새로운 문자가 나왔기 때문에 cnt 초기화
				}
			}
			// 단위마다 비교가 끝나면 마지막 비교 결과 저장
			if(cnt > 1) {	// 만약 앞에까지 같은 문자가 있었다면
				cup += cnt + temp2 + temp3;	// 결과 저장하는 문자열에 개수와 해당 문자 추가, 비교되지 못한 짜투리 문자열도 추가 !
			}else {	// 만약 다른 문자라면 현재 문자와 비교되지 못한 짜투리 문자열 추가 !
				cup += temp2 + temp3;
			}
			answer = Math.min(answer, cup.length()); // 하나의 비교단위가 끝날 때마다 결과 문자열 중 제일 짧은 문자열 길이 저장 
		}
        return answer;
    }
}