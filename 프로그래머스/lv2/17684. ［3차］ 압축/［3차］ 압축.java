import java.util.*;

class Solution {
    public int[] solution(String msg) {
        int[] answer = new int[msg.length()];
        List<String> list = new ArrayList<>();
        for(int i = 0; i < 26; i ++) {
            list.add(String.valueOf((char)(i + 65)));
        }
        String[] str = msg.split("");
        // 색인 번호 추가할 인덱스
        int i = 0;
        // 입력할 글자 인덱스
        int idx = 0;
        
        while(true) {
            // 입력할 글자 인덱스가 벗어날 경우 break
            if(idx >= str.length) break;
            String prev = str[idx];
            idx++;
            
            while(true) {
                // 인덱스 벗어날 경우 이전까지 저장된 문자열 색인 번호 등록
                if(idx >= str.length) {
                    answer[i++] = list.indexOf(prev) + 1;
                    list.add(prev);
                    break;
                }
                String temp = prev + str[idx];
                // 인덱스 벗어나지 않을 경우 사전에 등록되었을 때 다음 문자열 추가해서 다시 검사
                if(list.indexOf(temp) >= 0) {
                    prev += str[idx];
                    idx++;
                } else { // 사전에 등록되지 않았을 경우 이전 문자열까지의 색인 번호 등록 & 사전에 추가
                    answer[i++] = list.indexOf(prev) + 1;
                    list.add(temp);
                    break;
                } 
            }
        }
        int cnt = 0;
        for(int j = 0; j < answer.length; j++) {
            if(answer[j] == 0) break;
            cnt++;
        }
        int[] arr = new int[cnt];
        for(int j = 0; j < cnt; j++) {
            arr[j] = answer[j];
        }
        return arr;
    }
}