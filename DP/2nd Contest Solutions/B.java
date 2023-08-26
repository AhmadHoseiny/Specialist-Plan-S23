

import java.util.*;
import java.io.*;

public class B {

    static final long mod = (long)1e9+7;
    static int n;
    static char k[];
    static int d;
    static long memo[][][];
    public static long dp(int can, int modD, int idx){
        if(idx == n){
            if(modD == 0)
                return 1;
            return 0;
        }
        if(memo[can][modD][idx] != -1)
            return memo[can][modD][idx];
        long ans = 0;
        int UB = (can==0)?k[idx]-'0':9;
        for(int j=0 ; j<=UB ; j++){
            int newCan = (can==0 && j==UB)?0:1;
            int newModD = (modD + j%d)%d;
            ans += dp(newCan, newModD, idx+1);
            ans %= mod;
        }
        return memo[can][modD][idx] = ans;
    }
    public static void main(String[] args) throws Exception{
        sc = new Scanner(System.in);
        pw = new PrintWriter(System.out);

        k = sc.next().toCharArray();
        n = k.length;
        d = sc.nextInt();

        memo = new long[2][d][n+1];
        for(long e[][] : memo){
            for(long e1[] : e){
                Arrays.fill(e1, -1);
            }
        }
        long ans = (dp(0, 0, 0) - 1 + mod) % mod;
        pw.println(ans);


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

