import java.io.*;
import java.util.*;

public class LIS {

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
		int[] dp = new int[n];

//		for (int i = 0; i < n; i++) { // O(n ^ 2)
//			dp[i] = 1;
//			for (int j = i - 1; j < i; j++) {
//				if (arr[j] < arr[i]) {
//					dp[i] = Math.max(dp[i], dp[j] + 1);
//				}
//			}
//		}
		int ans = 0;
		segmentTreeMax sg = new segmentTreeMax(n);
		for (int i = 0; i < n; i++) { // O(n * log(n))
			int mx = sg.query(1, arr[i] - 1);
			dp[i] = 1 + mx;
			sg.updatePoint(arr[i], dp[i]);
			ans = Math.max(ans, dp[i]);
		}

		pw.println(ans);

		pw.flush();
	}

	static class segmentTreeMax {

		int N;
		int Z = 0; // neutral value
		int[] sgTree;

		public segmentTreeMax(int n) {
			N = 1;
			while (N < n) {
				N <<= 1;
			}
			sgTree = new int[N << 1]; // neutral value
		}

		int merge(int left, int right) {
			return Math.max(left, right);
		}

		void updatePoint(int idx, int val) { // O(log n)
			idx += N - 1;
			sgTree[idx] = Math.max(val, sgTree[idx]);
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
				return Z; // neutral value
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
