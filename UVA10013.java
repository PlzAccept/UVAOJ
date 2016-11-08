import java.util.*;
import java.io.*;

// Use BigInteger will TLE
public class UVA10013 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder out = new StringBuilder();
		while(t-->0) {
			br.readLine();
			int m = Integer.parseInt(br.readLine());
			int[] num = new int[m + 1];
			for(int i = 1; i < m + 1; i++) {
				String[] str = br.readLine().split(" ");
				num[i] = Integer.parseInt(str[0]) + Integer.parseInt(str[1]);
			}
			for(int i = m; i > 0; i--) {
				num[i - 1] += num[i] / 10;
				num[i] %= 10;
			}
			int index = 0;
			while(num[index] == 0) index++;
			for(int i = index; i < m + 1; i++) out.append(num[i]);
			out.append("\n");
			if(t != 0) out.append("\n");
		}
		System.out.print(out);
	}
}