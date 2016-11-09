import java.util.*;

public class UVA10017 {
	
	public static void solve(int n, Deque<Integer> src, Deque<Integer> mid, Deque<Integer> target) {
		if(total == 0) return;

		if(n == 1) {
			total--;
			target.offerLast(src.removeLast());
			print();
			return;
		}

		solve(n - 1, src, target, mid);
		if(total == 0) return;
		total--;
		target.offerLast(src.removeLast());
		print();
		solve(n - 1, mid, src, target);
	}

	static Deque<Integer> A, B, C;
	static StringBuilder out;
	static int total;

	public static void print() {
		out.append("A=>");
		if(!A.isEmpty()) out.append("  ");
		for(int n : A) out.append(" " + n);
		out.append("\n");

		out.append("B=>");
		if(!B.isEmpty()) out.append("  ");
		for(int n : B) out.append(" " + n);
		out.append("\n");

		out.append("C=>");
		if(!C.isEmpty()) out.append("  ");
		for(int n : C) out.append(" " + n);
		out.append("\n\n");

	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		out = new StringBuilder();
		int n, m, count = 1;
		while((n = in.nextInt()) + (m = in.nextInt()) != 0) {
			out.append("Problem #" + (count++) + "\n\n");
			total = m;
			A = new LinkedList<>();
			B = new LinkedList<>();
			C = new LinkedList<>();
			for(int i = n; i > 0; i--)
				A.offerLast(i);
			print();
			solve(n, A, B, C);
		}
		System.out.print(out);
	}
}