import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	static char[][] map;
	static boolean[][] visited, visited2;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	static int ans, ans2, N;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new char[N][N];
		visited = new boolean[N][N];
		visited2 = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {				
				if(!visited[i][j]) {
					visited[i][j] = true;
					color(i, j, 0);
				}
				if(!visited2[i][j]) {
					visited2[i][j] = true;
					color(i, j, 1);
				}
			}
		}
		System.out.println(ans + " " + ans2);
	}
	private static void color(int r, int c, int type) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {r, c});
		
		while(!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				int[] temp = q.poll();
				for (int d = 0; d < 4; d++) {
					int nx = temp[0] + dx[d];
					int ny = temp[1] + dy[d];
					if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
					if(type == 0 && visited[nx][ny]) continue;
					if(type == 1 && visited2[nx][ny]) continue;
					if(map[nx][ny] == map[r][c]) {
						if(type == 0) visited[nx][ny] = true;
						else visited2[nx][ny] = true;
						q.offer(new int[] {nx, ny});
					}else if(map[nx][ny] != map[r][c] && map[nx][ny] != 'B' && map[r][c] != 'B' && type == 1) {
						if(map[nx][ny] == (map[r][c] == 'R' ? 'G' : 'R')) {
							visited2[nx][ny] = true;
							q.offer(new int[] {nx, ny});
						}
					}
				}
			}
		}
		if(type == 0) ans++;
		else ans2++;
	}
}