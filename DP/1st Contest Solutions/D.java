package dp_1_contest;


import java.util.*;
import java.io.*;

public class D {

    static final long mod = (long) 1e9 +7;
    static int n;
    static long maxW;
    static int v[];
    static long w[];
    static long memo[][];
    public static long dp(int idx, int val){
        if(idx == n){
            if(val == 0)
                return 0;
            return (long)1e13;
        }
        if(memo[idx][val] != -1)
            return memo[idx][val];
        long ans = dp(idx+1, val);
        if(val-v[idx]>=0){
            ans = Math.min(ans, w[idx] + dp(idx+1, val-v[idx]));
        }
        return memo[idx][val] = ans;
    }
    public static void main(String[] args) throws Exception{
        sc = new Scanner(System.in);
        pw = new PrintWriter(System.out);

        n = sc.nextInt();
        maxW = sc.nextLong();
        v = new int[n];
        w = new long[n];
        int sum = 0;
        for(int i=0 ; i<n ; i++) {
            w[i] = sc.nextLong();
            v[i] = sc.nextInt();
            sum += v[i];
        }

        memo = new long[n][sum+1];
        for(long e[] : memo)
            Arrays.fill(e, -1);
        for(int i=sum ; i>=0 ; i--){
            long ansW = dp(0, i);
            if(ansW<=maxW){
                pw.println(i);
                break;
            }
        }


        pw.flush();
    }

    static Scanner sc;
    static PrintWriter pw;

    static class Scanner {
        StringTokenizer st;
        BufferedReader br;

        public Scanner(InputStream s) {
            br = new BufferedReader(new InputStreamReader(s));
        }

        public Scanner(String r) throws Exception {
            br = new BufferedReader(new FileReader(new File(r)));
        }

        public String next() throws IOException {
            while (st == null || !st.hasMoreTokens())
                st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        public long nextLong() throws IOException {
            return Long.parseLong(next());
        }

        public String nextLine() throws IOException {
            return br.readLine();
        }

        public double nextDouble() throws IOException {
            String x = next();
            StringBuilder sb = new StringBuilder("0");
            double res = 0, f = 1;
            boolean dec = false, neg = false;
            int start = 0;
            if (x.charAt(0) == '-') {
                neg = true;
                start++;
            }
            for (int i = start; i < x.length(); i++)
                if (x.charAt(i) == '.') {
                    res = Long.parseLong(sb.toString());
                    sb = new StringBuilder("0");
                    dec = true;
                } else {
                    sb.append(x.charAt(i));
                    if (dec)
                        f *= 10;
                }
            res += Long.parseLong(sb.toString()) / f;
            return res * (neg ? -1 : 1);
        }

        public Long[] nextLongArray(int n) throws IOException {
            Long[] a = new Long[n];
            for (int i = 0; i < n; i++)
                a[i] = nextLong();
            return a;
        }

        public int[] nextIntArray(int n) throws IOException {
            int[] a = new int[n];
            for (int i = 0; i < n; i++)
                a[i] = nextInt();
            return a;
        }

        public Integer[] nextIntegerArray(int n) throws IOException {
            Integer[] a = new Integer[n];
            for (int i = 0; i < n; i++)
                a[i] = nextInt();
            return a;
        }

        public boolean ready() throws IOException {
            return br.ready();
        }
    }

}

