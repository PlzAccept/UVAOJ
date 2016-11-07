import java.util.*;
import java.io.*;

public class UVA10010 {
	
	static char[][] grid;
	static int w, h;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		while(t-->0) {
			br.readLine();
			String line = br.readLine();
			String[] str = line.split(" ");
			h = Integer.parseInt(str[0]);
			w = Integer.parseInt(str[1]);
			grid = new char[h][w];
			for(int i = 0; i < h; i++) grid[i] = br.readLine().toLowerCase().toCharArray();
			int k = Integer.parseInt(br.readLine());
			for(int i = 0; i < k; i++) System.out.println(find(br.readLine().toLowerCase()));
			if(t != 0) System.out.println();
		}
	}
	
	public static String find(String str) {
		for(int i = 0; i < h; i++) for(int j = 0; j < w; j++) {
			if(check(i, j, 1, 0, str) || check(i, j, -1, 0, str)
		    || check(i, j, 0, 1, str) || check(i, j, 0, -1, str)
		    || check(i, j, 1, 1, str) || check(i, j, 1, -1, str)
		    || check(i, j, -1, -1, str) || check(i, j, -1, 1, str))
		    	return (i + 1) + " " + (j + 1);
		}
		return null;
	}
	
	public static boolean check(int i, int j, int di, int dj, String str) {
		int index = 0;
		while(i >= 0 && i < h && j >= 0 && j < w && index < str.length() && grid[i][j] == str.charAt(index)) {
			i += di;
			j += dj;
			index++;
		}
		return index == str.length();
	}
}
