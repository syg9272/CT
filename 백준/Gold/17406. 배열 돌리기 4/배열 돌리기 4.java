import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[] dx = {1, 0, -1, 0};	// 하 -> 우 -> 상 -> 좌
	static int[] dy = {0, 1, 0, -1};
	static int[][] map;
	static int[][] map2;
	static int minCnt = Integer.MAX_VALUE;
	static boolean[] isSelected;
	static int[][] idx;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		map2 = new int[N][M];
		for (int i = 0; i < N; i++) {
			String[] temp = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(temp[j]);
			}
		}
		for (int i = 0; i < N; i++) {	// 2차원 배열 깊은 복사 (한 가지 경우로 회전한 뒤 다음 경우로 회전하기 전에 원래 배열로 되돌리기 위한 용도)		
			System.arraycopy(map[i], 0, map2[i], 0, map2[i].length);
		}
		int r, c, s;
		idx = new int[R][4];
		isSelected = new boolean[R];
		int[][] num = new int[R][4];
		for (int i = 0; i < R; i++) {	// 인덱스 정보 저장
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			s = Integer.parseInt(st.nextToken());
			
			idx[i][0] = r - s -1;
			idx[i][1] = c - s -1;
			idx[i][2] = r + s -1;
			idx[i][3] = c + s -1;
		}
		perm(num, 0);
		System.out.println(minCnt);
	}
	
	private static void perm(int[][] num, int r) {	// 순열로 모든 경우에 따라 회전
		if(r == idx.length) {
			for (int i = 0; i < map.length; i++) {	// 2차원 배열 깊은 복사
				System.arraycopy(map2[i], 0, map[i], 0, map[i].length);
			}
			int x1, y1, x2, y2;
			for (int i = 0; i < num.length; i++) {
				x1 = num[i][0];
				y1 = num[i][1];
				x2 = num[i][2];
				y2 = num[i][3];
				while(true) {
					if(x1 >= x2 | y1 >= y2) break;
					rotation(x1, y1, x2, y2);
					x1++;
					y1++;
					x2--;
					y2--;
				}
			}
			// 최대 카운트 저장 
			int cnt = count();
			minCnt = Math.min(minCnt, cnt);
			
			return;
		}
		for (int i = 0; i < idx.length; i++) {
			if(!(isSelected[i])) {
				num[r] = Arrays.copyOf(idx[i], idx[i].length);
				isSelected[i] = true;
				perm(num,  r + 1);
				isSelected[i] = false;
			}
		}
	}

	private static int count() {	// 행 최대 합 반환
		int minSum = Integer.MAX_VALUE;
		for (int i = 0; i < map.length; i++) {
			int sum = 0;
			for (int j = 0; j < map[i].length; j++) {
				sum += map[i][j];
			}
			minSum = Math.min(minSum, sum);
		}
		return minSum;
	}
	
	private static void rotation(int x1, int y1, int x2, int y2) {	// 회전 : 역방향으로 뒤에 값 댕겨서 저장
		int temp = map[x1][y1];
		int r = x1;
		int c = y1;
		for (int i = 0; i < 4; i++) {
			r += dx[i];
			c += dy[i];
			while(r >= x1 && r <= x2 && c >= y1 && c <= y2) {
				map[r - dx[i]][c - dy[i]] = map[r][c];
				r += dx[i];
				c += dy[i];
			}
			r -= dx[i];
			c -= dy[i];
			if(i != 3) map[r][c] = map[r + dx[i + 1]][c + dy[i + 1]];	
		}
		map[r - dx[3]][c - dy[3]] = temp;
	}
}