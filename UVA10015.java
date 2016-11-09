import java.util.*;

public class UVA10015 {

	static List<Integer> primes = new ArrayList<>();
	static boolean[] bs;

	public static void sieve() {
		int size = 1000001;
		bs = new boolean[size];
		Arrays.fill(bs, true);
		bs[0] = bs[1] = false;
		for(long i = 2; i < size; i++) if(bs[(int)i]) {
			for(long j = i * i; j < size; j += i) bs[(int)j] = false;
			primes.add((int)i);
		}
	}

	public static void main(String[] args) {
		sieve();
		Scanner in = new Scanner(System.in);
		int n;
		while((n = in.nextInt()) != 0) {
			List<Integer> list = new ArrayList<>();
			for(int i = 1; i <= n; i++) list.add(i);
			int index = 0;
			int count = 0;
			while(list.size() > 1) {
				index = (index + primes.get(count) - 1) % list.size();
				list.remove((int)index);
				count++;
			} 
			System.out.println(list.get(0));
		}
	}
}