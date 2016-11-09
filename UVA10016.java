import java.util.*;
import java.io.*;

public class UVA10016 {

	public static int totalring;
	
	public static void swap(int[][] grid, int i, int j, int m, int n) {
		int temp = grid[i][j];
		grid[i][j] = grid[m][n];
		grid[m][n] = temp;
	}

	public static void udf(int[][] grid, int ring) {
		if(ring >= totalring) return;
		for(int i = ring; i < grid.length / 2; i++) {
			for(int j = ring; j < grid.length - ring; j++) {
				swap(grid, i, j, grid.length - i - 1, j);
			}
		}
	}

	public static void lrf(int[][] grid, int ring) {
		if(ring >= totalring) return;
		for(int i = ring; i < grid.length - ring; i++) {
			for(int j = ring; j < grid.length / 2; j++) {
				swap(grid, i, j, i, grid.length - j - 1);
			}
		}
	}

	public static void fmd(int[][] grid, int ring) {
		if(ring >= totalring) return;
		for(int i = ring; i < grid.length - ring; i++) {
			for(int j = ring; j < i; j++) {
				swap(grid, i, j, j, i);
			}
		}
	}

	public static void fid(int[][] grid, int ring) {
		if(ring >= totalring) return;
		for(int i = ring; i < grid.length - ring; i++) {
			for(int j = ring; j < grid.length - i; j++) {
				swap(grid, i, j, grid.length - 1 - j, grid.length - 1 - i);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		int m = Integer.parseInt(br.readLine());
		while(m-->0) {
			int n = Integer.parseInt(br.readLine());
			int[][] grid = new int[n][n];
			for(int i = 0; i < n; i++) {
				String[] line = br.readLine().split(" ");
				for(int j = 0; j < n; j++) grid[i][j] = Integer.parseInt(line[j]);
			}

			totalring = n % 2 == 0 ? n / 2 : n / 2 + 1;
			for(int i = 0; i < totalring; i++) {
				String[] line = br.readLine().split(" ");
				for(int j = 1; j < line.length; j++) {
				 	switch(Integer.parseInt(line[j])) {
				 		case 1: udf(grid, i); udf(grid, i + 1); break;
				 		case 2: lrf(grid, i); lrf(grid, i + 1); break;
				 		case 3: fmd(grid, i); fmd(grid, i + 1); break;
				 		case 4: fid(grid, i); fid(grid, i + 1); break;
				 	}
				}
			}

			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					if(j != 0) out.append(" ");
					out.append(grid[i][j]);
				}
				out.append("\n");
			}
		}
		System.out.print(out);
	}
}