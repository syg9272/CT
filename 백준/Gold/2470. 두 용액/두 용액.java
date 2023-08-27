import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		long[] arr = new long[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int left = 0;
		int right = N - 1;
		long ans = Integer.MAX_VALUE;
		long a = 0;
		long b = 0;
		
		Arrays.sort(arr);
		
		while(left < right) {
			long mix = arr[left] + arr[right];
			if(ans > Math.abs(mix)) {
				ans = Math.abs(mix);
				a = arr[left];
				b = arr[right];
			}
			if(mix < 0) left++;
			else if (mix > 0) right --;
			else break;
		}
		
		System.out.println((a < b ? a : b) + " " + (a > b ? a : b));
	}
}
