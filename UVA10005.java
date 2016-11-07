import java.util.*;

public class UVA10005 {
	
	static double EPS = 1e-9;
	
	static class Point implements Comparable<Point> {
		double x, y;
		Point(double xx, double yy) { x = xx; y = yy; };
		
		public Point minus(Point a) { return new Point(x - a.x, y - a.y); }
		public double dot(Point a)  { return x * a.x + y * a.y; 		  }
		public double cross(Point a){ return x * a.y - y * a.x; 		  }
		public double dis(Point a)  { return Math.sqrt((x-a.x)*(x-a.x)+(y-a.y)*(y-a.y)); }
		
		public int compareTo(Point a) {
			if(x != a.x) return Double.compare(x, a.x);
			return Double.compare(y, a.y);
		}
	}
	
	public static double angle(Point a, Point b, Point c) {
		return Math.acos(a.minus(b).dot(c.minus(b)) / (a.dis(b) * (c.dis(b))));
	}
	
	public static boolean ccw(Point a, Point b, Point c) {
		return b.minus(a).cross(c.minus(a)) > EPS;
	}
	
	public static List<Point> ch(List<Point> p) {
		int i, t, k = 0, n = p.size();
		List<Point> H = new ArrayList<>(2*n);
		Collections.sort(p);
		for(i = 0; i < n; i++) {
			while(k > 1 && !ccw(H.get(k - 2), H.get(k - 1), p.get(i))) k--;
			if(H.size() > k) H.set(k, p.get(i));
			else H.add(p.get(i));
			k++;
		}
		for(i = n - 2, t = k; i >= 0; i--) {
			while(k > t && !ccw(H.get(k - 2), H.get(k - 1), p.get(i))) k--;
			if(H.size() > k) H.set(k, p.get(i));
			else H.add(p.get(i));
			k++;
		}
		return H.subList(0, k);
	}
	
	public static double radiusOfExcircle(Point a, Point b, Point c) {
		double x = a.dis(b), y = a.dis(c), z = b.dis(c);
		double p = (x + y + z) / 2;
		double area = Math.sqrt(p * (p - x) * (p - y) * (p - z));
		return (x * y * z) / (4 * area);
	}
	
	//radius of minimum enclosing circle
	public static double mec(List<Point> p) {
		int a = 0, b = 1;
		while(true) {
			double min = Double.POSITIVE_INFINITY;
			int c = -1;
			for(int i = 0; i < p.size() - 1; i++) {
				if(i != a && i != b) {
					double ang = angle(p.get(a), p.get(i), p.get(b));
					if(ang < min) {
						min = ang;
						c = i;
					}
				}
			}
			if(min - Math.PI / 2 > EPS) return p.get(a).dis(p.get(b)) / 2;
			if(angle(p.get(a), p.get(b), p.get(c)) - Math.PI / 2 > EPS) {
				b = c;
			} else if(angle(p.get(b), p.get(a), p.get(c)) - Math.PI / 2 > EPS) {
				a = c;
			} else {
				return radiusOfExcircle(p.get(a), p.get(b), p.get(c));
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n;
		while((n = in.nextInt()) != 0) {
			List<Point> p = new ArrayList<>();
			for(int i = 0; i < n; i++) p.add(new Point(in.nextDouble(), in.nextDouble()));
			double r = in.nextDouble();
			if(n == 1) {
				System.out.println("The polygon can be packed in the circle.");
				continue;
			}
			double minr = mec(ch(p));
			System.out.println(r - minr > -EPS ? "The polygon can be packed in the circle." : "There is no way of packing that polygon.");
		}
	}
}
