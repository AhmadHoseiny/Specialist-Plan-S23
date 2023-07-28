

import java.util.*;
import java.io.*;

public class F {
    static int n, m;
    static char a[], b[];
    static int memo[][];
    public static int dp(int idx1, int idx2){
        if(idx1==n && idx2==m)
            return 0;
        if(memo[idx1][idx2] != -1)
            return memo[idx1][idx2];

        int ans = Integer.MAX_VALUE;
        if(idx1<n && idx2<m){
            if(a[idx1] == b[idx2])
                ans = Math.min(ans, dp(idx1+1, idx2+1)); //leave
            ans = Math.min(ans, 1 + dp(idx1, idx2+1)); //insert
            ans = Math.min(ans, 1 + dp(idx1+1, idx2)); //delete
            ans = Math.min(ans, 1 + dp(idx1+1, idx2+1)); //replace
        }
        else if(idx1<n){
            ans = Math.min(ans, 1 + dp(idx1+1, idx2)); //delete
        }
        else{ // idx2<m
            ans = Math.min(ans, 1 + dp(idx1, idx2+1)); //insert
        }
        return memo[idx1][idx2] = ans;
    }
    public static void trace(int idx1, int idx2){
        if(idx1==n && idx2==m)
            return;
        int ans = dp(idx1, idx2);
        if(idx1<n && idx2<m){
            int leave = Integer.MAX_VALUE;
            if(a[idx1] == b[idx2])
                leave = dp(idx1+1, idx2+1); //leave
            int ins = 1 + dp(idx1, idx2+1); //insert
            int del = 1 + dp(idx1+1, idx2); //delete
            int rep = 1 + dp(idx1+1, idx2+1); //replace
            if(ans == leave){
                trace(idx1+1, idx2+1);
            }
            else if(ans == ins){
                pw.println("INSERT " + (idx2+1) + " " + b[idx2]);
                trace(idx1, idx2+1);
            }
            else if(ans == del){
                pw.println("DELETE " + (idx2+1));
                trace(idx1+1, idx2);
            }
            else{
                pw.println("REPLACE " + (idx2+1) + " " + b[idx2]);
                trace(idx1+1, idx2+1);
            }
        }
        else if(idx1<n){
            pw.println("DELETE " + (idx2+1));
            trace(idx1+1, idx2);
        }
        else{ // idx2<m
            pw.println("INSERT " + (idx2+1) + " " + b[idx2]);
            trace(idx1, idx2+1);
        }
    }
    public static void main(String[] args) throws Exception{
        sc = new Scanner(System.in);
        pw = new PrintWriter(System.out);

        a = sc.next().toCharArray();
        b = sc.next().toCharArray();
        n = a.length;
        m = b.length;

        memo = new int[n+1][m+1];
        for(int e[] : memo)
            Arrays.fill(e, -1);

        pw.println(dp(0, 0));
        trace(0, 0);

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

