import java.util.*;

public class UVA10000 {
	
	static class Vertex {
		int id, longest, dstid;
		List<Vertex> adjacent;
		public Vertex(int id) {
			this.id = id;
			longest = 0;
			dstid = -1;
			adjacent = new ArrayList<>();
		}
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n, c = 1;
		while((n = in.nextInt()) != 0) {
			int src = in.nextInt() - 1;
			List<Vertex> v = new ArrayList<>();
			for(int i = 0; i < n; i++) v.add(new Vertex(i));
			int p, q;
			while((p = in.nextInt()) + (q = in.nextInt()) != 0) {
				p--; q--;
				v.get(p).adjacent.add(v.get(q));
			}
			dfs(v.get(src));
			System.out.printf("Case %d: The longest path from %d has length %d, finishing at %d.\n\n", (c++), (src + 1), v.get(src).longest, v.get(src).dstid + 1);
		} 
	}
	
	public static void dfs(Vertex v) {
		if(v.adjacent.size() == 0) {
			v.dstid = v.id;
			return;
		}
		
		for(Vertex u : v.adjacent) {
			if(u.dstid == -1) dfs(u);
			
			if(u.longest + 1 > v.longest) {
				v.longest = u.longest + 1;
				v.dstid = u.dstid;
			} else if(u.longest + 1 == v.longest) {
				v.dstid = Math.min(u.dstid, v.dstid);
			}
		}
	}
}


