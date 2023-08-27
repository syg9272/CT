import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N + k - 1];
		HashSet<Integer> hSet = new HashSet<>();
		
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		for (int i = 0; i < k - 1; i++) {
			arr[N + i] = arr[i];
		}
		
		int ans = 0;
		for (int i = 0; i < N; i++) {
			for (int j = i; j < i + k; j++) {
				hSet.add(arr[j]);
			}
			hSet.add(c);
			ans = Math.max(ans, hSet.size());
			hSet.clear();
		}
		System.out.println(ans);
	}
}