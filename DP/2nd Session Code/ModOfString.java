
import java.util.*;
import java.io.*;

public class ModOfString {


    public static void main(String[] args) throws Exception{
        sc = new Scanner(System.in);
        pw = new PrintWriter(System.out);

        char s[] = sc.next().toCharArray();
        int mod = sc.nextInt(); //e.g. 1e9+7
        int res = 0;
        for(int i=0 ; i<s.length ; i++){
            res = (res*10 + (s[i]-'0'))%mod;
        }
        pw.println(res);
        
        pw.flush();
    }

    static Scanner sc;
    static PrintWriter pw;

}