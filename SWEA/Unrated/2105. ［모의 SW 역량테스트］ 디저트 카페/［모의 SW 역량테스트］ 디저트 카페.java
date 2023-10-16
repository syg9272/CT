import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int[] dx = {1, 1, -1, -1};
	static int[] dy = {1, -1, -1, 1};
	static int N, maxAns, x, y;
	static int[][] map;
	static boolean[] dessert;
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("data/2105.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			maxAns = -1;	// 최소값 초기화
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			
			for (int i = 0; i < N; i++) {	// 맵 입력받기
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int i = 0; i < N-2; i++) {	// 모든 가게에 대해서 완탐 (사각형을 만들 수 있는 범위로 지정)
				for (int j = 1; j < N-1; j++) {
					dessert = new boolean[101];		// 디저트 종류 체크 초기화
					x = i;	// 원점 설정
					y = j;	// 원점 설정
					dfs(i, j, -1, 0, 1);	// dfs 돌리기
				}
			}
			sb.append("#" + t + " " + maxAns + "\n");
		}
		System.out.println(sb);
	}
	private static void dfs(int r, int c, int state, int change, int count) {
		for (int d = 0; d < 4; d++) {	// 4방탐색 : 시계방향
			int nx = r + dx[d];	
			int ny = c + dy[d];
			if(nx < 0 || nx >= N || ny < 0 || ny >= N || dessert[map[nx][ny]]) continue;	// 인덱스가 유효하지 않을 경우 무시, 종류가 겹치면 무시
			if(nx < x) continue;	// 원점 위로는 이미 탐색완료기 때문에 무시
			if(change == 4 && state != d) continue;	// 이미 4각형이 되었는데 방향을 한번 더 틀면 무시

			if(change == 3 && nx == x && ny == y && state != d) {	// 만약 방향을 3번 틀었는데 다음 방향으로 한칸 움직였을 때 원점이면 최대값 갱신, 이때 방향이 한번 더 꺾여야 함(state != d)
				maxAns = Math.max(maxAns, count);
				return;
			}
			if(change == 4 && nx == x && ny == y) {	// 만약 방향을 4번 틀었는데 다음 칸이 원점일 때 최대값 갱신
				maxAns = Math.max(maxAns, count);
				return;
			}
			
			if(state != d) {	// 방향을 틀 경우
				if(change >= 4) continue;
				dessert[map[nx][ny]] = true;	// 디저트 종류 개수 체크
				dfs(nx, ny, d, change + 1, count + 1);
			}else {	// 방향을 안 틀 경우
				dessert[map[nx][ny]] = true;	// 디저트 종류 개수 체크
				dfs(nx, ny, d, change, count + 1);
			}
			dessert[map[nx][ny]] = false;	// 변경사항 돌려놓기
		}
	}
}