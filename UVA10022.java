import java.util.*;

public class UVA10022 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		while(t-->0) {
			int a = in.nextInt(), b = in.nextInt();
			int min = Math.min(a, b), max = Math.max(a, b);
			int minLevel = getLevel(min);
			int maxLevel = getLevel(max);
			if(minLevel == maxLevel) {
				System.out.println(max - min);
				if(t != 0) System.out.println();
				continue;
			}
			int rangeL = 0, rangeR = 0, dis = 0;
			if((minLevel % 2 == 0 && min % 2 == 1) || (minLevel % 2 == 1 && min % 2 == 0)) {
				rangeL = (min - 1) - ((minLevel - 1) * (minLevel - 1) + 1);
				rangeR = rangeL + 2 * (maxLevel - minLevel) + 2;
				dis = 2 * (maxLevel - minLevel) + 1;
			} else {
				rangeL = min - ((minLevel - 1) * (minLevel - 1) + 1);
				rangeR = rangeL + 2 * (maxLevel - minLevel);
				dis = 2 * (maxLevel - minLevel);
			}

			int ans = 0;
			int pos = max - ((maxLevel - 1) * (maxLevel - 1) + 1);
			if((maxLevel % 2 == 0 && max % 2 == 1) || (maxLevel % 2 == 1 && max % 2 == 0)) {
				if(pos >= rangeL && pos <= rangeR) {
					System.out.println(dis - 1);
				} else if(pos < rangeL) {
					System.out.println(rangeL - pos + dis);
				} else {
					System.out.println(pos - rangeR + dis);
				}
			} else {
				if(pos >= rangeL && pos <= rangeR) {
					System.out.println(dis);
				} else if(pos < rangeL) {
					System.out.println(rangeL - pos + dis);
				} else {
					System.out.println(pos - rangeR + dis);
				}
			}

			if(t != 0) System.out.println();
		}
	}

	public static int getLevel(int n) {
		if(n == Math.pow((int)Math.sqrt((double)n), 2)) return (int)Math.sqrt((double)n);
		return (int)Math.sqrt((double)n) + 1;
	}
}