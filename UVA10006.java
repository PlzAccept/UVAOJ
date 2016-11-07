import java.util.*;

public class UVA10006 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		StringBuilder out = new StringBuilder();
		int n;
		while((n = in.nextInt()) != 0) {
			if(isPrime(n))
				out.append(n + " is normal.\n");
			else {
				boolean isCar = true;
				for(int i = 2; i < n && isCar; i++)
					if(powmod(i, n, n) != i) {
						isCar = false;
					}
				if(isCar) out.append("The number " + n + " is a Carmichael number.\n");
				else out.append(n + " is normal.\n");
			}
		}
		System.out.print(out);
	}
	
	public static long powmod(long a, long n, long b) {
		if(n == 1) return a % b;
		if(n % 2 == 0) {
			long c = powmod(a, n / 2, b) % b;
			return (c * c) % b;
		} else 
			return (a * powmod(a, n - 1, b)) % b;
	}
	
	public static boolean isPrime(int n) {
		for(int i = 2; i <= Math.sqrt(n); i++)
			if(n % i == 0) return false;
		return true;
	}
}
