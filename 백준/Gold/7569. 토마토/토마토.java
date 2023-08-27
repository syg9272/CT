import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		
		int[][][] map = new int[N][M][H];
		boolean[][][] visited = new boolean[N][M][H];
		Queue<int[]> q = new ArrayDeque<>();
		
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0; k < M; k++) {
					map[j][k][i] = Integer.parseInt(st.nextToken());
					if(map[j][k][i] == 1) {
						q.offer(new int[] {j, k, i});
						visited[j][k][i] = true;
					}
				}
			}
		}
		
		int[] dx = {-1, 0, 1, 0, 0, 0};
		int[] dy = {0, -1, 0, 1, 0, 0};
		int[] dz = {0, 0, 0, 0, 1, -1};
		
		int time = 0;
		
		while(!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				int[] temp = q.poll();
				for (int d = 0; d < 6; d++) {
					int nr = temp[0] + dx[d];
					int nc = temp[1] + dy[d];
					int nk = temp[2] + dz[d];
					if(nr < 0 || nr >= N || nc < 0 || nc >= M || nk < 0 || nk >= H || visited[nr][nc][nk] || map[nr][nc][nk] == -1) continue;
					q.offer(new int[] {nr, nc, nk});
					visited[nr][nc][nk] = true;
				}
			}
			time++;
		}
		
		boolean flag = false;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < M; k++) {
					if(!visited[j][k][i] && map[j][k][i] >= 0) {
						flag = true;
						System.out.println(-1);
						System.exit(0);
					}
				}
			}
		}
		System.out.println(--time);
	}
}