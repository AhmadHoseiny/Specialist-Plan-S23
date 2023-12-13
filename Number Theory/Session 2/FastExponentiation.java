public class FastExponentiation {
	static final long mod = (long) 1e9 + 7;

//	O(log(n))
	static long fastPower(long a, long n) {
		long res = 1;
		while (n != 0) {
			if ((n & 1) == 1) {
				res *= a;
				res %= mod;
			}
			a *= a;
			a %= mod;
			n >>= 1;
		}
		return res;
	}

//	O(log(mod))
	static long modInverse(long b) {
		return fastPower(b, mod - 2);
	}
}
