import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static final int INF = 9999999;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			int n = Integer.parseInt(br.readLine());
			
			int[][] map = new int[n + 2][n + 2];	// 거리 저장
			int[][] point = new int[n + 2][2];	// 좌표값 저장
			
			// 집 위치 저장
			st = new StringTokenizer(br.readLine());
			int start_x = Integer.parseInt(st.nextToken());
			int start_y = Integer.parseInt(st.nextToken());
			
			point[0][0] = start_x;
			point[0][1] = start_y;
			
			// 편의점 위치 저장
			for (int i = 1; i <= n; i++) {
				st = new StringTokenizer(br.readLine());
				point[i][0] = Integer.parseInt(st.nextToken());
				point[i][1] = Integer.parseInt(st.nextToken());
			}
			
			// 페스티벌 위치 저장
			st = new StringTokenizer(br.readLine());
			int end_x = Integer.parseInt(st.nextToken());
			int end_y = Integer.parseInt(st.nextToken());
			
			point[n + 1][0] = end_x;
			point[n + 1][1] = end_y;
			
			// 각 좌표간의 거리 저장 (맨해튼 거리)
			for (int i = 0; i < n + 2; i++) {
				for (int j = 0; j < n + 2; j++) {
					map[i][j] = Math.abs(point[i][0] - point[j][0]) + Math.abs(point[i][1] - point[j][1]);
					if(map[i][j] > 1000) map[i][j] = INF;	// 이동 불가능한 좌표에는 INF 넣어놓기
				}
			}
			
			for (int k = 1; k <= n; k++) {	// 편의점 (경유지)
				for (int i = 0; i <= n; i++) {	// 출발지
					for (int j = 1; j < n + 2; j++) {	// 도착지
						if(map[i][k] == INF || map[k][j] == INF) continue;	// 이동 불가능하면 무시
						if(map[i][j] > map[i][k] + map[k][j]) {
							map[i][j] = map[i][k] + map[k][j];
						}
					}
				}
			}
			sb.append((map[0][n + 1] == INF ? "sad" : "happy") + "\n");
		}
		System.out.println(sb);
	}
}
