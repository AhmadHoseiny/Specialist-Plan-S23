package segmenTree;

import java.io.*;
import java.util.*;

public class SerejaAndBrackets {

	static Scanner sc;
	static PrintWriter pw;
	static final int N = (int) 1e6 + 10;

	public static void main(String[] args) throws IOException {
		sc = new Scanner(System.in);
		pw = new PrintWriter(System.out);

		char[] s = sc.next().toCharArray();
		segmentTree sg = new segmentTree(s);
		int q = sc.nextInt();
		while (q-- > 0) {
			int l = sc.nextInt(), r = sc.nextInt();
			pw.println(sg.query(l, r).ans);
		}

		pw.flush();
	}

	static class Node {
		int ans, openSuf, closePref;

		public Node() {

		}

		public Node(char c) {
			if (c == '(')
				openSuf = 1;
			else
				closePref = 1;
		}

		Node merge(Node p) {
			Node res = new Node();
			res.ans = ans + p.ans;
			int match = Math.min(openSuf, p.closePref);
			res.ans += 2 * match;
			res.openSuf = p.openSuf + openSuf - match;
			res.closePref = closePref + p.closePref - match;
			return res;
		}
	}

	static class segmentTree {

		int N;
		Node[] sgTree;

		public segmentTree(char[] arr) {
			int n = arr.length;
			N = 1;
			while (N < n) {
				N <<= 1;
			}
			sgTree = new Node[N << 1]; // neutral value
			build(1, 1, N, arr);
		}

		Node merge(Node left, Node right) {
			return left.merge(right);
		}

		void build(int node, int l, int r, char[] arr) {
			if (l == r) {
				if (l <= arr.length) {
					sgTree[node] = new Node(arr[l - 1]);
				} else {
					sgTree[node] = new Node();
				}
				return;
			}
			int mid = (l + r) >> 1;
			int leftChild = node << 1, rightChild = leftChild | 1;
			build(leftChild, l, mid, arr);
			build(rightChild, mid + 1, r, arr);
			sgTree[node] = merge(sgTree[leftChild], sgTree[rightChild]);
		}

		Node query(int start, int end) {
			return query(1, 1, N, start, end);
		}

		Node query(int node, int l, int r, int start, int end) {
			if (l > end || r < start) {
				return new Node(); // neutral value
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