import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] dx = {0, -1, 0, 1};
	static int[] dy = {1, 0, -1, 0};
	static int N, ans = Integer.MAX_VALUE;
	static int[][] map;
	static boolean[][] visited, visited2;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		visited = new boolean[N][N];
		visited2 = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int n = 2;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] == 1 && !visited[i][j]) {
					findIsland(i, j, n);
					n++;
				}
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] > 0) {
					for (int d = 0; d < 4; d++) {
						int nr = i + dx[d];
						int nc = j + dy[d];
						if(nr < 0 || nr >= N || nc < 0 || nc >= N || visited2[nr][nc]) continue;
						if(map[nr][nc] == 0) {
							visited2[i][j] = true;
							bridge(i, j);
							visited2 = new boolean[N][N];
                            break;
						}
					}
				}
			}
		}
		System.out.println(ans);
	}
	private static void bridge(int r, int c) {	// 다리 개수 세는 함수
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {r, c, 0});
		visited2[r][c] = true;
		
		while(!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				int[] temp = q.poll();
                if(temp[2] > ans) continue;
				for (int d = 0; d < 4; d++) {
					int nr = temp[0] + dx[d];
					int nc = temp[1] + dy[d];
					if(nr < 0 || nr >= N || nc < 0 || nc >= N || visited2[nr][nc]) continue;
					if(map[nr][nc] > 0 && map[nr][nc] != map[r][c]) ans = Math.min(ans, temp[2]);
					if(map[nr][nc] == 0) {
						visited2[nr][nc] = true;
						q.offer(new int[] {nr, nc, temp[2] + 1});
					}
				}
			}
		}
	}
	
	private static void findIsland(int r, int c, int n) {	// 섬 분리하는 함수
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {r, c});
		map[r][c] = n;
		visited[r][c] = true;
		
		while(!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				int[] temp = q.poll();
				for (int d = 0; d < 4; d++) {
					int nr = temp[0] + dx[d];
					int nc = temp[1] + dy[d];
					if(nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc] || map[nr][nc] == 0) continue;
					map[nr][nc] = n;
					visited[nr][nc] = true;
					q.offer(new int[] {nr, nc});
				}
			}
		}
	}
}