import java.util.*;
import java.io.*;

public class UVA10001 {

	static int[] auto, cells;
	static int n, last, first;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str;
		while((str = br.readLine()) != null) {
			String[] line = str.split(" ");
			auto = new int[8];
			int identifier = Integer.parseInt(line[0]);
			for(int i = 0; i < 8; i++) {
				auto[i] = (identifier & (1 << i)) > 0 ? 1 : 0;
			}
			n = Integer.parseInt(line[1]);
			cells = new int[n + 2];
			String binary = line[2];
			for(int i = 0; i < binary.length(); i++)
				cells[i + 1] = binary.charAt(i) - '0';
			
			
			boolean found = false;
			for(int i = 0; i < 8; i++) {
				if(auto[i] == cells[1]) {
					last = i >> 2;
					first = (i & 2) > 0 ? 1 : 0; 
					if(solve((i << 1) & ~8, 2)) {
						found = true;
						break;
					}
				}
			}
			
			System.out.println(found ? "REACHABLE" : "GARDEN OF EDEN");
		}
	}
	
	public static boolean solve(int cell, int i) {
		if(i == n - 1) 
			return auto[cell + last] == cells[i] && solve(((cell + last) << 1) & ~8, i + 1);
		if(i == n)
			return auto[cell + first] == cells[i];
		if(auto[cell + 1] == cells[i] && solve(((cell + 1) << 1) & ~8, i + 1))
			return true;
		if(auto[cell] == cells[i] && solve((cell << 1) & ~8, i + 1))
			return true;
		return false;
	}
}



