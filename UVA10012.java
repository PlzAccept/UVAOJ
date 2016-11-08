import java.util.*;

public class UVA10012 {

	static double min;

	public static double size(double a, double b) {
		if(a == b) return a + b;
		double x = (a * a + a * b) / (b - a);
		return Math.sqrt(Math.pow(x + a + b, 2) - b * b) - Math.sqrt(x * x - a * a);
	}

	public static double calc(double[] a) {
		if(a.length == 1) return a[0] * 2;
		double[] offsets = new double[a.length];
		double max = 0;
		for(int i = 0; i < a.length; i++) {
			double off = a[i];
			for(int j = 0; j < i; j++)
				off = Math.max(off, offsets[j] + size(Math.min(a[i], a[j]), Math.max(a[i], a[j])));
			offsets[i] = off;
			max = Math.max(max, offsets[i] + a[i]);
		}
		return max;
	}

	public static void permute(double[] a, int k) {
		if(k == a.length) {
			min = Math.min(min, calc(a));
			return;
		}
		for(int i = k; i < a.length; i++) {
			swap(a, i, k);
			permute(a, k + 1);
			swap(a, i, k);
		}
	}

	public static void swap(double[] a, int i, int j) {
		double temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		while(n-->0) {
			int m = in.nextInt();
			double[] arr = new double[m];
			for(int i = 0; i < m; i++) arr[i] = in.nextDouble();
			min = Double.POSITIVE_INFINITY;
			permute(arr, 0);
			System.out.printf("%.3f\n", min);
		}
	}
}