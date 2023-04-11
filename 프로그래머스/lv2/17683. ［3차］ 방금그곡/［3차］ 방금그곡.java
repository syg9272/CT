import java.util.*;

class Solution {
    public String solution(String m, String[] musicinfos) {
        String answer = "(None)";
        int maxLen = 0;
        for(int i = 0; i < musicinfos.length; i ++) {
            String[] temp = musicinfos[i].split(",");
            int time = (Integer.parseInt(temp[1].split(":")[0]) * 60 + Integer.parseInt(temp[1].split(":")[1])) 
                - (Integer.parseInt(temp[0].split(":")[0]) * 60 + Integer.parseInt(temp[0].split(":")[1]));
            String[] note = temp[3].split("");
            String music = "";
            int idx = 0;
            for(int j = 0; j < time; j ++) {
                if(idx == note.length) idx = 0;
                if(note[idx].equals("#")) {
                    music += note[idx];
                    j --;
                    idx++;
                }else {
                    music += note[idx];
                    idx ++;
                }
            }
            if(idx == note.length) idx = 0;
            if(note[idx].equals("#")) music += "#";
            int l = 0;
            while(true) {
                if(l >= music.length()) break;
                int flag = music.indexOf(m, l);
                if(flag < 0) break;
                if((flag >= 0 && flag + m.length() == music.length()) || (flag >= 0 && music.charAt(flag + m.length()) != '#')) {
                    if(maxLen < time) {
                        answer = temp[2];
                        maxLen = time;
                    }
                    break;
                }
                l ++;
            }
        }
        
        return answer;
    }
}