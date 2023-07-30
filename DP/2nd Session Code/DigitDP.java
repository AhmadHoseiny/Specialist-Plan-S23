
import java.util.*;
import java.io.*;

public class DigitDP {
    /*
        Given two   Integers N, K: find all numbers from 0 to N such that their sum of digits is divisible by K.
        Print this number modulo 10^{9}+7.
        1<=N<=10^{10000}
        1<=K<=100

     */


    static final int mod = (int)1e9+7;
    static int n;
    static char s[];
    static int k;
    static int memo[][][];
    public static int dp(int can, int modK, int idx){
        if(idx == n){
            if(modK == 0)
                return 1;
            return 0;
        }
        if(memo[can][modK][idx] != -1)
            return memo[idx][modK][can];
        int ans = 0;
        int UB = (can==0)?s[idx]-'0':9;
        for(int j=0 ; j<=UB ; j++){
            int newModK = (modK + j%k)%k;
            int newCan = (can==0 && j==UB)?0:1;
            ans += dp(newCan, newModK, idx+1);
            ans %= mod;
        }
        return memo[can][modK][idx] = ans;
    }
    public static void main(String[] args) throws Exception{
        sc = new Scanner(System.in);
        pw = new PrintWriter(System.out);

        s = sc.next().toCharArray();
        n = s.length;
        k = sc.nextInt();
        memo = new int[2][k][n];
        for(int e[][] : memo)
            for(int e1[] : e)
                Arrays.fill(e1, -1);
        pw.println(dp(0, 0, 0));


        pw.flush();
    }

    static Scanner sc;
    static PrintWriter pw;

}