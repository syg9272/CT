import java.io.*;
import java.util.*;

public class Solution {
	public static int answer, N, group;
	public static int[][] map;
	public static Stair[] stairs;
	public static Person[] person;
	public static boolean[] num, check;
	// 계단 객체
	public static class Stair {
		public int x;	// x좌표
		public int y;	// y좌표
		public Queue<int[]> list;	// 해당 계단에 위치한 사람 번호와 남은 시간
		public Queue<Integer> q;	// 계단 대기열
		public int time;	// 길이
		public Stair(int x, int y, int time) {	// 생성자
			this.x = x;
			this.y = y;
			this.list = new ArrayDeque<>();
			this.q = new ArrayDeque<>();
			this.time = time;
		}
		public boolean inPeople(int num) {	// 계단 초과 여부 반환
			if(this.list.size() < 3) return true;
			else return false;
		}
		public void reset() {
			this.list = new ArrayDeque<>();
			this.q = new ArrayDeque<>();
		}
	}
	public static class Person {
		public int x;	// x좌표
		public int y;	// y좌표
		public int time;	// 계단까지의 이동 시간
		public int stairNum;	// 계단 넘버
		public String state;	// 해당 사람의 상태 (계단 도착 전 : "N", 계단 대기 : "W")
		public Person(int x, int y) {	// 생성자
			this.x = x;
			this.y = y;
			this.time = 0;
			this.stairNum = -1;
			this.state = "N";
		}
		public void setState(String state) {	// 상태 변경
			this.state = state;
		}
		public void reset() {	// 초기화
			this.time = 0;
			this.stairNum = -1;
			this.state = "N";
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t ++) {
			answer = Integer.MAX_VALUE;	// 결과값 초기화
			N = Integer.parseInt(br.readLine());	// 방 크기 설정
			map = new int[N][N];	// 방 생성
			stairs = new Stair[2];	// 계단 객체 저장 배열
			int countPeople = 0;	// 전체 인원 수 체크
			int idx = 0;	// 인덱스
			// 방 저장
			for(int i = 0; i < N; i ++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j ++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] == 1) {	// 인원 수 체크
						countPeople ++;
					}
					if(map[i][j] > 1) {	// 계단 객체 생성 후 저장
						stairs[idx ++] = new Stair(i, j, map[i][j]);
					}
				}
			}
			// 사람 저장
			person = new Person[countPeople];
			idx = 0;
			for(int i = 0; i < N; i ++) {
				for(int j = 0; j < N; j ++) {
					if(map[i][j] == 1) {
						person[idx ++] = new Person(i, j);
					}
				}
			}
			
			// 사람 별로 어떤 계단을 이용할 지 부분집합으로 결정
			for(int i = 0; i <= countPeople; i ++) {
				num = new boolean[countPeople];	// 체크 배열 초기화
				group = i;	// 최대 인원
				subset(0, 0);	// 부분집합 구하기
			}
			
			sb.append("#" + t + " " + answer + "\n");
		}
		System.out.println(sb);
	}
	
	// 부분 집합으로 어떤 계단 이용할 지 구분하기
	public static void subset(int r, int start) {
		// 재귀 종료 조건
		if(r == group) {
			// 시간 별 이동 체크하기
			movecheck();
			reset();
			return;
		}
		// 인덱스 벗어날 경우 돌아가기
		if(start == num.length) return;
		num[start] = true;
		subset(r + 1, start + 1);
		num[start] = false;
		subset(r, start + 1);
	}
	
	// 시간 별로 이동 체크하기
	public static void movecheck() {
		// 정해진 계단과의 거리 계산하기
		for(int i = 0; i < person.length; i ++) {
			if(num[i]) {	// 부분집합 결과에 따라 해당 계단과의 거리 계단
				person[i].stairNum = 1;
				person[i].time = Math.abs(stairs[1].x - person[i].x) + Math.abs(stairs[1].y - person[i].y);
			} else {
				person[i].stairNum = 0;
				person[i].time = Math.abs(stairs[0].x - person[i].x) + Math.abs(stairs[0].y - person[i].y);
			}
		}
		
		// 시간 별로 사람 이동시키기
		int minute = 0;
		check = new boolean[person.length];
		outer : while(true) {
			// 최소 시간을 이미 넘긴 경우 리턴
			if(minute >= answer) return;
			minute ++;	// 1분 지남
			// 모든 사람 이동
			for(int i = 0; i < person.length; i ++) {
				if(person[i].time == 0) {	// 계단까지의 거리가 0인 경우
					if(person[i].state == "N") {	// 계단 도착 전인 상태인 경우
						stairs[person[i].stairNum].q.add(i);	// 해당 계단 대기열에 추가
						person[i].setState("W");
					}
				} else {	// 계단까지의 거리가 1이상인 경우					
					person[i].time --;	// 한 칸 이동
				}
			}
			// 계단 위에 있는 사람 이동
			for(int i = 0; i < 2; i ++) {
				int size = stairs[i].list.size();	// 현재 해당 계단 위에 위치한 사람 수
				for(int j = 0; j < size; j ++) {	// 계단 위에 있는 사람 이동하기
					int[] temp = stairs[i].list.poll();
					if(temp[1] > 1) {	// 이동 후에도 도착하지 않는 사람만 다시 큐에 삽입					
						stairs[i].list.offer(new int[] {temp[0], temp[1] - 1});
					} else {	// 계단을 모두 내려온 사람
						check[temp[0]] = true;	// 이동 완료 체크
					}
				}
			}
			// 계단 대기열에 있는 사람 이동
			for(int i = 0; i < 2; i ++) {
				int cnt = 3 - stairs[i].list.size();	// 추가 가능한 인원 수
				while(cnt > 0) {
					// 대기 인원 이동 시작
					if(stairs[i].q.size() > 0) {
						stairs[i].list.add(new int[] {stairs[i].q.poll(), stairs[i].time});
					} else break;
					cnt --;
				}
			}
			// 모든 사람이 계단을 내려왔는지 체크
			boolean flag = true;
			for(int i = 0; i < person.length; i ++) {
				if(!check[i]) {
					flag = false;
					continue;
				}
			}
			if(flag) break outer;	// 모든 사람이 내려온 경우 반복문 종료
		}
		// 최소 시간 저장 후 리턴
		answer = Math.min(answer, minute);
		return;
	}
	
	// 모든 객체 상태 초기화
	public static void reset() {
		for(int i = 0; i < person.length; i ++) {
			person[i].reset();
		}
		for(int i = 0; i < 2; i ++) {
			stairs[i].reset();
		}
	}
}