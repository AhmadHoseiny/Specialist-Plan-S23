

import java.util.*;
import java.io.*;

public class G {
    static int n, k;
    static int arr[], cold[], hot[];

    public static void main(String[] args) throws Exception{
        sc = new Scanner(System.in);
        pw = new PrintWriter(System.out);

        int t = sc.nextInt();
        while(t-- >0){
            n = sc.nextInt();
            k = sc.nextInt();
            arr = new int[n+1];
            for(int i=1 ; i<=n ; i++){
                arr[i] = sc.nextInt();
            }
            cold = sc.nextIntArray(k);
            hot = sc.nextIntArray(k);

            //prev1, prev2, idx --> 1-based
            long dp[][] = new long[n+1][n+1];
            for(int prev1=n-1 ; prev1>=0 ; prev1--){
                for(int prev2=n-1 ; prev2>=0 ; prev2--){
                    int idx = Math.max(prev1, prev2)+1;
                    long ans = Long.MAX_VALUE;
                    long c = cold[arr[idx]-1];
                    long h = hot[arr[idx]-1];
                    //CPU 1
                    ans = Math.min(ans, dp[idx][prev2] + ((arr[idx]==arr[prev1])?h:c));
                    //CPU 2
                    ans = Math.min(ans, dp[prev1][idx] + ((arr[idx]==arr[prev2])?h:c));
                    dp[prev1][prev2] = ans;
                }
            }
            pw.println(dp[0][0]);
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

