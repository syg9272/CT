import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M, ans = 11;
	static char[][] map;
	static int[] dx = {-1, 0, 1, 0};	// 상 -> 우 -> 하 -> 좌
	static int[] dy = {0, 1, 0, -1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		int redX = 0, redY = 0, blueX = 0, blueY = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == 'R') {
					redX = i;
					redY = j;
				}
				if(map[i][j] == 'B') {
					blueX = i;
					blueY = j;
				}
			}
		}
		
		dfs(redX, redY, blueX, blueY, 1);
		
		System.out.println(ans > 10 ? -1 : ans);
	}
	private static void dfs(int redX, int redY, int blueX, int blueY, int cnt) {
		if(cnt > 10) return;
		outline : for (int d = 0; d < 4; d++) {
			int bx = blueX + dx[d];
			int by = blueY + dy[d];
			while(map[bx][by] != '#') {
				if(map[bx][by] == 'O') continue outline;
				bx += dx[d];
				by += dy[d];
			}
			bx -= dx[d];
			by -= dy[d];
			int rx = redX + dx[d];
			int ry = redY + dy[d];
			while(map[rx][ry] != '#') {
				if(map[rx][ry] == 'O') {
					ans = Math.min(ans, cnt);
					return;
				}
				rx += dx[d];
				ry += dy[d];
			}
			rx -= dx[d];
			ry -= dy[d];
			
			
			if(rx == bx && ry == by) {
				switch (d) {
				case 0:	// 상
					if(redX < blueX) bx -= dx[d];
					else rx -= dx[d];
					break;
				case 1:	// 우
					if(redY < blueY) ry -= dy[d];
					else by -= dy[d];
					break;
				case 2:	// 하
					if(redX > blueX) bx -= dx[d];
					else rx -= dx[d];
					break;
				case 3:	// 좌
					if(redY > blueY) ry -= dy[d];
					else by -= dy[d];
					break;
				}
			}
			
			map[redX][redY] = '.';
			map[blueX][blueY] = '.';
			map[rx][ry] = 'R';
			map[bx][by] = 'B';
			
			dfs(rx, ry, bx, by, cnt + 1);
			
			map[redX][redY] = 'R';
			map[blueX][blueY] = 'B';
			map[rx][ry] = '.';
			map[bx][by] = '.';
		}
	}
}
