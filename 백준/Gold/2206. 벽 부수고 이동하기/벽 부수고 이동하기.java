import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][M];
		boolean[][] visited = new boolean[N][M];
		boolean[][] visited2 = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			char[] c = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				map[i][j] = c[j] - '0';
			}
		}
		
		int[] dx = {-1, 0, 1, 0};
		int[] dy = {0, -1, 0, 1};
		
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {0, 0, 0, 1});
		visited[0][0] = true;
		
		while(!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				int[] temp = q.poll();
				if(temp[2] <= 1 && temp[0] == N - 1 && temp[1] == M - 1) {
					System.out.println(temp[3]);
					return;
				}
				for (int d = 0; d < 4; d++) {
					int nx = temp[0] + dx[d];
					int ny = temp[1] + dy[d];
					if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
					if(temp[2] > 1) continue;
					if(temp[2] == 0) {
						if(map[nx][ny] == 0 && !visited[nx][ny]) {
							visited[nx][ny] = true;
							q.offer(new int[] {nx, ny, temp[2], temp[3] + 1});
						}else if(map[nx][ny] == 1 && !visited[nx][ny]) {
							visited2[nx][ny] = true;
							q.offer(new int[] {nx, ny, temp[2] + 1, temp[3] + 1});
						}
					}else {
						if(map[nx][ny] == 0 && !visited2[nx][ny]) {
							visited2[nx][ny] = true;
							q.offer(new int[] {nx, ny, temp[2], temp[3] + 1});
						}
					}
				}
			}
		}
		System.out.println(-1);
	}
}