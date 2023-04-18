import java.io.BufferedReader;
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
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		Node[] adjList = new Node[N + 1];
		int[] inDegree = new int[N + 1];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			adjList[from] = new Node(to, adjList[from]);
			inDegree[to]++;
		}
		
		Queue<Integer> q = new ArrayDeque<>();
		ArrayList<Integer> list = new ArrayList<>();
		
		for (int i = 1; i <= N; i++) {
			if(inDegree[i] == 0) {
				q.offer(i);
			}
		}
		
		while(!q.isEmpty()) {
			int n = q.poll();
			list.add(n);
			for (Node temp = adjList[n]; temp != null; temp = temp.next) {
				if(--inDegree[temp.vertex] == 0) q.offer(temp.vertex);
			}
		}
		
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i) + " ");
		}
		
		System.out.println(sb);
	}
}