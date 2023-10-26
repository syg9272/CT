import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		int[][] dp = new int[2][3];
		
		st = new StringTokenizer(br.readLine());
		dp[0][0] = Integer.parseInt(st.nextToken());
		dp[0][1] = Integer.parseInt(st.nextToken());
		dp[0][2] = Integer.parseInt(st.nextToken());
		
		dp[1][0] = dp[0][0];
		dp[1][1] = dp[0][1];
		dp[1][2] = dp[0][2];
		
		int a, b, c, a1, b1, c1;
		
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			
			a1 = Math.max(dp[0][0] + a, dp[0][1] + a);
			b1 = Math.max(Math.max(dp[0][0] + b, dp[0][1] + b), dp[0][2] + b);
			c1 = Math.max(dp[0][1] + c, dp[0][2] + c);
			
			dp[0][0] = a1;
			dp[0][1] = b1;
			dp[0][2] = c1;

			a1 = Math.min(dp[1][0] + a, dp[1][1] + a);
			b1 = Math.min(Math.min(dp[1][0] + b, dp[1][1] + b), dp[1][2] + b);
			c1 = Math.min(dp[1][1] + c, dp[1][2] + c);
			
			dp[1][0] = a1;
			dp[1][1] = b1;
			dp[1][2] = c1;
			
		}
		
		System.out.println(Math.max(Math.max(dp[0][0], dp[0][1]), dp[0][2]) + " " + Math.min(Math.min(dp[1][0], dp[1][1]), dp[1][2]));
		
	}
}