
public class MatrixExponentiation {
	static final long mod = (long) 1e9 + 7;

//	O(k^3), k = max dimension of a and b
	long[][] mat_mul(long[][] a, long[][] b) {
		long[][] ans = new long[a.length][b[0].length];
		for (int i = 0; i < a.length; i++)
			for (int j = 0; j < b[0].length; j++)
				for (int k = 0; k < b.length; k++) {
					ans[i][j] += (a[i][k] * b[k][j]) % mod;
					ans[i][j] %= mod;
				}

		return ans;
	}

	long[][] identity(int n) {
		long[][] ans = new long[n][n];
		for (int i = 0; i < n; i++)
			ans[i][i] = 1;

		return ans;
	}

//	O(k^3 * log(n)), k = dimension of a (a is square)
	long[][] mat_pow(long[][] a, long n) {
		long[][] res = identity(a.length);

		while (n > 0) {
			if ((n & 1) != 0)
				res = mat_mul(res, a);

			a = mat_mul(a, a);
			n >>= 1;
		}

		return res;
	}
}
