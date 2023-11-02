import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] arr, cal;
	static int N, minAns = Integer.MAX_VALUE, maxAns = Integer.MIN_VALUE;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		cal = new int[4];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < cal.length; i++) {
			cal[i] = Integer.parseInt(st.nextToken());
		}
		
		number(arr[0], 1, 1);
		System.out.println(maxAns);
		System.out.println(minAns);
	}
	private static void number(int result, int idx, int n) {
		if(n == N) {
			minAns = Math.min(minAns, result);
			maxAns = Math.max(maxAns, result);
		}
		
		if(cal[0] > 0) {
			cal[0]--;
			number(result + arr[idx], idx + 1, n + 1);
			cal[0]++;
		}
		if(cal[1] > 0) {
			cal[1]--;
			number(result - arr[idx], idx + 1, n + 1);
			cal[1]++;
		}
		if(cal[2] > 0) {
			cal[2]--;
			number(result * arr[idx], idx + 1, n + 1);
			cal[2]++;
		}
		if(cal[3] > 0) {
			cal[3]--;
			number(result / arr[idx], idx + 1, n + 1);
			cal[3]++;
		}
	}
}