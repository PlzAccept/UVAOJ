import java.util.*;

public class UVA10002 {
	
	static class Point implements Comparable<Point> {
		long x, y;
		Point(long xx, long yy) {
			x = xx;
			y = yy;
		}
		
		public Point minus(Point b) { return new Point(x - b.x, y - b.y); }
		public long cross(Point b) { return x * b.y - y * b.x;			  }
		public int compareTo(Point b) {
			if(x != b.x) return Long.compare(x, b.x);
			return Long.compare(y, b.y);
		}
	}
	
	public static long ccw(Point a, Point b, Point c) {
		return b.minus(a).cross(c.minus(a));
	}
	
	public static List<Point> ch(List<Point> p) {
		int i, t, k = 0, n = p.size();
		List<Point> H = new ArrayList<>(2 * n);
		Collections.sort(p);
		for(i = 0; i < n; i++) {
			while(k > 1 && ccw(H.get(k - 2), H.get(k - 1), p.get(i)) <= 0) k--;
			if(H.size() > k) H.set(k, p.get(i));
			else H.add(p.get(i));
			k++;
		}
		
		for(i = n - 2, t = k; i >= 0; i--) {
			while(k > t && ccw(H.get(k - 2), H.get(k - 1), p.get(i)) <= 0) k--;
			if(H.size() > k) H.set(k, p.get(i));
			else H.add(p.get(i));
			k++;
		}
		return H.subList(0, k);
	}
	
	public static long area(List<Point> p) {
		long a = 0;
		for(int i = 0; i < p.size() - 1; i++)
			a += p.get(i).cross(p.get(i + 1));
		return a;
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n;
		while((n = in.nextInt()) >= 3) {
			List<Point> p = new ArrayList<>();
			for(int i = 0; i < n; i++) p.add(new Point(in.nextLong(), in.nextLong()));
			p = ch(p);
			long a = area(p) * 3;
			
			double x = 0, y = 0;
			for(int i = 0; i < p.size() - 1; i++) {
				Point p1 = p.get(i);
				Point p2 = p.get(i + 1);
				x += (p1.x + p2.x) * p1.cross(p2);
				y += (p1.y + p2.y) * p1.cross(p2);
			}
			
			x /= (double)a;
			y /= (double)a;
			System.out.printf("%.3f %.3f\n", x, y);
			
		}
	}
}





