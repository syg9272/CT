import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int V, E;
	
	static int[] p;
	static class Edge implements Comparable<Edge>{
		int from, to, weight;
		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	static void make() {
		p = new int[V + 1];
		for (int i = 1; i <= V; i++) {
			p[i] = i;
		}
	}
	static int find(int a) {
		if(p[a] == a) return a;
		return p[a] = find(p[a]);
	}
	static boolean union(int a, int b) {
		a = find(a);
		b = find(b);
		if(a == b) return false;
		
		p[b] = a;
		return true;
	}
	
	static class Node {
		int vertex, weight;
		Node next;
		public Node(int vertex, int weight, Node next) {
			this.vertex = vertex;
			this.weight = weight;
			this.next = next;
		}
	}
	
	static class Vertex implements Comparable<Vertex>{
		int no, weight;
		public Vertex(int no, int weight) {
			this.no = no;
			this.weight = weight;
		}
		@Override
		public int compareTo(Vertex o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		Edge[] edgeList = new Edge[E];
		Node[] adjList = new Node[V + 1];
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			edgeList[i] = new Edge(from, to, weight);
			
			// 무향 처리
			adjList[from] = new Node(to, weight, adjList[from]);
			adjList[to] = new Node(from, weight, adjList[to]);
		}
//		kruskal(edgeList);
//		prim(adjList);
		primPQ(adjList);
	}
	
	private static void kruskal(Edge[] edgeList) {
		Arrays.sort(edgeList);
		make();
		
		int cnt = 0;
		int result = 0;
		
		for (Edge edge : edgeList) {
			if(union(edge.from, edge.to)) {
				result += edge.weight;
				if(++cnt == V - 1) break;
			}
		}
		System.out.println(result);
	}
	
	private static void prim(Node[] adjList) {
		int[] minEdge = new int[V + 1];	// 각 정점이 신장트리에 포함된 정점과의 간선 비용 중 최소비용
		boolean[] visited = new boolean[V + 1];	// 신장트리에 포함 여부
		
		Arrays.fill(minEdge, Integer.MAX_VALUE); 	// 최소값 관리하기 위해 큰 값 세팅
		
		//1. 임의의 시작점 처리
		minEdge[1] = 0;
		int result = 0;	// 누적 가중치
		
		for (int c = 1; c <= V; c++) {
			//1. 신장트리의 구성에 포함되지 않은 정점 중 최소비용 정점 선택
			int minW = Integer.MAX_VALUE;
			int minV = -1;
			for (int i = 1; i <= V; i++) {
				if(!visited[i] && minW > minEdge[i]) {
					minW = minEdge[i];
					minV = i;
				}
			}
			//2. 선택된 곳 신장트리에 추가
			visited[minV] = true;
			//3. 누적 가중치 갱신
			result += minW;
			
			//4. 새로 추가된 정점의 모든 인접정점 들여다보며 처리
			for (Node temp = adjList[minV]; temp != null; temp = temp.next) {
				if(!visited[temp.vertex] && minEdge[temp.vertex] > temp.weight) {
					minEdge[temp.vertex] = temp.weight;
				}
			}
		}
		System.out.println(result);
	}
	private static void primPQ(Node[] adjList) {
		int[] minEdge = new int[V + 1];	// 각 정점이 신장트리에 포함된 정점과의 간선 비용 중 최소비용
		boolean[] visited = new boolean[V + 1];	// 신장트리에 포함 여부
		
		Arrays.fill(minEdge, Integer.MAX_VALUE); 	// 최소값 관리하기 위해 큰 값 세팅
		
		//1. 임의의 시작점 처리
		minEdge[1] = 0;
		int result = 0;	// 누적 가중치
		
		PriorityQueue<Vertex> pQueue = new PriorityQueue<>();
		pQueue.offer(new Vertex(1, minEdge[1]));
		
		int cnt = 0;	// 신장트리에 포함된 정점
		while (!pQueue.isEmpty()) {
			//1. 신장트리의 구성에 포함되지 않은 정점 중 최소비용 정점 선택
			Vertex minVertex = pQueue.poll();
			
			if(visited[minVertex.no]) continue;
			
			//2. 선택된 곳 신장트리에 추가
			visited[minVertex.no] = true;
			//3. 누적 가중치 갱신
			result += minVertex.weight;
			
			if(++cnt == V) break;	// 모든 정점을 포함했다면 빠져나오기
			
			//4. 새로 추가된 정점의 모든 인접정점 들여다보며 처리
			for (Node temp = adjList[minVertex.no]; temp != null; temp = temp.next) {
				if(!visited[temp.vertex] && minEdge[temp.vertex] > temp.weight) {
					minEdge[temp.vertex] = temp.weight;
					pQueue.offer(new Vertex(temp.vertex, minEdge[temp.vertex]));
				}
			}
		}
		System.out.println(result);
	}
}