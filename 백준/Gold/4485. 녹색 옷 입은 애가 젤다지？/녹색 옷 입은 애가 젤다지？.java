import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int ans, N;
    static int[][] map, map2;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int n = 1;
		while(true) {
            ans = Integer.MAX_VALUE;
			N = Integer.parseInt(br.readLine());
			if(N == 0) break;
			map = new int[N][N];
			map2 = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
                    map2[i][j] = Integer.MAX_VALUE;
				}
			}
			map2[0][0] = map[0][0];
			
			Queue<int[]> q = new ArrayDeque<>();
			q.offer(new int[] {0, 0, map[0][0]});
			
			while(!q.isEmpty()) {
				int size = q.size();
				for (int i = 0; i < size; i++) {
					int[] temp = q.poll();
					if(temp[0] == N - 1 && temp[1] == N - 1) {
						ans = Math.min(ans, temp[2]);
					}
					for (int d = 0; d < 4; d++) {
						int nr = temp[0] + dx[d];
						int nc = temp[1] + dy[d];
						if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
						if(map2[nr][nc] <= temp[2] + map[nr][nc]) continue;
						q.offer(new int[] {nr, nc, temp[2] + map[nr][nc]});
						map2[nr][nc] = temp[2] + map[nr][nc];
					}
				}
			}
            sb.append("Problem " + n++ + ": " + ans + "\n");
		}
        System.out.println(sb);
	}
}