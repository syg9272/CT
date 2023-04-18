import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int ans = 0;
		int[] num = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(num);
		int left, right;
		for (int i = 0; i < N; i++) {
			int target = num[i];
			left = 0;
			right = N-1;
			
			while(true) {
				if(left >= N-1 || left == right) break;
				if(left == i) {
					left++;
					continue;
				}else if(right == i) {
					right--;
					continue;
				}
				int temp = num[left] + num[right];
				if(temp == target) {
					ans++;
					break;
				}
				if(temp > target) {
					right--;
					continue;
				}else {
					left++;
					right = N-1;
				}
				
			}
		}
		System.out.println(ans);
	}
}
