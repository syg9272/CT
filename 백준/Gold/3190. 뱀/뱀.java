import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		
		int[][] map = new int[N][N];
		
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			map[r - 1][c - 1] = 1;
		}
		
		int X = Integer.parseInt(br.readLine());
		
		int[] dx = {0, 1, 0, -1};
		int[] dy = {1, 0, -1, 0};
		int time = 1;
		int d = 0;
		int headX = 0, headY = 0;
		int tailX = 0, tailY = 0;
		boolean flag = false;
		
		int body = -1;
		map[0][0] = -1;
		
		outline : for (int i = 0; i < X; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			while(time <= x) {
				// 벽 또는 자기자신과 부딪히면 끝내기
				if(headX + dx[d] < 0 || headX + dx[d] >= N || headY + dy[d] < 0 || headY + dy[d] >= N || 
						map[headX + dx[d]][headY + dy[d]] < 0) {
					flag = true;
					break outline;
				}
				// 이동한 칸에 사과가 있다면, 사과 없애고 머리 저장
				if(map[headX + dx[d]][headY + dy[d]] == 1) {
					map[headX + dx[d]][headY + dy[d]] = --body;
				} else {
					map[headX + dx[d]][headY + dy[d]] = --body;
					for (int j = 0; j < 4; j++) {
						int nr = tailX + dx[j];
						int nc = tailY + dy[j];
						if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
						if(map[nr][nc] + 1 == map[tailX][tailY]) {
							map[tailX][tailY] = 0;
							tailX = nr;
							tailY = nc;
							break;
						}
					}
				}					
				headX += dx[d];
				headY += dy[d];
				time++;
			}
			String dir = st.nextToken();
			
			if(dir.equals("D")) {
				if(d == 3) d = 0;
				else d++;
			} else {
				if(d == 0) d = 3;
				else d--;
			}
		}
		if(flag) System.out.println(time);
		else {
			while(true) {
				// 벽 또는 자기자신과 부딪히면 끝내기
				if(headX + dx[d] < 0 || headX + dx[d] >= N || headY + dy[d] < 0 || headY + dy[d] >= N || 
						map[headX + dx[d]][headY + dy[d]] < 0) {
					System.out.println(time);
					return;
				}
				// 이동한 칸에 사과가 있다면, 사과 없애고 머리 저장
				if(map[headX + dx[d]][headY + dy[d]] == 1) {
					map[headX + dx[d]][headY + dy[d]] = --body;
				} else {
					map[headX + dx[d]][headY + dy[d]] = --body;
					for (int j = 0; j < 4; j++) {
						int nr = tailX + dx[j];
						int nc = tailY + dy[j];
						if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
						if(map[nr][nc] + 1 == map[tailX][tailY]) {
							map[tailX][tailY] = 0;
							tailX = nr;
							tailY = nc;
						}
					}
				}					
				headX += dx[d];
				headY += dy[d];
				time++;
			}
		}
	}
}