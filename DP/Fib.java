import java.util.*;
import java.io.*;

public class Fib {

    static long memo[];
    public static long fib(int n){ //top-down O(n)
        if(n==0 || n==1)
            return 1;
        if(memo[n] != -1){
            return memo[n];
        }
        long ans = fib(n-1) + fib(n-2);
        memo[n] = ans;
        return ans;
    }
    public static long fibBU(int n){ // bottom-up O(n)
        long dp[] = new long[n+1];
        dp[0] = dp[1] = 1;
        for(int i=2 ; i<=n ; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }
    public static void main(String[] args) throws Exception{
        sc = new Scanner(System.in);
        pw = new PrintWriter(System.out);

        int n = sc.nextInt();
        memo = new long[n+1];
        Arrays.fill(memo, -1);
        pw.println(fib(n));
        pw.println(fibBU(n));

        pw.flush();
    }

    static Scanner sc;
    static PrintWriter pw;

}