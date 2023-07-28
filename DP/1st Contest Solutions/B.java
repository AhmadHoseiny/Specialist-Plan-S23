

import java.util.*;
import java.io.*;

public class B {

    static int n;
    static long arr[][];
    static long memo[][];

    public static long dp(int u, int idx){
        if(idx == n)
            return 0;
        if(memo[u][idx] != -1)
            return memo[u][idx];
        long ans = dp(u, idx+1); //leave
        ans = Math.max(ans, arr[u][idx] + dp(1^u, idx+1)); //take
        return memo[u][idx] = ans;
    }
    public static void main(String[] args) throws Exception{
        sc = new Scanner(System.in);
        pw = new PrintWriter(System.out);

        n = sc.nextInt();
        arr = new long[2][n];
        for(int j=0 ; j<2 ; j++){
            for(int i=0 ; i<n ; i++){
                arr[j][i] = sc.nextLong();
            }
        }
        memo = new long[2][n];
        for(long e[] : memo)
            Arrays.fill(e, -1);
        pw.println(Math.max(dp(0, 0), dp(1, 0)));


        pw.flush();
    }

    static Scanner sc;
    static PrintWriter pw;

    
}

