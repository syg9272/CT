import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		int vertex, weight; 
		Node next;
		public Node(int vertex, int weight, Node next) {
			this.vertex = vertex;
			this.weight = weight;
			this.next = next;
		}
	}
	
	static class Vertex {
		int no, weight;

		public Vertex(int no, int weight) {
			this.no = no;
			this.weight = weight;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		int start = Integer.parseInt(br.readLine());
		int end = V;
		
		Node[] adjList = new Node[V + 1];
		int[] D = new int[V + 1];
		Arrays.fill(D, Integer.MAX_VALUE);
		boolean[] visited = new boolean[V + 1];
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			// 유향 그래프
			adjList[from] = new Node(to, weight, adjList[from]);
		}
		
		// 시작정점
		D[start] = 0;
		int minW, minV;
		
		for (int i = 1; i <= V; i++) {
			minW = Integer.MAX_VALUE;
			minV = -1;
			for (int j = 1; j <= V; j++) {
				if(!visited[j] && minW > D[j]) {
					minW = D[j];
					minV = j;
				}
			}
			if(minV == -1) continue;
			visited[minV] = true;
			
			for (Node temp = adjList[minV]; temp != null; temp = temp.next) {
				if(!visited[temp.vertex] && D[temp.vertex] > D[minV] + temp.weight) {
					D[temp.vertex] = D[minV] + temp.weight;
				}
			}
		}
		for (int i = 1; i <= V; i++) {
			sb.append((D[i] == Integer.MAX_VALUE? "INF" : D[i]) + "\n");
		}
		System.out.println(sb);
	}
}