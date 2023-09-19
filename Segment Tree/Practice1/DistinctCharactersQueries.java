package segmenTree;

import java.io.*;
import java.util.*;

public class DistinctCharactersQueries {

	static Scanner sc;
	static PrintWriter pw;

	public static void main(String[] args) throws IOException {
		sc = new Scanner(System.in);
		pw = new PrintWriter(System.out);

		char[] arr = sc.next().toCharArray();
		segmentTree sg = new segmentTree(arr);
		int q = sc.nextInt();
		while (q-- > 0) {
			int type = sc.nextInt();
			if (type == 1) {
				int pos = sc.nextInt();
				char c = sc.next().charAt(0);
				sg.updatePoint(pos, c);
			} else {
				int l = sc.nextInt(), r = sc.nextInt();
				pw.println(Integer.bitCount(sg.query(l, r)));
			}
		}

		pw.flush();
	}

	static class segmentTree {

		int N, Z = 0;
		int[] sgTree;

		public segmentTree(char[] arr) {
			int n = arr.length;
			N = 1;
			while (N < n) {
				N <<= 1;
			}
			sgTree = new int[N << 1];
			build(1, 1, N, arr);

		}

		void build(int node, int s, int e, char[] arr) { // O(n)
			if (s == e) {
				if (s <= arr.length) {
					sgTree[node] = 1 << (arr[s - 1] - 'a');
				}
				return;
			}
			int mid = (s + e) >> 1;
			int left = node << 1, right = left | 1;
			build(left, s, mid, arr);
			build(right, mid + 1, e, arr);
			sgTree[node] = merge(sgTree[left], sgTree[right]);
		}

		int merge(int left, int right) {
			return left | right;
		}

		void updatePoint(int idx, char val) { // O(log n)
			idx += N - 1;
			sgTree[idx] = 1 << (val - 'a');
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
