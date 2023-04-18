import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] map;
	static int ans = Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		map = new int[10][10];
		
		for (int i = 0; i < 10; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 10; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		boolean find = true;
		outline : for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if(map[i][j] == 1) {
					find = false;
					colorPaper(i, j, 5, 5, 5, 5, 5, 0);
					break outline;
				}
			}
		}
		if(find) ans = 0;
		System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
	}
	private static void colorPaper(int r, int c, int p1, int p2, int p3, int p4, int p5, int cnt) {
		if(r == 9 && c > 9) {
			ans = Math.min(ans, cnt);
			return;
		}
		
		if(c > 9) {
			colorPaper(r + 1, 0, p1, p2, p3, p4, p5, cnt);
			return;
		}

		if(ans <= cnt) return;
		
		if(map[r][c] == 1) {
			if(check(r, c, 5) && p5 > 0) {
				fill(r, c, 5);
				colorPaper(r, c + 5, p1, p2, p3, p4, p5 - 1, cnt + 1);
				remove(r, c, 5);
			}
			if(check(r, c, 4) && p4 > 0) {
				fill(r, c, 4);
				colorPaper(r, c + 4, p1, p2, p3, p4 - 1, p5, cnt + 1);
				remove(r, c, 4);
			}
			if(check(r, c, 3) && p3 > 0) {
				fill(r, c, 3);
				colorPaper(r, c + 3, p1, p2, p3 - 1, p4, p5, cnt + 1);
				remove(r, c, 3);
			}
			if(check(r, c, 2) && p2 > 0) {
				fill(r, c, 2);
				colorPaper(r, c + 2, p1, p2 - 1, p3, p4, p5, cnt + 1);
				remove(r, c, 2);
			}
			if(check(r, c, 1) && p1 > 0) {
				fill(r, c, 1);
				colorPaper(r , c + 1, p1 - 1, p2, p3, p4, p5, cnt + 1);
				remove(r, c, 1);
			}
		}else {			
			colorPaper(r, c + 1, p1, p2, p3, p4, p5, cnt);
		}
	}
	
	private static boolean check(int r, int c, int len) {
		for (int i = r; i < r + len; i++) {
			for (int j = c; j < c + len; j++) {
				if(i >= 10 || j >= 10) return false;
				if(map[i][j] == 0) return false;
			}
		}
		return true;
	}
	
	private static void fill(int r, int c, int len) {
		for (int i = r; i < r + len; i++) {
			for (int j = c; j < c + len; j++) {
				map[i][j] = 0;
			}
		}
	}
	
	private static void remove(int r, int c, int len) {
		for (int i = r; i < r + len; i++) {
			for (int j = c; j < c + len; j++) {
				map[i][j] = 1;
			}
		}
	}
}
