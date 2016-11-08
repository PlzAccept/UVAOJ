import java.util.*;
import java.io.*;

public class UVA10014 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		while(t-->0) {
			br.readLine();
			int n = Integer.parseInt(br.readLine());
			double a0 = Double.parseDouble(br.readLine());
			double an1 = Double.parseDouble(br.readLine());
			double[] c = new double[n + 1];
			double sum = 0;
			for(int i = 1; i <= n; i++) {
				c[i] = c[i - 1] + Double.parseDouble(br.readLine());
				sum += c[i];
			}
			double ans = an1 + n * a0 - 2 * sum;
			ans /= (n + 1);
			System.out.printf("%.2f\n", ans);

			if(t != 0) System.out.println();
		}
	}
}