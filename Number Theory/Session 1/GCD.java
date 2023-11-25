public class GCD {

	// O(log(n))
	static long gcd(long a, long b) {
		if (b == 0)
			return a;
		return gcd(b, a % b);
	}

	// O(log(n))
	static long gcdIter(long a, long b) {
		while (b != 0) {
			long tmp = b;
			b = a % b;
			a = tmp;
		}
		return a;
	}

	// O(log(n))
	static long[] extendedGCD(long a, long b) {
		if (b == 0) {
			return new long[] { 1, 0 };
		}
		long[] tmp = extendedGCD(b, a % b);
		long x1 = tmp[0], y1 = tmp[1];
		long q = a / b;
		long x = y1, y = x1 - q * y1;
		return new long[] { x, y };
	}

	// O(1)
	static long[] shiftAns(long k, long a, long b, long x, long y) {
		long g = gcdIter(a, b);
		x += b / g * k;
		y -= a / g * k;
		return new long[] { x, y };
	}

	public static void main(String[] args) {
		long[] tmp = extendedGCD(24, 9);
		System.out.println(tmp[0] + " " + tmp[1]);
	}
}