import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int[][] map;	// 톱니바퀴 정보
	static int[] arr;	// 회전 정보
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new int[4][8];	
		String[] temp;
		for (int i = 0; i < 4; i++) {	// 톱니바퀴 받아오기
			temp = br.readLine().split("");
			for (int j = 0; j < 8; j++) {				
				map[i][j] = Integer.parseInt(temp[j]);
			}
		}
		int K = Integer.parseInt(br.readLine());
		int g, r; 
		for (int i = 0; i < K; i++) {	// 회전방향 저장하기
			arr = new int[4];
			temp = br.readLine().split(" ");
			g = Integer.parseInt(temp[0]);
			r = Integer.parseInt(temp[1]);
			arr[g - 1] = r;
			for (int j = g - 1; j < 3; j++) {
				if(j != g - 1 && arr[j] == 0) break;
				if(map[j][2] != map[j + 1][6]) arr[j + 1] = arr[j] == -1 ? 1 : -1;
			}
			for (int j = g - 1; j > 0; j--) {
				if(j != g - 1 && arr[j] == 0) break;
				if(map[j][6] != map[j - 1][2]) arr[j - 1] = arr[j] == -1 ? 1 : -1;
			}
			rotation();
		}
		int result = 0;
		for (int i = 0; i < map.length; i++) {
			result += map[i][0] == 0 ? 0 : Math.pow(2, i);
		}
		System.out.println(result);
	}

	private static void rotation() {	// 회전
		int temp;
		for (int i = 0; i < arr.length; i++) {
			if(arr[i] == 1) {
				temp = map[i][7];
				for (int j = map[i].length - 1; j > 0; j--) {
					map[i][j] = map[i][j - 1];
				}
				map[i][0] = temp;
			}else if(arr[i] == -1){
				temp = map[i][0];
				for (int j = 0; j < map[i].length - 1; j++) {
					map[i][j] = map[i][j + 1];
				}
				map[i][7] = temp;
			}
		}
	}
}