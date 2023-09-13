import java.util.*;

class Solution {
    public int solution(int storey) {
        int answer = 0;
        
        while(storey > 0) {
            // System.out.println(answer + " " + storey);
            if(storey % 10 > 5) {
                answer += 10 - (storey % 10);
                storey += 10 - (storey % 10);
            } else if(storey % 10 < 5){
                answer += storey % 10;
                storey -= storey % 10;
            } else {
                if(storey % 100 > 50) {
                    storey += 5;
                } else {
                    storey -= 5;
                }
                answer += 5;
            }
            storey /= 10;
        }
        
        return answer;
    }
}