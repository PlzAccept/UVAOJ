import java.util.*;

public class UVA10009 {

	static class City {
		String name;
		int level;
		City parent;
		
		City(String nm, City pt) { name = nm; parent = pt; level = 0; }
	}
	
	static HashSet<City> visited;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();;
		while(t-->0) {
			List<City> cities = new ArrayList<>();
			HashMap<String, City> map = new HashMap<>();
			City rm = new City("R", null);
			cities.add(rm);
			map.put("Rome", rm);
			int m = in.nextInt(), n = in.nextInt();
			for(int i = 0; i < m; i++) {
				String c1 = in.next(), c2 = in.next();
				City a = null;
				if(map.containsKey(c1)) {
					a = map.get(c1);
				} else {
					a = new City(String.valueOf(c1.charAt(0)), null);
					cities.add(a);
					map.put(c1, a);
				}
				if(map.containsKey(c2)) {
					map.get(c2).parent = a;
				} else {
					City newCity = new City(String.valueOf(c2.charAt(0)), a);
					cities.add(newCity);
					map.put(c2, newCity);
				}
			}
			
			visited = new HashSet<>();
			for(City c : cities) dfs(c);
			for(int i = 0; i < n; i++) {
				System.out.println(findPath(map.get(in.next()), map.get(in.next())));
			}
			if(t != 0) System.out.println();
		}
	}
	
	public static void dfs(City c) {
		if(c.parent == null) return;
		if(visited.contains(c)) return;
		visited.add(c);
		dfs(c.parent);
		c.level = c.parent.level + 1;
	}
	
	public static String findPath(City src, City dst) {
		String p1 = "", p2 = "";
		while(src.level > dst.level) {
			p1 += src.name;
			src = src.parent;
		}
		while(dst.level > src.level) {
			p2 = dst.name + p2;
			dst = dst.parent;
		}
		while(src != dst) {
			p1 += src.name;
			p2 = dst.name + p2;
			src = src.parent;
			dst = dst.parent;
		}
		return p1 + src.name + p2;
	}
}



