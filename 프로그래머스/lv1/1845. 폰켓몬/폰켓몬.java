import java.util.*;

class Solution {
    
    public int solution(int[] nums) {
        int answer = 0;
        int N = nums.length / 2;
        HashMap<Integer, Integer> hMap = new HashMap<>();
        for(int i = 0; i < nums.length; i ++) {
            hMap.put(nums[i], nums[i]);
        }
        return hMap.keySet().size() > N ? N : hMap.keySet().size();
    }
}