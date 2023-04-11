import java.util.HashMap;

class Solution {
    public String[] solution(String[] record) {
        HashMap<String, String> map = new HashMap<>();
		int cnt = 0;
		for (int i = 0; i < record.length; i++) {
			String[] temp = record[i].split(" ");
			if(temp[0].equals("Enter")) {
				map.put(temp[1], temp[2]);
				cnt ++;
			}else if(temp[0].equals("Change")) {
				map.put(temp[1], temp[2]);
			}else if(temp[0].equals("Leave"))cnt++;
		}
		String[] answer = new String[cnt];
		for (int j = 0, index = 0; j < record.length; j++) {
			String[] temp = record[j].split(" ");
			if(temp[0].equals("Enter")) {
				answer[index++] = map.get(temp[1]) + "님이 들어왔습니다.";
			}else if(temp[0].equals("Leave")) {
				answer[index++] = map.get(temp[1]) + "님이 나갔습니다.";
			}
		}
		return answer;
    }
}