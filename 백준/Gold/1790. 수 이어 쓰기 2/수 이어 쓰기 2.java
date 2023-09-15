import java.io.IOException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		long N = sc.nextInt();
		long k = sc.nextInt();
		long i = 2;
		long endPrev = 9;
		
		if(k < 10) {	// 만약 k가 1자리 수라면
			if(k <= N) System.out.println(k);	// k가 범위내에 있을 경우 k출력
			else System.out.println(-1);	// 범위 밖이면 -1 출력
			return;
		}
		while(true) {	// N이 몇 자리 수인지 파악하기
			endPrev += 9 * i * Math.pow(10, i - 1);
			if(k <= endPrev) {				
				endPrev -= 9 * i * Math.pow(10, i - 1);
				break;
			}
			i++;
		}

		k -= endPrev; 
		long nowNum = (int) (((k - 1)/ i) + Math.pow(10, (i - 1)));	// k가 포함된 수 구하기
		k--;
		
		if(nowNum > N) System.out.println(-1);	// k가 포함된 수가 N보다 크면 -1 출력
		else {
			System.out.println(String.valueOf(nowNum).charAt((int) (k % i)));	// k % i 번째 인덱스 출력
		}
	}
}