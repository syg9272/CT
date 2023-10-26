import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split("-");
		int[] arr = new int[temp.length];
		for (int i = 0; i < temp.length; i++) {
			String[] str;
			if(temp[i].contains("+")) {				
				str = temp[i].split("[+]");
				int sum = 0;
				for (int j = 0; j < str.length; j++) {
					sum += Integer.parseInt(str[j]);
				}
				arr[i] = sum;
			}else arr[i] = Integer.parseInt(temp[i]);
		}
		int result = arr[0];
		for (int i = 1; i < arr.length; i++) {
			result -= arr[i];
		}
		System.out.println(result);
	}
}