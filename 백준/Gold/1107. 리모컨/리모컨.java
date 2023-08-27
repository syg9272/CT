import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M, len, ans = Integer.MAX_VALUE;
	static boolean[] arr;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		arr = new boolean[10];
		
		// 고장난 버튼 체크
		if(M != 0) {
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				arr[Integer.parseInt(st.nextToken())] = true;
			}
		}
		// N이 몇자리 수인지 체크
		int num = N;
		len = 0;
		while(num > 0) {
			num /= 10;
			len++;
		}
		if(N == 0) len = 1;
		
//		if(M == 0) {
//			System.out.println(Math.min(len, Math.abs(N - 100)));
//			System.exit(0);
//		}
		
		if(M == 10) {
			System.out.println(Math.abs(N - 100));
			System.exit(0);
		}
		
		perm(0, 0);
		
		
		int maxLen = 0;
		int idx2 = 0;
		for (int i = 0; i < arr.length; i++) {
			if(!arr[i]) {
				if(i != 0) {					
					while(idx2 < len + 1) {
						maxLen = maxLen * 10 + i;
						idx2++;
					}
					break;
				} else {
					for (int j = 1; j < arr.length; j++) {
						if(!arr[j]) {
							maxLen = (int) (Math.pow(10, len) * j);
						}
						break;
					}
					break;
				}
			}
		}
		maxLen = Math.abs(N - maxLen) + len + 1;
		
		if(len > 1) {
			int minLen = 0;
			int idx = 0;
			for (int i = arr.length - 1; i >= 0; i--) {
				if(!arr[i]) {
					while(idx < len - 1) {
						minLen = minLen * 10 + i;
						idx++;
					}
					minLen = Math.abs(N - minLen) + len - 1;
					break;
				}
			}
			System.out.println(Math.min(Math.min(Math.min(ans + len, Math.abs(N - 100)), minLen), maxLen));
		} else {
			System.out.println(Math.min(Math.min(ans + len, Math.abs(N - 100)), maxLen));
		}
	}
	private static void perm(int num, int idx) {
		if(idx == len) {
			ans = Math.min(ans, Math.abs(N - num));
			return;
		}
		for (int i = 0; i < 10; i++) {
			if(arr[i]) continue;
			perm(num * 10 + i, idx + 1);
		}
	}
}