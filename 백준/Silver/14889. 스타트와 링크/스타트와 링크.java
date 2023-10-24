import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int N;
	static int[][] map;
	static int result = 1000000000;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			String[] temp = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(temp[j]);
			}
		}
		
		int[] num = new int[N/2];
		comb(num, 0, 0);
		System.out.println(result);
	}

	static void comb(int[] num, int r, int start) {
		int minSum;
		if(r == num.length) {
			int[] num2 = new int[N/2];
			outline : for (int i = 0, idx = 0; i < N; i++) {
				for (int j = 0; j < num.length; j++) {
					if(num[j] == i) continue outline;
				}
				num2[idx++] = i;
			}
			minSum = Math.abs(sumNum(num) - sumNum(num2));
			result = Math.min(minSum, result);
			return;
		}
		if(start == N) return;
		num[r] = start;
		comb(num, r + 1, start + 1);
		comb(num, r, start + 1);
	}

	static int sumNum(int[] arr) {
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				if(i != j) {					
					sum += map[arr[i]][arr[j]];
				}
			}
		}
		return sum;
	}
}