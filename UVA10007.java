import java.util.*;
import java.math.*;

/*
 * Catalan numbers times permutation
 */
public class UVA10007 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n;
		while((n = in.nextInt()) != 0) {
			BigInteger ans = BigInteger.ONE;
			for(int i = n * 2; i > n + 1; i--) {
				ans = ans.multiply(BigInteger.valueOf(i));
			}
			System.out.println(ans.toString());
		}
	}
}
