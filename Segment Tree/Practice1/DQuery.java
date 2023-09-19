package segmenTree;

import java.io.*;
import java.util.*;

public class DQuery {

	static Scanner sc;
	static PrintWriter pw;
	static final int N = (int) 1e6 + 10;

	public static void main(String[] args) throws IOException {
		sc = new Scanner(System.in);
		pw = new PrintWriter(System.out);

		int n = sc.nextInt();
		int[] arr = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			arr[i] = sc.nextInt();
		}
		int[] nxtOcc = new int[n + 1], nxtIdx = new int[N];
		Arrays.fill(nxtIdx, n + 1);
		for (int i = n; i > 0; i--) {
			nxtOcc[i] = nxtIdx[arr[i]];
			nxtIdx[arr[i]] = i;
		}

		ArrayList<int[]> allElem = new ArrayList<>();
		for (int i = 1; i <= n; i++) {
			allElem.add(new int[] { nxtOcc[i], i, -1 });
		}

		int q = sc.nextInt();
		for (int i = 1; i <= q; i++) {
			int l = sc.nextInt(), r = sc.nextInt();
			allElem.add(new int[] { r, l, r, i });
		}

		Collections.sort(allElem, (a, b) -> a[0] == b[0] ? (a[2] == -1 ? -1 : b[2] == -1 ? 1 : 0) : (a[0] - b[0]));

		segmentTree sg = new segmentTree(n);
		int[] ans = new int[q + 1];
		for (int i = allElem.size() - 1; i >= 0; i--) {
			int[] a = allElem.get(i);
			if (a[2] == -1) {
				sg.updatePoint(a[1], 1);
			} else {
				ans[a[3]] = sg.query(a[1], a[2]);
			}
		}

		for (int i = 1; i <= q; i++) {
			pw.println(ans[i]);
		}

		pw.flush();
	}

	static class segmentTree {

		int N, Z = 0;
		int[] sgTree;

		public segmentTree(int n) {
			N = 1;
			while (N < n) {
				N <<= 1;
			}
			sgTree = new int[N << 1];
		}

		int merge(int left, int right) {
			return left + right;
		}

		void updatePoint(int idx, int val) { // O(log n)
			idx += N - 1;
			sgTree[idx] += val;
			while (idx > 1) {
				idx >>= 1;
				sgTree[idx] = merge(sgTree[idx << 1], sgTree[(idx << 1) | 1]);
			}
		}

		int query(int l, int r) { // O(log n)
			return query(1, 1, N, l, r);
		}

		int query(int node, int s, int e, int l, int r) {
			if (s > r || e < l) {
				return Z;
			}
			if (s >= l && e <= r) {
				return sgTree[node];
			}
			int mid = (s + e) >> 1;
			int left = node << 1, right = left | 1;
			return merge(query(left, s, mid, l, r), query(right, mid + 1, e, l, r));
		}
	}

	static class Scanner {
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s) {
			br = new BufferedReader(new InputStreamReader(s));
		}

		public String next() throws IOException {
			while (st == null || !st.hasMoreTokens())
				st = new StringTokenizer(br.readLine());
			return st.nextToken();
		}

		public int nextInt() throws IOException {
			return Integer.parseInt(next());
		}
	}
}
