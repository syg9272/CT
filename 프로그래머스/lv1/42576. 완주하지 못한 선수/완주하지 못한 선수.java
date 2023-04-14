import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        HashMap<String, Integer> hMap = new HashMap<>();
        for(int i = 0; i < completion.length; i ++) {
            if(hMap.containsKey(completion[i])) {
                hMap.put(completion[i], hMap.get(completion[i]) + 1);
            } else {
                hMap.put(completion[i], 1);
            }
        }
        for(int i = 0; i < participant.length; i ++) {
            if(hMap.containsKey(participant[i]) && hMap.get(participant[i]) > 0) {
                hMap.put(participant[i], hMap.get(participant[i]) - 1);
            } else {
                answer = participant[i];
                break;
            }
        }
        return answer;
    }
}