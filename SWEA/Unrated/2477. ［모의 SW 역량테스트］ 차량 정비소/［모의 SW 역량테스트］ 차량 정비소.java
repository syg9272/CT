import java.io.*;
import java.util.*;

public class Solution {
	// 입력값 변수
	public static int answer, N, M, K, A, B;
	public static int nowTime;
	public static Desk[] desk;
	public static Fix[] fix;
	public static Person[] person;
	public static Queue<Integer> q;				// 정비 창구 대기 큐
	public static PriorityQueue<Integer> pq;	// 접수 창구 대기 큐
	// 접수 창구 객체
	public static class Desk {
		public int num;	// 창구 번호
		public int time;	// 처리 시간
		public List<Integer> list;	// 다녀간 고객
		public int start;	// 손님 들어온 시간
		public boolean state;	// 창구 상태
		public Desk(int num, int time) {	// 생성자
			this.num = num;
			this.time = time;
			list = new ArrayList<>();
			this.start = -1;
			state = true;
		}
	}
	// 정비 창구 객체
	public static class Fix {
		public int num;	// 창구 번호
		public int time;	// 처리 시간
		public List<Integer> list;	// 다녀간 고객
		public int start;	// 손님 들어온 시간
		public boolean state;	// 창구 상태
		public Fix(int num, int time) {	// 생성자
			this.num = num;
			this.time = time;
			list = new ArrayList<>();
			this.start = -1;
			state = true;
		}
	}
	// 고객 객체
	public static class Person{
		public int num;	// 고객 번호
		public int time;	// 도착 시간
		public int deskNum;	// 이용한 접수 창구 번호
		public int fixNum;	// 이용한 정비 창구 번호
		public Person(int num, int time) {	// 생성자
			this.num = num;
			this.time = time;
			this.deskNum = -1;
			this.fixNum = -1;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t ++) {
			answer = -1;
			// 입력값 저장
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());	// 접수 창구 개수
			M = Integer.parseInt(st.nextToken());	// 정비 창구 개수
			K = Integer.parseInt(st.nextToken());	// 고객 수
			A = Integer.parseInt(st.nextToken());	// 체크해야 할 접수 창구번호
			B = Integer.parseInt(st.nextToken());	// 체크해야 할 정비 창구번호
			q = new ArrayDeque<>();	// 정비 창구 대기 큐
			pq = new PriorityQueue<>(new Comparator<Integer>() {	// 접수 창구 대기 큐 (고객번호 우선수위 큐)
				@Override
				public int compare(Integer o1, Integer o2) {
					return o1 - o2;
				}
			});
			// 각 객체 배열 생성
			desk = new Desk[N + 1];
			fix = new Fix[M + 1];
			person = new Person[K + 1];
			// 창구 이용 시간 저장
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i <= N; i ++) {	// 접수창구
				desk[i] = new Desk(i, Integer.parseInt(st.nextToken()));
			}
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i <= M; i ++) {	// 정비창구
				fix[i] = new Fix(i, Integer.parseInt(st.nextToken()));
			}
			// 고객 도착 시간 저장
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i <= K; i ++) {
				person[i] = new Person(i, Integer.parseInt(st.nextToken()));
			}
			// 시간별로 고객 이동
			movePeople();
			// 모든 고객 이용 완료 후 결과값 체크
			check();
			
			sb.append("#" + t + " " + answer + "\n");
		}
		System.out.println(sb);
	}
	
	// 창구에 고객 배치하는 메서드
	public static void movePeople() {
		// 첫 손님 도착 시간부터 체크
		nowTime = person[1].time;
		while(true) {
			// 도착한 손님 체크
			for(int i = 1; i <= K; i ++) {
				// 현재 시간에 도착했지만 접수 전인 경우
				if(person[i].time <= nowTime && person[i].deskNum < 0 && !pq.contains(i)) {
					pq.add(i);	// 접수 대기 큐에 삽입
				}
			}
			// 접수 창구 진행
			for(int i = 1; i <= N; i ++) {
				// 접수 처리 된 고객 제거
				if(desk[i].start >= 0 && desk[i].start + desk[i].time <= nowTime) {
					// 고객 없는 상태로 초기화
					desk[i].start = -1;	
					desk[i].state = true;
					// 정비 대기 큐에 삽입
					q.offer(desk[i].list.get(desk[i].list.size() - 1));
				}
			}
			// 접수 창구에 고객 배치
			for(int i = 1; i <= N; i ++) {
				if(pq.isEmpty()) break;
				if(desk[i].state) {
					int num = pq.poll();
					person[num].deskNum = i;
					desk[i].list.add(num);	// 고객 리스트에 추가
					desk[i].start = nowTime;// 고객 들어온 시간 저장
					desk[i].state = false;	// 상태 변경
				}
			}
			// 정비 창구 진행
			for(int i = 1; i <= M; i ++) {
				// 정비 처리 된 고객 제거
				if(fix[i].start >= 0 && fix[i].start + fix[i].time <= nowTime) {
					// 고객 없는 상태로 초기화
					fix[i].start = -1;
					fix[i].state = true;
				}
			}
			// 정비 창구에 고객 배치
			for(int i = 1; i <= M; i ++) {
				if(q.isEmpty()) break;
				if(fix[i].state) {
					int num = q.poll();
					person[num].fixNum = i;
					fix[i].list.add(num);	// 고객 리스트에 추가
					fix[i].state = false;	// 고객 들어온 시간 저장
					fix[i].start = nowTime;	// 상태 변경
				}
			}
			// 1분 증가
			nowTime ++;
			
			// 반복문 종료 조건
			boolean flag = true;
			for(int i = 1; i <= K; i ++) {
				if(person[i].fixNum < 0) flag = false;
			}
			if(flag) break;
		}
		return;
	}
	
	// 결과값 체크
	public static void check() {
		int sum = 0;
		// 같은 창구 이용한 고객 체크
		for(int i = 1; i <= K; i ++) {
			if(person[i].deskNum == A && person[i].fixNum == B) {
				sum += i;
			}
		}
		// 없을 경우 갱신 x
		if(sum != 0) answer = sum;
		return;
	}
}