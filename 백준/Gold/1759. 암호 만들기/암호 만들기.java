import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static StringBuilder sb;
	static int L;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		String[] arr = br.readLine().split(" ");	// 문자 입력받기
		String[] str = new String[L];	// L개 뽑아서 담아놓을 배열 생성
		Arrays.sort(arr);	// 입력받은 문자 사전 순으로 정렬
		comb(arr, str, 0, 0);	// 뽑기 시작 ~!
		System.out.println(sb);	// 뽑은 결과 저장해 둔 sb 출력
	}
	static void comb(String[] arr, String[] str, int r, int start) {	// 조합으로 C개 중에 L개 뽑기
		if(r == L) {
			int vowel = 0;
			int consonant = 0;
			for (String s : str) {
				if(s.equals("a") | s.equals("e") | s.equals("i") |
						s.equals("o") | s.equals("u")) {
					vowel++;	// 모음 개수 세기
				}else consonant++;	// 자음 개수 세기
			}
			if(vowel >= 1 && consonant >= 2) {	// 모음 1개 이상이고, 자음 2개 이상일 때 암호 가능해짐
				for (String s : str) {
					sb.append(s);
				}
				sb.append("\n");	// 남은 자음이 2이상일 경우 암호가 될 수 있음
			}
			return;
		}
		if(start == arr.length) return;
		str[r] = arr[start];
		comb(arr, str, r + 1, start + 1);
		comb(arr, str, r, start + 1);
	}
}