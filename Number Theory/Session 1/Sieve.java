import java.util.ArrayList;
import java.util.TreeMap;

public class Sieve {
	static int N = (int) 1e7;
	static boolean[] sieve = new boolean[N + 1];
	static int[] distPrimes = new int[N + 1];
	static ArrayList<Integer> primes;
	static int[] lpf = new int[N + 1];

	// O(nlog(log(n)))
	static void sieveNoOfDistPrimeFactors() {
		distPrimes[0] = distPrimes[1] = 0;
		for (int i = 2; i <= N; i += 2)
			distPrimes[i]++;
		for (int i = 3; i <= N; i += 2) {
			if (distPrimes[i] != 0)
				continue;
			for (int j = i; j <= N; j += i)
				distPrimes[j]++;
		}
	}

	// O(nlog(log(n)))
	static void sieve() {
		sieve[0] = sieve[1] = true;
		for (long i = 4; i * i <= N; i += 2)
			sieve[(int) i] = true;
		for (long i = 3; i * i <= N; i += 2) {
			if (sieve[(int) i])
				continue;
			for (long j = 2 * i; j <= N; j += i)
				sieve[(int) j] = true;
		}

		primes = new ArrayList<>();
		primes.add(2);
		for (int i = 3; i <= N; i += 2)
			if (!sieve[i])
				primes.add(i);
	}

	// O(nlog(log(n)))
	static void sieveLargestPrimeFactor() {
		for (int i = 2; i <= N; i += 2)
			lpf[i] = 2;
		for (int i = 3; i <= N; i += 2) {
			if (lpf[i] != 0)
				continue;
			for (int j = i; j <= N; j += i)
				lpf[j] = (int) i;
		}
	}

	// O(sqrt(n))
	static TreeMap<Integer, Integer> factor(int n) {
		TreeMap<Integer, Integer> res = new TreeMap<>();
		for (long i = 2; i * i <= n; i++) {
			while (n % i == 0) {
				res.put((int) i, 1 + res.getOrDefault(i, 0));
				n /= i;
			}
		}
		if (n != 1)
			res.put(n, 1);
		return res;
	}

	// O(sqrt(n)/log(n))
	// requires sieve precomputaion before running
	static TreeMap<Integer, Integer> factor2(int n) {
		TreeMap<Integer, Integer> res = new TreeMap<>();
		for (int p : primes) {
			while (n % p == 0) {
				res.put(p, 1 + res.getOrDefault(p, 0));
				n /= p;
			}
		}
		if (n != 1)
			res.put(n, 1);
		return res;
	}

	// O(log(n))
	// requires sieveLargestPrimeFactor precomputation before running
	static TreeMap<Integer, Integer> factor3(int n) {
		TreeMap<Integer, Integer> res = new TreeMap<>();
		while (n != 1) {
			res.put(lpf[n], 1 + res.getOrDefault(lpf[n], 0));
			n /= lpf[n];
		}
		return res;
	}
}