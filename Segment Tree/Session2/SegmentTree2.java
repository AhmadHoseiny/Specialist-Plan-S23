import java.io.*;
import java.util.*;

public class SegmentTree2 {
	static Scanner sc;
	static PrintWriter pw;

	public static void main(String[] args) throws IOException {
		sc = new Scanner(System.in);
		pw = new PrintWriter(System.out);

		pw.flush();
	}

	// segment tree max with range update
	static class segmentTree {

		int N;
		int Z = 0; // neutral value
		long[] sgTree, lazy;

		public segmentTree(int[] arr) {
			int n = arr.length;
			N = 1;
			while (N < n) {
				N <<= 1;
			}
			sgTree = new long[N << 1]; // neutral value
			lazy = new long[N << 1];
			Arrays.fill(lazy, -1);
			build(1, 1, N, arr);
		}

		long merge(long left, long right) {
			return Math.max(left, right);
		}

		void build(int node, int l, int r, int[] arr) { // O(n)
			if (l == r) {
				if (l <= arr.length) {
					sgTree[node] = arr[l - 1];
				}
				return;
			}
			int mid = (l + r) >> 1;
			int leftChild = 2 * node, rightChild = leftChild + 1;
			build(leftChild, l, mid, arr);
			build(rightChild, mid + 1, r, arr);
			sgTree[node] = merge(sgTree[leftChild], sgTree[rightChild]);
		}

		void propagate(int node, int s, int e) { // O(1)
			if (lazy[node] == -1)
				return;
			int left = node << 1, right = left | 1;
			lazy[left] = lazy[left] == -1 ? lazy[node] : (lazy[node] + lazy[left]);
			lazy[right] = lazy[right] == -1 ? lazy[node] : (lazy[node] + lazy[right]);
			sgTree[left] += lazy[node];
			sgTree[right] += lazy[node];
			lazy[node] = -1;

		}

		void updateRange(int l, int r, int val) { // O(log n)
			updateRange(1, 1, N, l, r, val);
		}

		void updateRange(int node, int s, int e, int l, int r, int val) {
			if (s > r || e < l)
				return;
			if (s >= l && e <= r) {
				sgTree[node] += val;
				lazy[node] = lazy[node] == -1 ? val : (lazy[node] + val);
				return;
			}
			propagate(node, s, e);
			int mid = (s + e) >> 1;
			int left = node << 1, right = left | 1;
			updateRange(left, s, mid, l, r, val);
			updateRange(right, mid + 1, e, l, r, val);
			sgTree[node] = merge(sgTree[left], sgTree[right]);
		}

		long query(int l, int r) { // O(log n)
			return query(1, 1, N, l, r);
		}

		long query(int node, int s, int e, int l, int r) {
			if (s > r || e < l) {
				return Z; // neutral value
			}
			if (s >= l && e <= r) {
				return sgTree[node];
			}
			propagate(node, s, e);
			int mid = (s + e) >> 1;
			int left = node << 1, right = left | 1;
			return merge(query(left, s, mid, l, r), query(right, mid + 1, e, l, r));
		}

	}

	// segment tree sum with range update
	static class segmentTreeSum {

		int N;
		int Z = 0; // neutral value
		long[] sgTree, lazy;

		public segmentTreeSum(int[] arr) {
			int n = arr.length;
			N = 1;
			while (N < n) {
				N <<= 1;
			}
			sgTree = new long[N << 1]; // neutral value
			lazy = new long[N << 1];
			Arrays.fill(lazy, -1);
			build(1, 1, N, arr);
		}

		long merge(long left, long right) {
			return left + right;
		}

		void build(int node, int l, int r, int[] arr) { // O(n)
			if (l == r) {
				if (l <= arr.length) {
					sgTree[node] = arr[l - 1];
				}
				return;
			}
			int mid = (l + r) >> 1;
			int leftChild = 2 * node, rightChild = leftChild + 1;
			build(leftChild, l, mid, arr);
			build(rightChild, mid + 1, r, arr);
			sgTree[node] = merge(sgTree[leftChild], sgTree[rightChild]);
		}

