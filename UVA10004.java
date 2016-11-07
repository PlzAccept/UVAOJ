import java.util.*;

public class UVA10004 {
	
	static List<List<Integer>> adj;
	static int[] color;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n;
		while((n = in.nextInt()) != 0) {
			adj = new ArrayList<>();
			for(int i = 0; i < n; i++) adj.add(new ArrayList<>());
			int l = in.nextInt();
			for(int i = 0; i < l; i++) {
				int a = in.nextInt();
				int b = in.nextInt();
				adj.get(a).add(b);
				adj.get(b).add(a);
			}
			color = new int[n];
			System.out.println(dfs(0, 1) ? "BICOLORABLE." : "NOT BICOLORABLE.");
		}
	}
	
	public static boolean dfs(int n, int c) {
		if(color[n] != 0) return c == color[n];
		color[n] = c;
		for(int v : adj.get(n))
			if(!dfs(v, -c))
				return false;
		return true;
	}
}
