import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		long[] arr = new long[N + 1];
		
		for (int i = 1; i <= N; i++) {
			arr[i] = arr[i - 1] + Long.parseLong(br.readLine());
		}
		
		for (int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine());
			if(Integer.parseInt(st.nextToken()) == 1) {
				int start = Integer.parseInt(st.nextToken());
				long change = Long.parseLong(st.nextToken());
				long n = change - arr[start] + arr[start - 1];
				for (int j = start; j < arr.length; j++) {
					arr[j] += n;
				}
			} else {
				sb.append((arr[Integer.parseInt(st.nextToken()) - 1] * -1 + arr[Integer.parseInt(st.nextToken())]) + "\n");
			}
		}
		System.out.println(sb);
	}
}
