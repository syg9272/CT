import java.io.*;
import java.util.*;

public class Solution {
	public static int answer, N, W, H;		// 출력값, 입력값
	public static int[][] map, copyMap;		// 입력되는 벽돌 맵, 실제 사용할 카피 맵
	public static int[] num;				// 중복 순열 담을 배열
	public static int[] dx = {0, -1, 0, 1};	// 4방 탐색 x 좌표
	public static int[] dy = {-1, 0, 1, 0};	// 4방 탐색 y 좌표
	public static Queue<int[]> q;			// bfs 사용할 큐
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t ++) {
			// 입력값 저장
			answer = Integer.MAX_VALUE;	// 결과값 초기화
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());	// 구슬 떨어뜨리는 횟수
			W = Integer.parseInt(st.nextToken());	// 열
			H = Integer.parseInt(st.nextToken());	// 행
			map = new int[H][W];					// 벽돌 맵 생성
			for(int i = 0; i < H; i ++) {			// 맵에 벽돌 저장
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < W; j ++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 중복순열로 구슬 떨어뜨릴 컬럼 정하기
			num = new int[N];
			perm(0);
			
			
			sb.append("#" + t + " " + answer + "\n");
		}
		System.out.println(sb);
	}
	
	// 구슬 떨어뜨릴 컬럼 정하는 중복 순열 함수
	public static void perm(int r) {
		// 재귀 종료 조건
		if(r == num.length) {
			copy();
			playGame();	// 구슬 떨어뜨리기 시뮬레이션
			return;
		}
		// 중복 순열
		for(int i = 0; i < W; i ++) {
			num[r] = i;
			perm(r + 1);
		}
	}
	
	// 정해진 컬럼에 구슬 떨어뜨리기
	public static void playGame() {
		for(int j = 0; j < num.length; j ++) {
			// 해당 컬럼에 제일 위 벽돌 명중 -> 연속적으로 벽돌 맞추기 (bfs)
			for(int i = 0; i < H; i ++) {
				if(copyMap[i][num[j]] != 0) {
					q = new ArrayDeque<>();
					q.offer(new int[] {i, num[j], copyMap[i][num[j]] - 1});
					bfs();
					break;
				}
			}
		}
		// 구슬 모두 친 뒤에 남은 벽돌 개수 저장
		int sum = 0;
		for(int i = 0; i < H; i ++) {
			for(int j = 0; j < W; j ++) {
				if(copyMap[i][j] > 0) sum ++;
			}
		}
		
		// 남은 벽돌 최소값 저장
		answer = Math.min(answer, sum);
		return;
	}
	
	// 벽돌 맞추기
	public static void bfs() {
		while(!q.isEmpty()) {
			int time = q.size();
			for(int t = 0; t < time; t ++) {	// q 사이즈만큼 한 싸이클 돌기
				int[] temp = q.poll();
				for(int d = 0; d < 4; d ++) {	// 4방 탐색
					for(int i = 0; i <= temp[2]; i ++) {	// 벽돌에 적힌 수 -1 범위만큼 벽돌치기						
						int nx = temp[0] + dx[d] * i;	// x 좌표 이동
						int ny = temp[1] + dy[d] * i;	// y 좌표 이동
						if(nx < 0 || nx >= H || ny < 0 || ny >= W) break;	// 범위를 벗어날 경우 다음 방향 탐색
						if(copyMap[nx][ny] > 1) {	// 1보다 범위가 큰 경우 큐에 삽입
							q.offer(new int[] {nx, ny, copyMap[nx][ny] - 1});
						}
						copyMap[nx][ny] = 0;	// 명중한 위치 벽돌 없애기
					}
				}
			}
		}
		
		// 벽돌 모두 제거한 뒤, 벽돌 떨어뜨리기
		downBlock();
		return;
	}
	
	// 벽돌 떨어뜨리기
	public static void downBlock() {
		int[] temp;	// 존재하는 벽돌만 담을 배열
		// temp에 남아있는 벽돌만 담은 뒤 copyMap에 다시 저장
		for(int j = 0; j < W; j ++) {
			int idx = 0;
			temp = new int[H];
			for(int i = H - 1; i >= 0; i --) {
				if(copyMap[i][j] > 0) temp[idx ++] = copyMap[i][j];
			}
			idx = H - 1;
			for(int i = 0; i < H; i ++) {
				copyMap[i][j] = temp[idx --];
			}
		}
		return;
	}
	
	// 벽돌 맵 초기 상태로 복사하기
	public static void copy() {
		copyMap = new int[H][W];
		for(int i = 0; i < H; i ++) {
			copyMap[i] = Arrays.copyOf(map[i], map[i].length);
		}
	}
}