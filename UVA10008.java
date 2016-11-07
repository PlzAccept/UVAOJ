import java.util.*;

public class UVA10008 {
	
	static class Char implements Comparable<Char> {
		char c;
		int count;
		Char(char cc) { c = cc; count = 0; }
		
		public int compareTo(Char o) {
			if(count != o.count) return Integer.compare(o.count, count);
			return Integer.compare((int)c, (int)o.c);
		}
	}
	
	public static void main(String[] args) {
		List<Char> cs = new ArrayList<>();
		for(char c = 'A'; c <= 'Z'; c++) cs.add(new Char(c));
		Scanner in = new Scanner(System.in);
		int n = Integer.parseInt(in.nextLine());
		while(n-->0) {
			String str = in.nextLine().toLowerCase();
			for(int i = 0; i < str.length(); i++) {
				if(Character.isLetter(str.charAt(i))) {
					cs.get((int)(str.charAt(i) - 'a')).count++;
				}
			}
		}
		Collections.sort(cs);
		for(int i = 0; i < cs.size(); i++) {
			if(cs.get(i).count == 0) break;
			System.out.println(cs.get(i).c + " " + cs.get(i).count);
		}
	}
}
