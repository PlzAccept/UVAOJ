import java.util.*;

public class UVA10018 {
	
	public static long reverse(long a) {
		String str = String.valueOf(a);
		String rev = "";
		for(int i = str.length() - 1; i >= 0; i--) rev += str.charAt(i);
		return Long.parseLong(rev);
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		while(n-->0) {
			int count = 0;
			long num = in.nextInt();
			do {
				num += reverse(num);
				count++;
			} while(num != reverse(num));
			System.out.println(count + " " + num);
		}
	}
}