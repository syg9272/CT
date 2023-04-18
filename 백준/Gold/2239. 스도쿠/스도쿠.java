import java.awt.Window;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int[][] map;
    static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		map = new int[9][9];
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int i = 0; i < 9; i++) {
			char[] c = br.readLine().toCharArray();
			for (int j = 0; j < 9; j++) {
				map[i][j] = c[j] - '0';
			}
		}
		sudoku(0, 0);
	}

	private static void sudoku(int r, int c) {
		if(r == 9) {	
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					sb.append(map[i][j]);
				}
				sb.append("\n");
			}
			System.out.println(sb);
			System.exit(0);
		}
		if(map[r][c] != 0) {
			if(c + 1 == 9) sudoku(r + 1, 0);
			else sudoku(r, c + 1);
			return;
		} else {			
			for (int i = 1; i <= 9; i++) {
				if(check(r, c, i)) {
					map[r][c] = i;
					if(c + 1 == 9) sudoku(r + 1, 0);
					else sudoku(r, c + 1);
					map[r][c] = 0;
				}
			}
			return;
		}
	}

	private static boolean check(int r, int c, int num) {
		for (int j = 0; j < 9; j++) {
			if(map[r][j] == num) return false;
		}
		for (int i = 0; i < 9; i++) {
			if(map[i][c] == num) return false;
		}
		int x = (r / 3) * 3;
		int y = (c / 3) * 3;
		for (int i = x; i < x + 3; i++) {
			for (int j = y; j < y + 3; j++) {
				if(map[i][j] == num) return false;
			}
		}
		return true;
	}
}