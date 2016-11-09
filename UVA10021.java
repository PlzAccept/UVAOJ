import java.util.*;
import java.io.*;

public class UVA10021 {

	static class Vertex implements Comparable<Vertex> {
		double minDis = Double.POSITIVE_INFINITY;
		List<Vertex> adj = new ArrayList<>();
		public int compareTo(Vertex v) { return Double.compare(minDis, v.minDis); }
	}

	public static void dijkstra(Vertex src, Vertex dst) {
		PriorityQueue<Vertex> pq = new PriorityQueue<>();
		src.minDis = 0;
		pq.add(src);
		while(!pq.isEmpty()) {
			Vertex u = pq.poll();
			if(u == dst) return;
			for(Vertex v : u.adj) {
				if(u.minDis + 1 < v.minDis) {
					v.minDis = u.minDis + 1;
					pq.add(v);
				}
			}
		}
	}

	static boolean[][][][] wall;
	static int w, h;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		br.readLine();
		while(t-->0) {
			String[] line = br.readLine().split(" ");
			w = Integer.parseInt(line[0]); h = Integer.parseInt(line[1]);
			line = br.readLine().split(" ");
			int sx = Integer.parseInt(line[0]) - 1, sy = Integer.parseInt(line[1]) - 1;
			line = br.readLine().split(" ");
			int dx = Integer.parseInt(line[0]) - 1, dy = Integer.parseInt(line[1]) - 1;

			wall = new boolean[h][w][h][w];

			boolean isV = false;;

			while(true) {
				String str = br.readLine();
				if(str == null || str.isEmpty()) break;
				if(str.toLowerCase().equals("v")) isV = true;
				else if(str.toLowerCase().equals("h")) isV = false;
				else {
					line = str.split(" ");
					int n = Integer.parseInt(line[0]) - 1, m = Integer.parseInt(line[1]) - 1;
					if(isV) {
						wall[m][n][m][n + 1] = true;
						wall[m][n + 1][m][n] = true;
					} else {
						wall[m][n][m + 1][n] = true;
						wall[m + 1][n][m][n] = true;
					}
				}
			}

			//h, w stands for location of the cube
			//6 stands for the orientation of the cube
			//0: down, 1: front, 2: left, 3: back, 4: right, 5: up
			Vertex[][][] graph = new Vertex[h][w][6];
			for(int i = 0; i < h; i++) for(int j = 0; j < w; j++) for(int k = 0; k < 6; k++)
				graph[i][j][k] = new Vertex();

			for(int i = 0; i < h; i++) for(int j = 0; j < w; j++) {
				if(check(i, j, i + 1, j)) {
					graph[i][j][0].adj.add(graph[i + 1][j][1]);
					graph[i][j][1].adj.add(graph[i + 1][j][5]);
					graph[i][j][2].adj.add(graph[i + 1][j][2]);
					graph[i][j][3].adj.add(graph[i + 1][j][0]);
					graph[i][j][4].adj.add(graph[i + 1][j][4]);
					graph[i][j][5].adj.add(graph[i + 1][j][3]);
				}
				if(check(i, j, i - 1, j)) {
					graph[i][j][0].adj.add(graph[i - 1][j][3]);
					graph[i][j][1].adj.add(graph[i - 1][j][0]);
					graph[i][j][2].adj.add(graph[i - 1][j][2]);
					graph[i][j][3].adj.add(graph[i - 1][j][5]);
					graph[i][j][4].adj.add(graph[i - 1][j][4]);
					graph[i][j][5].adj.add(graph[i - 1][j][1]);
				}
				if(check(i, j, i, j - 1)) {
					graph[i][j][0].adj.add(graph[i][j - 1][4]);
					graph[i][j][1].adj.add(graph[i][j - 1][1]);
					graph[i][j][2].adj.add(graph[i][j - 1][0]);
					graph[i][j][3].adj.add(graph[i][j - 1][3]);
					graph[i][j][4].adj.add(graph[i][j - 1][5]);
					graph[i][j][5].adj.add(graph[i][j - 1][2]);
				}
				if(check(i, j, i, j + 1)) {
					graph[i][j][0].adj.add(graph[i][j + 1][2]);
					graph[i][j][1].adj.add(graph[i][j + 1][1]);
					graph[i][j][2].adj.add(graph[i][j + 1][5]);
					graph[i][j][3].adj.add(graph[i][j + 1][3]);
					graph[i][j][4].adj.add(graph[i][j + 1][0]);
					graph[i][j][5].adj.add(graph[i][j + 1][4]);
				}
			}

			dijkstra(graph[sy][sx][0], graph[dy][dx][0]);
			if(Double.isFinite(graph[dy][dx][0].minDis)) {
				System.out.println((int)graph[dy][dx][0].minDis);
			} else {
				System.out.println("No solution");
			}

			if(t != 0) System.out.println();
		}
	}

	public static boolean check(int si, int sj, int i, int j) {
		return (i >= 0 && i < h && j >= 0 && j < w && !wall[si][sj][i][j]);
	}
}