import java.util.*;
import java.math.*;

public class UVA10019 {
	
	public static int getone(int n) {
		int count = 0;
		for(int i = 0; i < 32; i++) if((n & (1 << i)) > 0) count++;
		return count;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		while(n-->0) {
			String num = in.next();
			BigInteger a = new BigInteger(num);
			int b1 = getone(Integer.parseInt(a.toString()));
			a = new BigInteger(num, 16);
			int b2 = getone(Integer.parseInt(a.toString()));
			System.out.println(b1 + " " + b2);
		}
	}
}