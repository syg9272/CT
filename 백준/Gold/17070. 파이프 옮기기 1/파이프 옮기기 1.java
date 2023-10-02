import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int[][] map;
	static int cnt = 0;
	static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			String[] temp = br.readLine().split(" ");
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(temp[j - 1]);
			}
		}
		if(map[N][N] == 1) System.out.println(0);
		else {
			movePipe(1, 2, 0);
			System.out.println(cnt);
		}
	}
	static void movePipe(int r, int c, int i) {	// r : 현재 행, c : 현재 열, i : 현재 파이프 모양(0 : 가로, 1 : 세로, 2 : 대각선)
		if(r == N && c == N) {	// 파이프를 끝까지 옮겼을 경우 cnt++
			cnt++;
			return;
		}
		switch (i) {	// 파이프 모양에 따라 움직일 수 있는 위치,,, 가로 : 가로, 대각선 	세로 : 세로, 대각선	대각선 : 가로, 세로, 대각선	
		case 0:
			if(c + 1 <= N && map[r][c + 1] == 0) {				
				movePipe(r, c + 1, 0);
			}
			if(r + 1 <= N && c + 1 <= N && map[r + 1][c] == 0 && map[r][c + 1] == 0 && map[r + 1][c + 1] == 0) {				
				movePipe(r + 1, c + 1, 2);
			}
			break;
		case 1:
			if(r + 1 <= N && map[r + 1][c] == 0) {				
				movePipe(r + 1, c, 1);
			}
			if(r + 1 <= N && c + 1 <= N && map[r + 1][c] == 0 && map[r][c + 1] == 0 && map[r + 1][c + 1] == 0) {				
				movePipe(r + 1, c + 1, 2);
			}
			break;
		case 2:
			if(c + 1 <= N && map[r][c + 1] == 0) {				
				movePipe(r, c + 1, 0);
			}
			if(r + 1 <= N && map[r + 1][c] == 0) {				
				movePipe(r + 1, c, 1);
			}
			if(r + 1 <= N && c + 1 <= N && map[r + 1][c] == 0 && map[r][c + 1] == 0 && map[r + 1][c + 1] == 0) {				
				movePipe(r + 1, c + 1, 2);
			}
			break;
		}
	}
}