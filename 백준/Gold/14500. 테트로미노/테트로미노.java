import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, ans = Integer.MIN_VALUE;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				visited[i][j] = true;
				dfs2(i, j, 0, map[i][j]);
				visited[i][j] = false;
				bfs(i, j);
			}
		}
		System.out.println(ans);
	}
	private static void dfs2(int r, int c, int cnt, int sum) {
		if(cnt == 3) {
			ans = Math.max(ans, sum);
			return;
		}
		for (int d = 0; d < 4; d++) {
			int nr = r + dx[d];
			int nc = c + dy[d];
			if(nr < 0 || nr >= N || nc < 0 || nc >= M || visited[nr][nc]) continue;
			visited[nr][nc] = true;
			dfs2(nr, nc, cnt + 1, sum + map[nr][nc]);
			visited[nr][nc] = false;
		}
	}
	private static void bfs(int i, int j) {
		int sum = 0;
		sum += map[i][j];
		int cnt = 0;
		for (int d = 0; d < 4; d++) {
			int nr = i + dx[d];
			int nc = j + dy[d];
			if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
			sum += map[nr][nc];
			cnt++;
		}
		if(cnt == 4) {
			for (int d = 0; d < 4; d++) {
				ans = Math.max(ans, sum - map[i + dx[d]][j + dy[d]]);
			}
		} else if(cnt == 3) {
			ans = Math.max(ans, sum);
		}
	}
}