		void propagate(int node, int s, int e) { // O(1)
			if (lazy[node] == -1)
				return;
			int left = node << 1, right = left | 1;
			int len = (e - s + 1) / 2;
			lazy[left] = lazy[left] == -1 ? lazy[node] : (lazy[node] + lazy[left]);
			lazy[right] = lazy[right] == -1 ? lazy[node] : (lazy[node] + lazy[right]);
			sgTree[left] += len * lazy[node];
			sgTree[right] += len * lazy[node];

			lazy[node] = -1;

		}

		void updateRange(int l, int r, int val) { // O(log n)
			updateRange(1, 1, N, l, r, val);
		}

		void updateRange(int node, int s, int e, int l, int r, int val) {
			if (s > r || e < l)
				return;
			if (s >= l && e <= r) {
				sgTree[node] += (e - s + 1) * val;
				lazy[node] = lazy[node] == -1 ? val : (lazy[node] + val);
				return;
			}
			propagate(node, s, e);
			int mid = (s + e) >> 1;
			int left = node << 1, right = left | 1;
			updateRange(left, s, mid, l, r, val);
			updateRange(right, mid + 1, e, l, r, val);
			sgTree[node] = merge(sgTree[left], sgTree[right]);
		}

		long query(int l, int r) { // O(log n)
			return query(1, 1, N, l, r);
		}

		long query(int node, int s, int e, int l, int r) {
			if (s > r || e < l) {
				return Z; // neutral value
			}
			if (s >= l && e <= r) {
				return sgTree[node];
			}
			propagate(node, s, e);
			int mid = (s + e) >> 1;
			int left = node << 1, right = left | 1;
			return merge(query(left, s, mid, l, r), query(right, mid + 1, e, l, r));
		}

	}

	// segment tree query point with range update (assign value to range)
	static class segmentTreeAssign {

		int N;
		int Z = -1; // neutral value
		long[] lazy; // here lazy carries the updates

		public segmentTreeAssign(int n) {
			N = 1;
			while (N < n) {
				N <<= 1;
			}
			lazy = new long[N << 1]; // neutral value
		}

		void propagate(int node) {
			if (lazy[node] == Z) {
				return;
			}
			int left = node << 1, right = left | 1;
			lazy[left] = lazy[node];
			lazy[right] = lazy[node];

			lazy[node] = Z;

		}

		void updateRange(int l, int r, int val) {
			updateRange(1, 1, N, l, r, val);
		}

		void updateRange(int node, int s, int e, int l, int r, int val) {
			if (s > r || e < l)
				return;
			if (s >= l && e <= r) {
				lazy[node] = val;
				return;
			}
			propagate(node);
			int mid = (s + e) >> 1;
			int left = node << 1, right = left | 1;
			updateRange(left, s, mid, l, r, val);
			updateRange(right, mid + 1, e, l, r, val);
		}

		long query(int idx) {
			return query(1, 1, N, idx);
		}

		long query(int node, int s, int e, int idx) {

			if (s == e) {
				return lazy[node];
			}
			propagate(node);
			int mid = (s + e) >> 1;
			int left = node << 1, right = left | 1;
			if (idx <= mid) {
				return query(left, s, mid, idx);
			} else {
				return query(right, mid + 1, e, idx);
			}
		}
	}

	static class Scanner {
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s) {
			br = new BufferedReader(new InputStreamReader(s));
		}

		public Scanner(String s) throws IOException {
			br = new BufferedReader(new FileReader(new File(s)));
		}

		public String next() throws IOException {
			while (st == null || !st.hasMoreTokens())
				st = new StringTokenizer(br.readLine());
			return st.nextToken();
		}

		public boolean hasNext() {
			return st.hasMoreTokens();
		}

		public int nextInt() throws IOException {
			return Integer.parseInt(next());
		}

		public double nextDouble() throws IOException {
			return Double.parseDouble(next());
		}

		public long nextLong() throws IOException {
			return Long.parseLong(next());
		}

		public String nextLine() throws IOException {
			return br.readLine();
		}

		public boolean ready() throws IOException {
			return br.ready();
		}

	}

}
