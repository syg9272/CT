import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		int vertex;
		Node next;
		public Node(int vertex, Node next) {
			this.vertex = vertex;
			this.next = next;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		Node[] adjList = new Node[N + 1];
		int[] inDegree = new int[N + 1];
		int[] singer;
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int K = Integer.parseInt(st.nextToken());
			singer = new int[K];
			for (int j = 0; j < K; j++) {
				singer[j] = Integer.parseInt(st.nextToken());
			}
			for (int j = 0; j < K - 1; j++) {
				int from = singer[j];
				for (int k = j + 1; k < K; k++) {
					int to = singer[k];
					adjList[from] = new Node(to, adjList[from]);
					inDegree[to]++;
				}
			}
		}
		
		ArrayList<Integer> list = new ArrayList<>();
		Queue<Integer> q = new ArrayDeque<>();
		
		for (int i = 1; i <= N; i++) {
			if(inDegree[i] == 0) q.offer(i);
		}
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			list.add(cur);
			for (Node temp = adjList[cur]; temp != null; temp = temp.next) {
				if(--inDegree[temp.vertex] == 0) q.offer(temp.vertex);
			}
		}
		
		if(list.size() != N) System.out.println(0);
		else {
			for (int i = 0; i < list.size(); i++) {
				sb.append(list.get(i) + "\n");
			}
			System.out.println(sb);
		}
	}
}