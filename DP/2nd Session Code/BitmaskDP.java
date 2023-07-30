
import java.util.*;
import java.io.*;

public class BitmaskDP {

    //Traveling Salesman Problem
    /*
        Given n cities, a city 1 <= s <= n and a matrix of n rows and n columns representing
        the distance between each pair of cities. Find the length of the shortest
        path that starts at city s, visits each city exactly once and returns to the starting city

        1 <= n <= 16

     */





    static int n;
    static int s;
    static long cost[][];
    static long memo[][];
    public static long dp(int cur, int mask){
        if(mask == (1<<n)-1){
            return cost[cur][s];
        }
        if(memo[cur][mask] != -1)
            return memo[cur][mask];
        long ans = Long.MAX_VALUE;
        for(int i=0 ; i<n ; i++){
            if((mask&(1<<i))==0){
                ans = Math.min(ans, cost[cur][i] + dp(i, mask|(1<<i)));
            }
        }
        return memo[cur][mask] = ans;
    }

    public static void main(String[] args) throws Exception{
        sc = new Scanner(System.in);
        pw = new PrintWriter(System.out);

        n = sc.nextInt();
        s = sc.nextInt() -1;
        cost = new long[n][n];
        for(int i=0 ; i<n ; i++)
            for(int j=0 ; j<n ; j++)
                cost[i][j] = sc.nextLong();
        memo = new long[n][1<<n];
        for(long e[] : memo)
            Arrays.fill(e, -1);

        pw.println(dp(s, 1<<s));

        /*
            Time Complexity of solution = O(n^2 * 2^n)
        */

        pw.flush();
    }

    static Scanner sc;
    static PrintWriter pw;


}