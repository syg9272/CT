import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	public static class Star {
		int y;
		int x;
		public Star(int x, int y) {
			this.y = y;
			this.x = x;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		Star[] star = new Star[K];
		
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			star[i] = new Star(x, y);
		}
		
		int ans = 0;
		
		for (int i = 0; i < star.length; i++) {
			for (int j = 0; j < star.length; j++) {
				int y = star[j].y;
				int x = star[i].x;
				int cnt = 0;
				for (int r = 0; r < star.length; r++) {
					if(star[r].x >= x && star[r].x <= x + L && star[r].y >= y && star[r].y <= y + L) cnt++;
				}
				ans = Math.max(ans, cnt);
			}
		}
		System.out.println(K - ans);
	}
}