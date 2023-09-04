import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int[][] map;
	static int[] hit = new int[10];
	static int N;
	static int ans = 0;
	public static void main(String[] args) throws NumberFormatException, IOException {
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 N = Integer.parseInt(br.readLine());
		 map = new int[N][10];
		 boolean[] isSelected = new boolean[10];
		 for (int i = 0; i < N; i++) {
			 String[] temp = br.readLine().split(" ");
			for (int j = 1; j < 10; j++) {
				map[i][j] = Integer.parseInt(temp[j - 1]);
			}
		 }
		 hit[1] = 1;
		 isSelected[1] = true;
		 hitter(2, isSelected);
		 System.out.println(ans);
	}
	private static void hitter(int r, boolean[] isSelected) {
		if(r == 10) {
			int temp = hit[1];
			hit[1] = hit[4];
			hit[4] = temp;
			ans = Math.max(ans, play());
			temp = hit[4];
			hit[4] = hit[1];
			hit[1] = temp;
			return;
		}
		for (int i = 2; i < 10; i++) {
			if(isSelected[i]) continue;
			hit[r] = i;
			isSelected[i] = true;
			hitter(r + 1, isSelected);
			isSelected[i] = false;
 		}
	}
	private static int play() {
		int[] ground;
		int out;
		int score = 0;
		int idx = 1;
		for (int i = 0; i < N; i++) {
			ground = new int[4];
			out = 0;
			while(out < 3) {
				if(idx == 10) idx -= 9;
				if(map[i][hit[idx]] == 0) {
					out++;
				}else {
					for (int j = 3; j >= 1; j--) {
						if(ground[j] == 0) continue;
						if(j + map[i][hit[idx]] >= 4) {
							score++;
						}else {
							ground[j + map[i][hit[idx]]] = ground[j];
						}
						ground[j] = 0;
					}
					if(map[i][hit[idx]] == 4) score++;
					else ground[map[i][hit[idx]]] = hit[idx];
				}
				idx++;
			}
		}
		return score;
	}
}