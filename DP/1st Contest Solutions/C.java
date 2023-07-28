


import java.util.*;
import java.io.*;

public class C {

    static final long mod = (long) 1e9 +7;
    static int n, m;
    static char grid[][];
    static long memo[][];

    public static long dp(int i, int j){
        if(i==n-1 && j==m-1){
            return 1;
        }
        if(memo[i][j] != -1)
            return memo[i][j];
        long ans = 0;
        if(i+1<n && grid[i+1][j]!='#'){
            ans += dp(i+1, j);
            ans %= mod;
        }
        if(j+1<m && grid[i][j+1]!='#'){
            ans += dp(i, j+1);
            ans %= mod;
        }
        return memo[i][j] = ans;
    }

    public static void main(String[] args) throws Exception{
        sc = new Scanner(System.in);
        pw = new PrintWriter(System.out);

        n = sc.nextInt();
        m = sc.nextInt();
        grid = new char[n][];
        for(int i=0 ; i<n ; i++){
            grid[i] = sc.next().toCharArray();
        }
        memo = new long[n][m];
        for(long e[] : memo)
            Arrays.fill(e, -1);

        pw.println(dp(0, 0));


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

