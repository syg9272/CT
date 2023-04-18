import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, x, y, d1, d2, ans = Integer.MAX_VALUE;
	static int[][] map;
	static int[] population;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 1; i <= N - 2; i++) {
			for (int j = 2; j <= N - 1; j++) {
				x = i; y = j;
				selectD();
			}
		}
		System.out.println(ans);
	}
	private static void selectD() {
		for (int i = 1; i <= N - 2; i++) {
			for (int j = 1; j <= N - 2; j++) {
				if(x + i + j <= N && y - i >= 1 && y + j <= N) {					
					d1 = i; d2 = j;
					bfs();
				}
			}
		}
	}
	private static void bfs() {
		int[] cnt = new int[6];
		boolean[][] visited = new boolean[N + 1][N + 1];
		int[][] map2 = new int[N + 1][N + 1];
		
		for (int i = 0; i <= d1; i++) {
			visited[x + i][y - i] = true;
			visited[x + d2 + i][y + d2 - i] = true;
		}
		for (int i = 0; i <= d2; i++) {
			visited[x + i][y + i] = true;
			visited[x + d1 + i][y - d1 + i] = true;
		}
		
		outline : for (int j = y - d1 + 1; j < y + d2; j++) {
			boolean flag = false;
			for (int i = 1; i <= N; i++) {
				if(visited[i][j] && flag) continue outline;
				if(visited[i][j] && !flag) flag = true;
				if(!visited[i][j] && flag) visited[i][j] = true;
			}
		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if(visited[i][j]) {
					cnt[5] += map[i][j];
					continue;
				}
				if(i < x + d1 && j <= y) {
					map2[i][j] = 1;
					cnt[1] += map[i][j];
				}
				else if(i <= x + d2 && j > y) {
					map2[i][j] = 2;
					cnt[2] += map[i][j];
				}
				else if(i >= x + d1 && j < y - d1 + d2) {
					map2[i][j] = 3;
					cnt[3] += map[i][j];
				}
				else if(i > x + d2 && j >= y - d1 + d2) {
					map2[i][j] = 4;
					cnt[4] += map[i][j];
				}
			}
		}
		Arrays.sort(cnt);
		ans = Math.min(ans, cnt[5] - cnt[1]);
	}
}
