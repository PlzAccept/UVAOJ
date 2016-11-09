import java.util.*;
import java.io.*;

public class UVA10020 {
	
	static class Cover implements Comparable<Cover> {
		int l, h;
		Cover(int ll, int hh) 		  { l = ll; h = hh; 				}
		public int compareTo(Cover c) { return Integer.compare(l, c.l); }
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		StringBuilder out = new StringBuilder();
		int t = in.nextInt();
		while(t-->0) {
			int m = in.nextInt(); 
			List<Cover> covers = new ArrayList<>();
			while(true) {
				int l = in.nextInt(), r = in.nextInt(); 
				if(l == 0 && r == 0) break;
				covers.add(new Cover(l, r));
			}
			Collections.sort(covers);
			List<Cover> ans = new ArrayList<>();
			boolean noSolution = false;
			int index = 0;
			int rightMost = 0;
			Cover opt = null;
			while(index < covers.size()) {
				if(covers.get(index).l > rightMost) {
					if(opt == null) {
						noSolution = true;
						break;
					}
					rightMost = opt.h;
					ans.add(opt);
					opt = null;
				} else {
					if(opt == null || covers.get(index).h > opt.h) {
						opt = covers.get(index);
						if(opt.h >= m) {
							rightMost = opt.h;
							ans.add(opt);
							break;
						}
					}
					index++;
				}
			}
			if(rightMost < m || noSolution) {
				out.append("0\n");
			} else {
				out.append(ans.size() + "\n");
				for(Cover c : ans) out.append(c.l + " " + c.h + "\n");
			}
			if(t != 0) out.append("\n");
		}
		System.out.print(out);
	}
}