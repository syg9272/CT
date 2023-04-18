import java.util.Scanner;

public class Main {
	static char[][] map;
	static int R, C, ans;
	static int[] dr = {-1, 0, 1};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();
		map = new char[R][];
		for (int i = 0; i < R; i++) map[i] = sc.next().toCharArray();
		for (int i = 0; i < R; i++) dfs(i, 0);
		System.out.println(ans);
	}
	private static boolean dfs(int r, int c) {
		if(c == C - 1) {
			ans++;
			return true;
		}
		map[r][c] = 'x';
		int nr, nc = c + 1;
		for (int d = 0; d < 3; d++) {
			nr = r + dr[d];
			if(nr >= 0 && nr < R && map[nr][nc] == '.') {
				if (dfs(nr, nc)) return true;
			}
		}
		return false;
	}
}