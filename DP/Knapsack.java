import java.util.*;
import java.io.*;

public class Knapsack {

    static int n;
    static int maxW;
    static int v[];
    static int w[];
    static long memo[][];
    public static long solve(int idx, int remw){ //top-down O(n * maxW)
        if(idx == n){
            return 0;
        }
        if(memo[idx][remw] != -1)
            return memo[idx][remw];
        long leave = solve(idx+1, remw);
        long take = Long.MIN_VALUE;
        if(remw-w[idx]>=0){
            take = v[idx] + solve(idx+1, remw-w[idx]);
        }
        long ans = Math.max(take, leave);
        memo[idx][remw] = ans;
        return ans;
    }
    public static long ksBU(){ //bottom-up O(n * maxW)
        long dp[][] = new long[n+1][maxW+1]; //dp[idx][remw]

        //base case (no need to fill, already 0)
        for(int remw=0 ; remw<=maxW ; remw++){
            dp[n][remw] = 0;
        }

        for(int idx=n-1 ; idx>=0 ;idx--){
            for(int remw=0 ; remw<=maxW ; remw++){
                long leave = dp[idx+1][remw];
                long take = Long.MIN_VALUE;
                if(remw-w[idx]>=0){
                    take = v[idx] + dp[idx+1][remw-w[idx]];
                }
                long ans = Math.max(leave, take);
                dp[idx][remw] = ans;
            }
        }
        return dp[0][maxW];
    }
    public static long ksBUSpace(){ //time --> O(n * maxW) , space --> O(maxW)
        //dp[0] --> new array
        //dp[1] --> old array

        long dp[][] = new long[2][maxW+1];

        //base case (no need to fill, already 0)
        for(int remw=0 ; remw<=maxW ; remw++){
            dp[1][remw] = 0;
        }

        for(int idx=n-1 ; idx>=0 ; idx--){
            for(int remw=0 ; remw<=maxW ; remw++){
                long leave = dp[1][remw];
                long take = Long.MIN_VALUE;
                if(remw-w[idx]>=0)
                    take = v[idx] + dp[1][remw-w[idx]];
                long ans = Math.max(leave, take);
                dp[0][remw] = ans;
            }
            dp[1] = dp[0];
            dp[0] = new long[maxW+1];
        }
        return dp[1][maxW];
    }
    public static void main(String[] args) throws Exception{
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
        for(long e[] : memo){
            Arrays.fill(e, -1);
        }
        pw.println(solve(0, maxW));
        pw.println(ksBU());
        pw.println(ksBUSpace());
        pw.flush();
    }

    static Scanner sc;
    static PrintWriter pw;

}