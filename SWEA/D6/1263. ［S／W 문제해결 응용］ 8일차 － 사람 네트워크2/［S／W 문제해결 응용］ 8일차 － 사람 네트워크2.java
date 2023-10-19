import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("data/1263.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			
			int ans = Integer.MAX_VALUE;
			
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			
			int[][] map = new int[n][n];
			
			for (int i = 0; i < n; i++) {	// 그래프 입력받기
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(i != j && map[i][j] == 0) map[i][j] = 10000000;	// 비워져있는 곳 큰 값으로 채우기
				}
			}
			
			for (int k = 0; k < n; k++) {	// 거쳐가는 노드
				for (int i = 0; i < n; i++) {	// 출발 노드
					if(i == k) continue;
					for (int j = 0; j < n; j++) {	// 도착 노드
						if(j == i || j == k) continue;
						if(map[i][k] + map[k][j] < map[i][j]) {	// 어떤 노드를 거쳤을 때 더 빠른 경우 최소 비용 갱신
							map[i][j] = map[i][k] + map[k][j];
						}
					}
				}
			}
			for (int i = 0; i < n; i++) {	// 각 노드별로 비용의 합을 계산 -> 최단 경로 출력
				int sum = 0;
				for (int j = 0; j < n; j++) {
					sum += map[i][j];
				}
				ans = Math.min(ans, sum);
			}
			sb.append("#" + t + " " + ans + "\n");
		}
		System.out.println(sb);
	}
}