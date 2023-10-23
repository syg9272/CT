import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static int D, W, K, ans;
	static int[][] map, copyMap;
	static int[] drug;
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("data/2112.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			ans = K;
			
			map = new int[D][W];
			drug = new int[D];
			
			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			if(K == 1) sb.append("#" + t + " " + 0 + "\n");
			else {				
				drugInjection(0, 0);
				sb.append("#" + t + " " + ans + "\n");
			}
		}
		System.out.println(sb);
	}
	private static void drugInjection(int r, int cnt) {
		if(r == D) {
			check();
			return;
		}
		
		for (int i = -1; i <= 1; i++) {
			if(i >= 0 && cnt < ans - 1) {				
				drug[r] = i;
				drugInjection(r + 1, cnt + 1);
				
			}else if(i == -1) {
				drug[r] = i;
				drugInjection(r + 1, cnt);
			}
		}
	}
	private static void check() {
		int count = 0;
		// 성능 검사 할 때마다 맵 카피
		copyMap = new int[D][W];
		for (int i = 0; i < D; i++) {
			copyMap[i] = Arrays.copyOf(map[i], W);
		}
		// 약품을 투입하는 조건에 따라 맵 변경
		for (int i = 0; i < D; i++) {
			if(drug[i] < 0) continue;
			else count++;
			for (int j = 0; j < W; j++) {
				copyMap[i][j] = drug[i];
			}
		}
		// 성능 검사를 통과할 수 있는지 파악
		int sum = 0;
		for (int j = 0; j < W; j++) {
			int type = copyMap[0][j];
			int cnt = 1;
			boolean flag = false;
			for (int i = 1; i < D; i++) {
				if(copyMap[i][j] == type) {
					cnt++;
				} else {
					type = copyMap[i][j];
					cnt = 1;
				}
				if(cnt == K) {
					flag = true;
					break;
				}
			}
			if(flag) {
				sum++;
			}else return;
		}
		if(sum == W) {
			ans = Math.min(ans, count);
		}
	}
}