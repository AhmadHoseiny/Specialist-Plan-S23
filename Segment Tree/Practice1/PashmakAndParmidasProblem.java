package segmenTree;

import java.io.*;
import java.util.*;

public class PashmakAndParmidasProblem {

	static Scanner sc;
	static PrintWriter pw;

	public static void main(String[] args) throws IOException {
		sc = new Scanner(System.in);
		pw = new PrintWriter(System.out);

		int n = sc.nextInt();
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}

		int[] prefFun = new int[n + 1], sufFun = new int[n + 1];
		HashMap<Integer, Integer> freqArr = new HashMap<>();
		for (int i = 0; i < n; i++) {
			freqArr.put(arr[i], freqArr.getOrDefault(arr[i], 0) + 1);
			prefFun[i] = freqArr.get(arr[i]);
		}
		segmentTree sg = new segmentTree(n);
		freqArr = new HashMap<>();
		for (int i = n - 1; i >= 0; i--) {
			freqArr.put(arr[i], freqArr.getOrDefault(arr[i], 0) + 1);
			sufFun[i] = freqArr.get(arr[i]);
			sg.updatePoint(sufFun[i], 1);
		}
		
		// prefFun[i] = Fun(1, i, ai)
		// sufFun[j] = Fun(j, n, aj)
		
		// ans of idx i is the number of suffixes with lower Function value than prefFun[i]

		long ans = 0;
		for (int i = 0; i < n; i++) {
			sg.updatePoint(sufFun[i], -1);
			ans += sg.query(1, prefFun[i] - 1);
		}
		pw.println(ans);

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
