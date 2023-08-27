import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		if(K < N) {
			System.out.println(N - K);
			return;
		}
		
		int[] dp = new int[K + 1];
		
		for (int i = N - 1; i >= 0; i--) {
			dp[i] = dp[i + 1] + 1;
		}
		
		for (int i = N + 1; i <= K; i++) {
			dp[i] = dp[i - 1] + 1;
			if(i % 2 == 0) {
				dp[i] = Math.min(dp[i], dp[i / 2]);
			}
			if(i % 2 != 0) {
				dp[i] = Math.min(Math.min(dp[i], dp[(i - 1) / 2] + 1), dp[(i + 1) / 2] + 1);
			}
		}
		System.out.println(dp[K]);
	}
}
