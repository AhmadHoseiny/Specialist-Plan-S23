import java.io.*;
import java.util.*;

public class IncreaingSubsequenceWithLenK {

	static Scanner sc;
	static PrintWriter pw;

	public static void main(String[] args) throws IOException {
		sc = new Scanner(System.in);
		pw = new PrintWriter(System.out);
		int n = sc.nextInt(), k = sc.nextInt();
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		k++;
		long[] dp = new long[n];
		Arrays.fill(dp, 1); // K = 1;

//		for (int i = 2; i <= k; i++) { // O(n ^ 2 * k)
//			long[] nextDp = new long[n];
//			for (int idx = 0; idx < n; idx++) {
//				for (int j = 0; j < idx; j++) {
//					if (arr[j] < arr[i]) {
//						nextDp[idx] += dp[j];
//					}
//				}
//			}
//		}

		for (int i = 2; i <= k; i++) { // O(n ^ 2 * k)
			long[] nextDp = new long[n];
			segmentTreeSum sg = new segmentTreeSum(n);
			for (int idx = 0; idx < n; idx++) {
				nextDp[idx] = sg.query(1, arr[idx] - 1);
				sg.updatePoint(arr[idx], dp[idx]);
			}
			dp = nextDp;
		}
		long ans = 0;
		for (long x : dp) {
			ans += x;
		}
		pw.println(ans);
		pw.flush();
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
		}

		long merge(long left, long right) {
			return left + right;
		}

		void updatePoint(int idx, long val) { // O(log n)
			idx += N - 1;
			sgTree[idx] += val;
			while (idx > 1) {
				idx >>= 1;
				sgTree[idx] = merge(sgTree[idx << 1], sgTree[(idx << 1) | 1]);
			}
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
