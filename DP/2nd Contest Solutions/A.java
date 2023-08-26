

import java.util.*;
import java.io.*;

public class A {
    static int n;
    static int r[];
    static int c[];
    static long memo[][];
    public static long dp(int st, int en){
        if(st == en)
            return 0;
        if(memo[st][en] != -1)
            return memo[st][en];
        long ans = Long.MAX_VALUE;
        for(int split=st ; split<en ; split++){
            long curAns = 1L*r[st]*c[split]*c[en] + dp(st, split) + dp(split+1, en);
            ans = Math.min(ans, curAns);
        }
        return memo[st][en] = ans;
    }
    public static void trace(int st, int en){
        if(st == en){
            pw.print("A" + (st+1));
            return;
        }
        long ans = dp(st, en);
        int fSplit = -1;
        for(int split=st ; split<en ; split++){
            long curAns = 1L*r[st]*c[split]*c[en] + dp(st, split) + dp(split+1, en);
            if(ans == curAns){
                fSplit = split;
                break;
            }
        }
        pw.print("(");
        trace(st, fSplit);
        pw.print(" x ");
        trace(fSplit+1, en);
        pw.print(")");
    }
    public static void main(String[] args) throws Exception{
        sc = new Scanner(System.in);
        pw = new PrintWriter(System.out);

        int tc = 1;
        while(true){
            n = sc.nextInt();
            if(n==0)
                break;
            pw.print("Case " + (tc++) + ": ");
            r = new int[n];
            c = new int[n];
            for(int i=0 ; i<n ; i++){
                r[i] = sc.nextInt();
                c[i] = sc.nextInt();
            }
            memo = new long[n][n];
            for(long e[] : memo)
                Arrays.fill(e, -1);
            dp(0, n-1);
            trace(0, n-1);
            pw.println();
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

