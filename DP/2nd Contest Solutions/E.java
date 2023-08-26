

import java.util.*;
import java.io.*;

public class E {
    static int n;
    static int m;
    static int a[];
    static int extra[][];
    static long memo[][];
    public static long dp(int last, int mask){
        if(Integer.bitCount(mask) == m)
            return 0;
        if(memo[last][mask] != -1)
            return memo[last][mask];
        long ans = Long.MIN_VALUE;
        for(int i=0 ; i<n ; i++){
            if((mask&(1<<i))==0){
                ans = Math.max(ans, 1L*a[i] + extra[last][i] + dp(i, mask|(1<<i)));
            }
        }
        return memo[last][mask] = ans;
    }
    public static void main(String[] args) throws Exception{
        sc = new Scanner(System.in);
        pw = new PrintWriter(System.out);

        n = sc.nextInt();
        m = sc.nextInt();
        int k = sc.nextInt();
        a = sc.nextIntArray(n);
        extra = new int[n][n];
        for(int i=0 ; i<k ; i++){
            int x = sc.nextInt()-1;
            int y = sc.nextInt()-1;
            int c = sc.nextInt();
            extra[x][y] = c;
        }
        memo = new long[n][1<<n];
        for(long e[] : memo){
            Arrays.fill(e, -1);
        }
        long ans = Long.MIN_VALUE;
        for(int i=0 ; i<n ; i++){
            ans = Math.max(ans, a[i]+dp(i, 1<<i));
        }
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

