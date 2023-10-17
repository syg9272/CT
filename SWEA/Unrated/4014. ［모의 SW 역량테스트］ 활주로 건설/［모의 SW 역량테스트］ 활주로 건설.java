import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("data/4014.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {	
			int ans = 0;
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			
			int[][] map = new int[n][n];
			
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			// 모든 행에 대한 활주로 건설 가능성 체크
			outline : for (int i = 0; i < n; i++) {
				int h = map[i][0];	// 처음 높이 저장
				int cnt = 1;	// 같은 높이 개수 저장
				int cnt2 = 0;	// 1칸 낮은 높이 개수 저장
				for (int j = 1; j < n; j++) {
					if(map[i][j] == h) {	// h와 높이가 같은 경우
						if(cnt2 > 0) continue outline;	// 이전에 1칸 낮은 높이가 x보다 적게 있었을 경우 활주로 건설 불가능
						cnt++;	// 같은 높이 개수 ++
					} else if(map[i][j] == h + 1) {	// h보다 1칸 높을 경우
						if(cnt < x || cnt2 > 0) continue outline;
						if(cnt >= x) {
							cnt = 1;
							h = map[i][j];
						}
					} else if(map[i][j] == h - 1) {
						cnt = 0;
						cnt2++;
						if(cnt2 == x) {
							cnt2 = 0;
							h = map[i][j];
						}
					} else continue outline;					
				}
				if(cnt2 == 0 )ans++;
			}
			// 모든 열에 대한 활주로 건설 가능성 체크
			outline : for (int j = 0; j < n; j++) {
				int h = map[0][j];	// 처음 높이 저장
				int cnt = 1;	// 같은 높이 개수 저장
				int cnt2 = 0;	// 1칸 낮은 높이 개수 저장
				for (int i = 1; i < n; i++) {
					if(map[i][j] == h) {	// h와 높이가 같은 경우
						if(cnt2 > 0) continue outline;	// 이전에 1칸 낮은 높이가 x보다 적게 있었을 경우 활주로 건설 불가능
						cnt++;	// 같은 높이 개수 ++
					} else if(map[i][j] == h + 1) {	// h보다 1칸 높을 경우
						if(cnt < x || cnt2 > 0) continue outline;
						if(cnt >= x) {
							cnt = 1;
							h = map[i][j];
						}
					} else if(map[i][j] == h - 1) {
						cnt = 0;
						cnt2++;
						if(cnt2 == x) {
							cnt2 = 0;
							h = map[i][j];
						}
					} else continue outline;					
				}
				if(cnt2 == 0) ans++;
			}
			sb.append("#" + t + " " + ans + "\n");
		}
		System.out.println(sb);
	}
}