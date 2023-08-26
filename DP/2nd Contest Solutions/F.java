

import java.util.*;
import java.io.*;

public class F {
    static int xs, ys;
    static int n;
    static int arr[][];
    static long memo[];
    public static long getDist(int x1, int y1, int x2, int y2){
        return 1L*(x1-x2)*(x1-x2) + 1L*(y1-y2)*(y1-y2);
    }
    public static long dp(int mask){
        if(mask==(1<<n)-1){
            return 0;
        }
        if(memo[mask] != -1)
            return memo[mask];
        long ans = Long.MAX_VALUE;
        for(int i=0 ; i<n ; i++){
            if((mask&(1<<i))==0){
                for(int j=0 ; j<n ; j++){
                    if((mask&(1<<j))==0){
                        long dist = getDist(xs, ys, arr[i][0], arr[i][1])
                                + getDist(arr[i][0], arr[i][1], arr[j][0], arr[j][1])
                                + getDist(arr[j][0], arr[j][1], xs, ys);
                        ans = Math.min(ans, dist + dp((mask|(1<<i))|(1<<j)));
                    }
                }
                break;
            }
        }
        return memo[mask] = ans;
    }

    public static void trace(int mask){
        if(mask==(1<<n)-1){
            return;
        }
        long ans = dp(mask);
        for(int i=0 ; i<n ; i++){
            if((mask&(1<<i))==0){
                for(int j=0 ; j<n ; j++){
                    if((mask&(1<<j))==0){
                        long dist = getDist(xs, ys, arr[i][0], arr[i][1])
                                + getDist(arr[i][0], arr[i][1], arr[j][0], arr[j][1])
                                + getDist(arr[j][0], arr[j][1], xs, ys);
                        long curAns = dist + dp((mask|(1<<i))|(1<<j));
                        if(ans == curAns){
                            if(i==j)
                                pw.print((i+1) + " ");
                            else
                                pw.print((i+1) + " " + (j+1)+" ");
                            pw.print("0 ");
                            trace((mask|(1<<i))|(1<<j));
                            return;
                        }
                    }
                }
            }
        }
    }
    public static void main(String[] args) throws Exception{
        sc = new Scanner(System.in);
        pw = new PrintWriter(System.out);

        xs = sc.nextInt();
        ys = sc.nextInt();
        n = sc.nextInt();
        arr = new int[n][2];
        for(int i=0 ; i<n ; i++)
            for(int j=0 ; j<2 ; j++)
                arr[i][j] = sc.nextInt();
        memo = new long[1<<n];
        Arrays.fill(memo, -1);
        pw.println(dp(0));
        pw.print("0 ");
        trace(0);
        pw.println();



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

