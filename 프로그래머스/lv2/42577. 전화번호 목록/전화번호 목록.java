import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        HashMap<String, Integer> hMap = new HashMap<>();
        for(int i = 0; i < phone_book.length; i ++) {
            hMap.put(phone_book[i], i);
        }
        for(int i = 0; i < phone_book.length; i ++) {
            for(int j = 0; j < phone_book[i].length(); j ++) {
                if(hMap.containsKey(phone_book[i].substring(0, j))) {
                    answer = false;
                    break;
                }
            }
        }
        return answer;
    }
}