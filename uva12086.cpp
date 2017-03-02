#include <string>
#include <cstring>
#include <vector>
using namespace std;

#define MAXN 200005

int st[2*MAXN];
char str[10];
int n;

int query(int l, int r) {
	int ret = 0;
	for(l += n, r += n; l < r; l >>= 1, r >>= 1) {
		if(l&1) ret += st[l++];
		if(r&1) ret += st[--r];
	}
	return ret;
}

void update(int p, int v) {
	st[p += n] = v;
	for(p >>= 1; p; p >>= 1) st[p] = st[p<<1] + st[p<<1|1];
}

int main() {
	int ct = 0;
	while(1) {
		scanf("%d", &n);
		if(n == 0) return 0;
		if(ct != 0) printf("\n");
		printf("Case %d:\n", ++ct);
		for(int i = 0; i < n; i++) scanf("%d", st + n + i);
		for(int i = n - 1; i; i--) st[i] = st[i<<1] + st[i<<1|1];
		while(1) {
			scanf("%s", str);
			if(str[0] == 'E') break;
			int a, b;
			scanf("%d %d", &a, &b);
			if(str[0] == 'M') printf("%d\n", query(a - 1, b));
			else update(a - 1, b);
		}
	}
}