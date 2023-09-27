import java.io.*;
import java.util.*;

public class SegmentTrees {

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
		segmentTreeMaxSubArraySum sg = new segmentTreeMaxSubArraySum(arr);

		pw.println(sg.query(1, n).ans);
		pw.flush();
	}
	
	static class segmentTreeNode {
		int l, r;
		long sum;
		segmentTreeNode leftChild, rightChild;

		public segmentTreeNode(int s, int e) {
			l = s;
			r = e;
		}

		long op(long sum2, long sum3) {
			return sum2 + sum3;
		}

		void build(int[] arr) {

			if (l == r) {
				sum = arr[l];
				return;
			}

			int mid = (l + r) >> 1;
			leftChild = new segmentTreeNode(l, mid);
			rightChild = new segmentTreeNode(mid + 1, r);
			leftChild.build(arr);
			rightChild.build(arr);
			sum = op(leftChild.sum, rightChild.sum);
		}

		void updatePoint(int idx, int val) {

			if (l == r) {
				sum = val;
				return;
			}

			int mid = (l + r) >> 1;
			if (idx <= mid) {
				leftChild.updatePoint(idx, val);
			} else {
				rightChild.updatePoint(idx, val);
			}

			sum = op(leftChild.sum, rightChild.sum);
		}

		long query(int start, int end) {
			if (r < start || l > end) { // excluded
				return 0;
			}

			if (l >= start && r <= end) {// fully included
				return sum;
			}
			
			// Partially included
			return op(leftChild.query(start, end), rightChild.query(start, end));

		}

	}
	
	static class segmentTreeSum {

		int N;
		int Z = 0; // neutral value
		long[] sgTree;

		public segmentTreeSum(int n) {
			N = 1;
			while (N < n) {
				N <<= 1;
			}
			sgTree = new long[N << 1]; // neutral value
//			build(1, 1, N, arr);
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

		void updatePoint(int idx, int val) { // O(log n)
			idx += N - 1;
			sgTree[idx] = val;
			while (idx > 1) {
				idx >>= 1;
				sgTree[idx] = merge(sgTree[idx << 1], sgTree[(idx << 1) | 1]);
			}
		}

		long query(int start, int end) { // O(log n)
			return query(1, 1, N, start, end);
		}

		long query(int node, int l, int r, int start, int end) {
			if (l > end || r < start) {
				return Z; // neutral value
			}
			if (l >= start && r <= end) {
				return sgTree[node];
			}
			int mid = (l + r) >> 1;
			int left = node << 1, right = left | 1;
			return merge(query(left, l, mid, start, end), query(right, mid + 1, r, start, end));
		}

		int getKthOne(int k) {
			if (sgTree[1] < k)
				return -1;
			return getKthOne(1, 1, N, k);
		}

		int getKthOne(int node, int l, int r, int k) {

			if (l == r) {
				return l;
			}

			int mid = (l + r) >> 1;
			int left = node << 1, right = left | 1;
			if (sgTree[left] >= k) {
				return getKthOne(left, l, mid, k);
			} else {
				return getKthOne(right, mid + 1, r, (int) (k - sgTree[left]));
			}
		}
	}
	
	static class Pair {
		int minValue, cnt;

		public Pair() {

		}

		public Pair(int val) {
			minValue = val;
			cnt = 1;
		}

		Pair merge(Pair p) {
			Pair res = new Pair();
			if (minValue < p.minValue) {
				res.minValue = minValue;
				res.cnt = cnt;
			} else if (minValue > p.minValue) {
				res.minValue = p.minValue;
				res.cnt = p.cnt;
			} else {
				res.minValue = minValue;
				res.cnt = cnt + p.cnt;
			}
			return res;
		}
	}

	static class segmentTreeMinWithCount {

		int N;
		int Z = Integer.MAX_VALUE; // neutral value
		Pair[] sgTree;

		public segmentTreeMinWithCount(int[] arr) {
			int n = arr.length;
			N = 1;
			while (N < n) {
				N <<= 1;
			}
			sgTree = new Pair[N << 1]; // neutral value
			build(1, 1, N, arr);
		}

		Pair merge(Pair left, Pair right) {
			if (left == null)
				return right;
			if (right == null)
				return left;
			return left.merge(right);
		}

		void build(int node, int l, int r, int[] arr) {
			if (l == r) {
				if (l <= arr.length) {
					sgTree[node] = new Pair(arr[l - 1]);
				}
				return;
			}
			int mid = (l + r) >> 1;
			int leftChild = 2 * node, rightChild = leftChild + 1;
			build(leftChild, l, mid, arr);
			build(rightChild, mid + 1, r, arr);
			sgTree[node] = merge(sgTree[leftChild], sgTree[rightChild]);
		}

		void updatePoint(int idx, int val) {
			idx += N - 1;
			sgTree[idx] = new Pair(val);
			while (idx > 1) {
				idx >>= 1;
				sgTree[idx] = merge(sgTree[idx << 1], sgTree[(idx << 1) | 1]);
			}
		}

		Pair query(int start, int end) {
			return query(1, 1, N, start, end);
		}

		Pair query(int node, int l, int r, int start, int end) {
			if (l > end || r < start) {
				return null; // neutral value
			}
			if (l >= start && r <= end) {
				return sgTree[node];
			}
			int mid = (l + r) >> 1;
			int left = node << 1, right = left | 1;
			return merge(query(left, l, mid, start, end), query(right, mid + 1, r, start, end));
		}
	}

	static class segmentTreeMax {

		int N;
		int Z = 0; // neutral value
		int[] sgTree;

		public segmentTreeMax(int[] arr) {
			int n = arr.length;
			N = 1;
			while (N < n) {
				N <<= 1;
			}
			sgTree = new int[N << 1]; // neutral value
			build(1, 1, N, arr);
		}

		int merge(int left, int right) {
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

		void updatePoint(int idx, int val) { // O(log n)
			idx += N - 1;
			sgTree[idx] = val;
			while (idx > 1) {
				idx >>= 1;
				sgTree[idx] = merge(sgTree[idx << 1], sgTree[(idx << 1) | 1]);
			}
		}

		int query(int start, int end) { // O(log n)
			return query(1, 1, N, start, end);
		}

		int query(int node, int l, int r, int start, int end) {
			if (l > end || r < start) {
				return Z; // neutral value
			}
			if (l >= start && r <= end) {
				return sgTree[node];
			}
			int mid = (l + r) >> 1;
			int left = node << 1, right = left | 1;
			return merge(query(left, l, mid, start, end), query(right, mid + 1, r, start, end));
		}

		int getGreaterThanOrEqual(int x) {
			if (sgTree[1] < x) {
				return -1;
			}
			return getGreaterThanOrEqual(1, 1, N, x);
		}

		int getGreaterThanOrEqual(int node, int l, int r, int x) {

			if (l == r) {
				return l;
			}

			int mid = (l + r) >> 1;
			int left = node << 1, right = left | 1;
			if (sgTree[left] >= x) {
				return getGreaterThanOrEqual(left, l, mid, x);
			} else {
				return getGreaterThanOrEqual(right, mid + 1, r, x);
			}
		}
	}
	
	static class Node {
		long ans, sum, maxPref, maxSuf;

		public Node(int val) {
			ans = sum = maxPref = maxSuf = val;
			// if empty subarray allowed
			// ans = maxPref = maxSuf = Math.max(0, val);
		}

		Node merge(Node p) {
			Node res = new Node(0);

			res.ans = Math.max(ans, Math.max(p.ans, maxSuf + p.maxPref));
			res.sum = sum + p.sum;
			res.maxPref = Math.max(maxPref, sum + p.maxPref);
			res.maxSuf = Math.max(p.maxSuf, maxSuf + p.sum);

			return res;
		}
	}

	static class segmentTreeMaxSubArraySum {

		int N;
		Node[] sgTree;

		public segmentTreeMaxSubArraySum(int[] arr) {
			int n = arr.length;
			N = 1;
			while (N < n) {
				N <<= 1;
			}
			sgTree = new Node[N << 1]; // neutral value
			build(1, 1, N, arr);
		}

		Node merge(Node left, Node right) {
			if (left == null)
				return right;
			if (right == null)
				return left;
			return left.merge(right);
		}

		void build(int node, int l, int r, int[] arr) {
			if (l == r) {
				if (l <= arr.length) {
					sgTree[node] = new Node(arr[l - 1]);
				}
				return;
			}
			int mid = (l + r) >> 1;
			int leftChild = 2 * node, rightChild = leftChild + 1;
			build(leftChild, l, mid, arr);
			build(rightChild, mid + 1, r, arr);
			sgTree[node] = merge(sgTree[leftChild], sgTree[rightChild]);
		}

		void updatePoint(int idx, int val) {
			idx += N - 1;
			sgTree[idx] = new Node((int) (val + sgTree[idx].sum));
			while (idx > 1) {
				idx >>= 1;
				sgTree[idx] = merge(sgTree[idx << 1], sgTree[(idx << 1) | 1]);
			}
		}

		Node query(int start, int end) {
			return query(1, 1, N, start, end);
		}

		Node query(int node, int l, int r, int start, int end) {
			if (l > end || r < start) {
				return null; // neutral value
			}
			if (l >= start && r <= end) {
				return sgTree[node];
			}
			int mid = (l + r) >> 1;
			int left = node << 1, right = left | 1;
			return merge(query(left, l, mid, start, end), query(right, mid + 1, r, start, end));
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
