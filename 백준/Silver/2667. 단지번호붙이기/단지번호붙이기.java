import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		char[][] map = new char[N][N];
		boolean[][] visited = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		int num = 0;
		int[] dx = {-1, 0, 1, 0};
		int[] dy = {0, 1, 0, -1};
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] == '0' || visited[i][j]) continue;
				visited[i][j] = true;
				Queue<int[]> q = new ArrayDeque<>();
				q.offer(new int[] {i, j});
				int cnt = 1;
				while(!q.isEmpty()) {
					int size = q.size();
					for (int k = 0; k < size; k++) {
						int[] temp = q.poll();
						for (int d = 0; d < 4; d++) {
							int nr = temp[0] + dx[d];
							int nc = temp[1] + dy[d];
							if(nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc] || map[nr][nc] == '0') continue;
							q.offer(new int[] {nr, nc});
							cnt++;
							visited[nr][nc] = true;
						}
					}
				}
				list.add(cnt);
				num++;
			}
		}
		Collections.sort(list);
		sb.append(num + "\n");
		for (Integer cnt : list) {
			sb.append(cnt + "\n");
		}
		System.out.println(sb);
	}
}