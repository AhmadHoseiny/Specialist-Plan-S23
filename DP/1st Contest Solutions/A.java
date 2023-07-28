

import java.util.*;
import java.io.*;

public class A {

    public static void main(String[] args) throws Exception{
        sc = new Scanner(System.in);
        pw = new PrintWriter(System.out);

        int n = sc.nextInt();
        int maxW = sc.nextInt();
        int v[] = new int[n];
        int w[] = new int[n];
        for(int i=0 ; i<n ; i++){
            w[i] = sc.nextInt();
            v[i] = sc.nextInt();
        }
        long dp[][] = new long[2][maxW+1];
        for(int idx=n-1 ; idx>=0 ; idx--){
            for(int remw=0 ; remw<=maxW ; remw++){
                long ans = dp[1][remw];
                if(remw-w[idx]>=0)
                    ans = Math.max(ans, v[idx] + dp[1][remw-w[idx]]);
                dp[0][remw] = ans;
            }
            dp[1] = dp[0];
            dp[0] = new long[maxW+1];
        }
        pw.println(dp[1][maxW]);


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

