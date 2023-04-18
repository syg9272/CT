import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, ans = Integer.MIN_VALUE;
	static int[] dx = {0, -1, 0, 1, 0};
	static int[] dy = {0, 0, -1, 0, 1};
	public static class Number {
		int r;
		int c;
		int num;
		boolean move;
		public Number(int r, int c, int num, boolean move) {
			this.r = r;
			this.c = c;
			this.num = num;
			this.move = move;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		Number[][] newMap = new Number[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int num = Integer.parseInt(st.nextToken());
				if(num != 0) newMap[i][j] = new Number(i, j, num, false);
			}
		}
		
		dfs(0, newMap);
		
		System.out.println(ans);
		
	}
	private static void dfs(int cnt, Number[][] newMap) {
		if(cnt == 5) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(newMap[i][j] == null) continue;
					ans = Math.max(ans, newMap[i][j].num);
				}
			}
			return;
		}
		
		Number[][] map = new Number[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(newMap[i][j] == null) continue;
				map[i][j] = new Number(i, j, newMap[i][j].num, false);
			}
		}
		
//		위쪽 방향
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] == null) continue;
				map[i][j].r --;
				while(true) {
					if(map[i][j].r < 0) break;
					if(map[map[i][j].r][j] != null) {
						if(map[map[i][j].r][j].move) break;
						if(map[map[i][j].r][j].num == map[i][j].num) {
							map[map[i][j].r][j].num *= 2;
							map[map[i][j].r][j].move = true;
							map[i][j].move= true;
						}else break;
					}
					map[i][j].r --;
				}
				map[i][j].r++;
				if(map[i][j].move) map[i][j] = null;
				else {
					if(map[i][j].r != i) {			
						map[map[i][j].r][j] = new Number(map[i][j].r, j, map[i][j].num, false);
						map[i][j] = null;
					}
				}
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] == null) continue;
				map[i][j].move = false;
			}
		}
		
		dfs(cnt + 1, map);
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(newMap[i][j] == null) {
					map[i][j] = null;
					continue;
				}
				map[i][j] = new Number(i, j, newMap[i][j].num, false);
			}
		}
		
//		아래 방향
		for (int i = N - 1; i >= 0; i--) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] == null) continue;
				map[i][j].r ++;
				while(true) {
					if(map[i][j].r == N) break;
					if(map[map[i][j].r][j] != null) {
						if(map[map[i][j].r][j].move) break;
						if(map[map[i][j].r][j].num == map[i][j].num) {
							map[map[i][j].r][j].num *= 2;
							map[map[i][j].r][j].move = true;
							map[i][j].move= true;
						}else break;
					}
					map[i][j].r ++;
				}
				map[i][j].r --;
				if(map[i][j].move) map[i][j] = null;
				else {
					if(map[i][j].r != i) {						
						map[map[i][j].r][j] = new Number(map[i][j].r, j, map[i][j].num, false);
						map[i][j] = null;
					}
				}
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] == null) continue;
				map[i][j].move = false;
			}
		}
		
		dfs(cnt + 1, map);
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(newMap[i][j] == null) {
					map[i][j] = null;
					continue;
				}
				map[i][j] = new Number(i, j, newMap[i][j].num, false);
			}
		}
		
//		왼쪽 방향
		for (int j = 0; j < N; j++) {
			for (int i = 0; i < N; i++) {
				if(map[i][j] == null) continue;
				map[i][j].c --;
				while(true) {
					if(map[i][j].c < 0) break;
					if(map[i][map[i][j].c] != null) {
						if(map[i][map[i][j].c].move) break;
						if(map[i][map[i][j].c].num == map[i][j].num) {
							map[i][map[i][j].c].num *= 2;
							map[i][map[i][j].c].move = true;
							map[i][j].move= true;
						}else break;
					}
					map[i][j].c --;
				}
				map[i][j].c ++;
				if(map[i][j].move) map[i][j] = null;
				else {
					if(map[i][j].c != j) {						
						map[i][map[i][j].c] = new Number(i, map[i][j].c, map[i][j].num, false);
						map[i][j] = null;
					}
				}
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] == null) continue;
				map[i][j].move = false;
			}
		}
		
		dfs(cnt + 1, map);
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(newMap[i][j] == null) {
					map[i][j] = null;
					continue;
				}
				map[i][j] = new Number(i, j, newMap[i][j].num, false);
			}
		}
		
//		오른쪽 방향
		for (int i = 0; i < N; i++) {
			for (int j = N - 1; j >= 0; j--) {
				if(map[i][j] == null) continue;
				map[i][j].c ++;
				while(true) {
					if(map[i][j].c == N) break;
					if(map[i][map[i][j].c] != null) {
						if(map[i][map[i][j].c].move) break;
						if(map[i][map[i][j].c].num == map[i][j].num) {
							map[i][map[i][j].c].num *= 2;
							map[i][map[i][j].c].move = true;
							map[i][j].move= true;
						}else break;
					}
					map[i][j].c ++;
				}
				map[i][j].c --;
				if(map[i][j].move) map[i][j] = null;
				else {
					if(map[i][j].c != j) {						
						map[i][map[i][j].c] = new Number(i, map[i][j].c, map[i][j].num, false);
						map[i][j] = null;
					}
				}
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] == null) continue;
				map[i][j].move = false;
			}
		}
		
		dfs(cnt + 1, map);
	}
}