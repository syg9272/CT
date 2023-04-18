import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static Node[] adjList;
	static int[] population, idx;
	static int N, sum, ans = Integer.MAX_VALUE;
	public static class Node {	// 노드 객체
		int to;
		Node next;
		public Node(int to, Node next) {
			this.to = to;
			this.next = next;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		population = new int[N + 1];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {	// 인구 수 담기
			population[i] = Integer.parseInt(st.nextToken());
		}
		
		adjList = new Node[N + 1];
		
		for (int i = 1; i <= N; i++) {	// 인접리스트 구현
			st = new StringTokenizer(br.readLine());
			int size = Integer.parseInt(st.nextToken());
			if(size == 0) {
				adjList[i] = new Node(i, adjList[i]);
				adjList[i] = new Node(i, adjList[i]);
			}else {				
				for (int j = 0; j < size; j++) {				
					int from = Integer.parseInt(st.nextToken());
					adjList[from] = new Node(i, adjList[from]);
					adjList[i] = new Node(from, adjList[i]);
				}
			}
		}
		
		for (int i = 1; i < 6; i++) {	// 부분집합 (최소 1구역 ~ 최대 N - 1구역)
			idx = new int[i];
			divArea(0, 1);
		}
		System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
	}
	private static void divArea(int r, int start) {	// 구역 부분집합으로 나누기
		if(r >= idx.length) {
			// 구역 나눠보고 가능할 경우 인구 수 차이 계산 후 최소값 갱신
			if(check()) ans = Math.min(ans, sum);
			return;
		}
		if(start > N) return;
		idx[r] = start;
		divArea(r + 1, start + 1);
		divArea(r, start + 1);
	}
	private static boolean check() {
		int cnt = 0;
		int cnt2 = 0;
		boolean[] include = new boolean[N + 1];
		boolean[] visited = new boolean[N + 1];	// 포함된 구역
		boolean[] visited2 = new boolean[N + 1];	// 포함되지 않은 구역
		
		for (int i = 0; i < idx.length; i++) {	// 부분집합에 포함된 구역과 아닌 구역 나누기
			include[idx[i]] = true;
		}
		
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(idx[0]);
		visited[idx[0]] = true;
		
		while(!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				for (Node temp = adjList[q.poll()]; temp != null; temp = temp.next) {
					if(include[temp.to] && !visited[temp.to]) {
						visited[temp.to]= true;
						q.offer(temp.to);
					}
				}
			}
		}
		
		for (int i = 1; i <= N; i++) {
			if(!include[i]) {
				q.offer(i);
                visited2[i] = true;
				break;
			}
		}
		
		while(!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				for (Node temp = adjList[q.poll()]; temp != null; temp = temp.next) {
					if(!include[temp.to] && !visited2[temp.to]) {
						visited2[temp.to]= true;
						q.offer(temp.to);
					}
				}
			}
		}
		
		for (int i = 1; i <= N; i++) {
			if(include[i] != visited[i]) return false;
			if(include[i] == visited2[i]) return false;
			if(include[i]) cnt += population[i];
			if(!include[i]) cnt2 += population[i];
		}
		sum = Math.abs(cnt - cnt2);
		return true;
	}
}