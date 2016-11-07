import java.util.*;

public class UVA10003 {

	static int[][] dp;
	static int[] cut;
	static int n, l;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while((l = in.nextInt()) != 0) {
			n = in.nextInt();
			cut = new int[n + 2];
			for(int i = 1; i <= n; i++)
				cut[i] = in.nextInt();
			cut[0] = 0;
			cut[n + 1] = l;
			dp = new int[n + 2][n + 2];
			for(int[] a : dp) Arrays.fill(a, -1);
			System.out.println("The minimum cutting is " + solve(0, n + 1) + ".");
		}
	}
	
	public static int solve(int s, int e) {
		if(s + 1 == e) return 0;
		if(dp[s][e] != -1) return dp[s][e];
		int min = Integer.MAX_VALUE;
		for(int i = s + 1; i < e; i++)
			min = Math.min(min, solve(s, i) + solve(i, e) + cut[e] - cut[s]);
		return dp[s][e] = min; 
	}
}
