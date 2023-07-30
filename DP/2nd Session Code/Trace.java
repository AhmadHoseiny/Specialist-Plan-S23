
import java.util.*;
import java.io.*;

public class Trace {
    static int n;
    static int maxW;
    static int v[];
    static int w[];
    static long memo[][];
    static ArrayList<Integer> res;

    public static long dp(int idx, int remw){
        if(idx == n)
            return 0;
        if(memo[idx][remw] != -1)
            return memo[idx][remw];
        long ans = dp(idx+1, remw);
        if(remw-w[idx]>=0)
            ans = Math.max(ans, v[idx] + dp(idx+1, remw-w[idx]));
        return memo[idx][remw] = ans;
    }
    public static void trace(int idx, int remw){
        if(idx == n)
            return;
        long ans = dp(idx, remw);
        long leave = dp(idx+1, remw);
        long take = Long.MIN_VALUE;
        if(remw-w[idx]>=0)
            take = v[idx] + dp(idx+1, remw-w[idx]);
        if(ans == take){
            res.add(idx);
            trace(idx+1, remw-w[idx]);
        }
        else{ //ans = leave
            trace(idx+1, remw);
        }
    }
    public static void traceItr(){
        int idx = 0;
        int remw = maxW;
        while(idx < n){
            long ans = dp(idx, remw);
            long leave = dp(idx+1, remw);
            long take = Long.MIN_VALUE;
            if(remw-w[idx]>=0)
                take = v[idx] + dp(idx+1, remw-w[idx]);
            if(ans == take){
                res.add(idx);
                remw -= w[idx];
            }
            idx++;
        }
    }


    public static void main(String[] args) {
        sc = new Scanner(System.in);
        pw = new PrintWriter(System.out);

        n = sc.nextInt();
        maxW = sc.nextInt();
        v = new int[n];
        w = new int[n];
        for(int i=0 ; i<n ; i++){
            v[i] = sc.nextInt();
        }
        for(int i=0 ; i<n ; i++){
            w[i] = sc.nextInt();
        }
        memo = new long[n+1][maxW+1];
        for(long e[] : memo)
            Arrays.fill(e, -1);
        pw.println(dp(0, maxW));
        res = new ArrayList();
        trace(0, maxW);
//        traceItr();
        pw.println(res);


        pw.flush();
    }
    static Scanner sc;
    static PrintWriter pw;

}
