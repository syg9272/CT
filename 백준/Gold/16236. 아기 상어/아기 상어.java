import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		boolean[][] visited = new boolean[N][N];
		int startX = 0;
		int startY = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 9) {
					startX = i;
					startY = j;
				}
			}
		}
		
		int[] dx = {-1, 0, 0, 1};
		int[] dy = {0, -1, 1, 0};
		
		int shark = 2;
		int ans = 0;
		
		Queue<int[]> q = new ArrayDeque<>();
		Queue<int[]> fishQ = new ArrayDeque<>();
		q.offer(new int[] {startX, startY, 0, 0});
		visited[startX][startY] = true;
		
		while(!q.isEmpty()) {
			int size = q.size();
			int minH = N;
			int minW = N;
			for (int i = 0; i < size; i++) {
				int[] temp = q.poll();
				for (int d = 0; d < 4; d++) {
					int nx = temp[0] + dx[d];
					int ny = temp[1] + dy[d];
					if(nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny] || map[nx][ny] > shark) continue;
					if(map[nx][ny] == shark || map[nx][ny] == 0) {
						visited[nx][ny] = true;
						q.offer(new int[] {nx, ny, temp[2], temp[3] + 1});
					}
					else if (map[nx][ny] < shark) {
						minH = Math.min(minH, nx);
						fishQ.offer(new int[] {nx, ny, temp[2], temp[3]});
						visited[nx][ny] = true;
					}
				}
			}
			if(fishQ.size() == 0) continue;
			int s = fishQ.size();
			for (int i = 0; i < s; i++) {					
				int[] num = fishQ.poll();
				if(num[0] == minH) {
					minW = Math.min(minW, num[1]);
					fishQ.offer(new int[] {num[0], num[1], num[2], num[3]});
				}
			}
			if(fishQ.size() > 1) {
				int s2 = fishQ.size();
				for (int i = 0; i < s2; i++) {					
					int[] num = fishQ.poll();
					if(num[1] == minW) {
						fishQ.offer(new int[] {num[0], num[1], num[2], num[3]});
					}
				}
			}
			int[] arr = fishQ.poll();
			map[arr[0]][arr[1]] = 9;
			map[startX][startY] = 0;
			startX = arr[0];
			startY = arr[1];
			visited = new boolean[N][N];
			visited[arr[0]][arr[1]] = true;
			ans += arr[3] + 1;
			q.clear();
			if(arr[2] + 1 == shark) {
				shark++;
				q.offer(new int[] {arr[0], arr[1], 0, 0});
			}else q.offer(new int[] {arr[0], arr[1], arr[2] + 1, 0});
		}
		System.out.println(ans);
	}
}