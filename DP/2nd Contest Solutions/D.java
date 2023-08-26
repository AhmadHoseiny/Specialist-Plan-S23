

import java.util.*;
import java.io.*;

public class D {

    static final long mod = (long)1e9+7;
    static int n;
    static char l[];
    static char r[];
    static int m;
    static int d;
    static long memo[][][][];
    static int mods[][];
    public static void pre(){
        mods = new int[10][m];
        for(int j=0 ; j<10 ; j++)
            for(int modM=0 ; modM<m ; modM++)
                mods[j][modM] = (modM*10 + j)%m;

    }
    public static long dp(int canD, int canU, int modM, int idx){
        if(idx == n){
            if(modM == 0)
                return 1;
            return 0;
        }
        if(memo[canD][canU][modM][idx] != -1)
            return memo[canD][canU][modM][idx];
        long ans = 0;
        int LB = (canD==0)?l[idx]-'0':0;
        int UB = (canU==0)?r[idx]-'0':9;
        for(int j=LB ; j<=UB ; j++){
            if(((idx&1)==1 && j!=d) || ((idx&1)==0) && j==d)
                continue;
            int newCanU = (canU==0 && j==UB)?0:1;
            int newCanD = (canD==0 && j==LB)?0:1;
            int newModM = mods[j][modM];
            ans += dp(newCanD, newCanU, newModM, idx+1);
            ans %= mod;
        }
        return memo[canD][canU][modM][idx] = ans;
    }

    public static void main(String[] args) throws Exception{
        sc = new Scanner(System.in);
        pw = new PrintWriter(System.out);

        m = sc.nextInt();
        d = sc.nextInt();
        l = sc.next().toCharArray();
        r = sc.next().toCharArray();
        n = l.length;

        memo = new long[2][2][m][n];
        for(long e[][][] : memo)
            for(long e1[][] : e)
                for(long e2[] : e1)
                    Arrays.fill(e2, -1);
        pre();
        pw.println(dp(0, 0, 0, 0));


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

