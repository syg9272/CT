import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.Comparator;

public class Solution {
	static int N, M;
	static int[] p;
	static Edge[] edgeList;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			edgeList = new Edge[M];
			make();
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				edgeList[i] = new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			
			Arrays.sort(edgeList, new Comparator<Edge>() {

				@Override
				public int compare(Edge o1, Edge o2) {
					if(o1.weight >= 0 && o2.weight >= 0) return o1.weight - o2.weight;
					else if(o1.weight < 0) return -10;
					else if(o2.weight < 0) return 10;
					else return o1.weight - o2.weight;
				}
			});
			
			long ans = 0;
			int cnt = 0;
			for (Edge edge: edgeList) {
				if(union(edge.from, edge.to)) {
					ans += edge.weight;
					if(++cnt == N - 1) break;
				}
			}
			
			sb.append("#"+t+" "+ans).append("\n");			
		}
		System.out.print(sb);
	}
	static class Edge {
		int from;
		int to;
		int weight;
		
		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		
	}
	private static void make() {
		p = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			p[i] = i;
		}
	}
	private static int find(int a) {
		if(a == p[a]) return a;
		return p[a] = find(p[a]);
	}
	private static boolean union(int a, int b) {
		a = find(a);
		b = find(b);
		if(a == b) return false;
		
		p[b] = a;
		return true;
	}
	
}
