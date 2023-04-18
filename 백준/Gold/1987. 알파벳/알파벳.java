import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static Queue<Point> q;
	static char[][] map;
	static int R, C;
	static int ans = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		q = new LinkedList<>();
		boolean[] visited = new boolean[26];
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			String temp = st.nextToken();
			for (int j = 0; j < C; j++) {
				map[i][j] = temp.charAt(j);
			}
		}
		visited[map[0][0] - 'A'] = true;
		q.offer(new Point(0, 0));
		move(visited, 1);
		System.out.println(ans);
	}
	private static void move(boolean[] visited, int cnt) {
		ans = Math.max(ans, cnt);
		int r, c;
		while(!q.isEmpty()) {			
			Point p = q.poll();
			r = p.x; c = p.y;
			for (int i = 0; i < 4; i++) {
				if(r + dx[i] >= 0 && r + dx[i] < R && c + dy[i] >= 0 && c + dy[i] < C) {				
					if(!visited[map[r + dx[i]][c + dy[i]]-'A']) {
						visited[map[r + dx[i]][c + dy[i]]-'A'] = true;
						q.offer(new Point(r + dx[i], c + dy[i]));
						move(visited, cnt + 1);
						visited[map[r + dx[i]][c + dy[i]]-'A'] = false;
					}
				}
			}
		}
	}
}