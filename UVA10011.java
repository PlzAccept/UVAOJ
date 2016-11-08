import java.util.*;

public class UVA10011 {

	public static double EPS = 1e-9;

	static class Point {
		double x, y;
		Point(double xx, double yy)  { x = xx; y = yy;					   							  }
		public Point minus(Point a)  { return new Point(x - a.x, y - a.y); 							  }
		public double dot(Point a) 	 { return x * a.x + y * a.y;		   							  }
		public double cross(Point a) { return x * a.y - a.x * y; 		   							  }
		public double dis(Point a)   { return Math.sqrt(Math.pow(x - a.x, 2) + Math.pow(y - a.y, 2)); }
	}

	// return the anlge construct by Vector BA and Vector BC
	public static double angle(Point a, Point b, Point c) {
		return Math.acos(a.minus(b).dot(c.minus(b)) / (b.dis(a) * b.dis(c)));
	}

	public static Point rotate(Point a, double rad) {
		double x = a.x * Math.cos(rad) - a.y * Math.sin(rad);
		double y = a.x * Math.sin(rad) + a.y * Math.cos(rad);
		return new Point(x, y);
	}

	public static double ccw(Point a, Point b, Point c) {
		return b.minus(a).cross(c.minus(a));
	}

	// return the distance from point p to line ab
	public static double disToLine(Point a, Point b, Point p) {
		return Math.abs((p.x - a.x) * (b.y - a.y) - (p.y - a.y) * (b.x - a.x)) / a.dis(b);
	}

	public static double round(double n) {
		return (double)Math.round(n * 100000000) / 100000000;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		while(t-->0) {
			double y = in.nextDouble();
			double x = in.nextDouble();
			double r = in.nextDouble();
			double hy = in.nextDouble();
			double hx = in.nextDouble();

			if(y < 0) {
				y = -y;
				hy = -hy;
			}
			Point origin = new Point(0, 0);
			Point center = new Point(x, y);
			Point house = new Point(hx, hy);

			double ang = Math.PI / 2 - angle(center, new Point(0, 0), new Point(1, 0));
			center = rotate(center, ang);
			house = rotate(house, ang);

			ang = Math.PI / 2 - Math.asin(r / center.y);
			double hyp = Math.sqrt(center.y * center.y - r * r);
			Point p = new Point(hyp * Math.cos(ang), hyp * Math.sin(ang));
			Point p2 = new Point(-p.x, p.y);

			if(Math.abs(r - center.y) < EPS) {
				if(house.y < -EPS) {
					System.out.println("0.000");
					continue;
				}
				double ans = Math.min(Math.abs(house.dis(center) - r), Math.abs(house.y));
				System.out.printf("%.3f\n", round(ans));
				continue;
			}

			if(ccw(origin, p, house) > EPS && ccw(origin, p2, house) < -EPS) {
				if(origin.dis(house) - origin.dis(p) < -EPS) {
					System.out.println("0.000");
					continue;
				}
				double ans = Math.min(Math.min(disToLine(origin, p, house), disToLine(origin, p2, house)), Math.abs(house.dis(center) - r));
				System.out.printf("%.3f\n", round(ans));
			} else {
				System.out.println("0.000");
			}
		}
	}
}