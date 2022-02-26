package P11401;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static final long p = 1000000007;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long n = Integer.parseInt(st.nextToken());
        long k = Integer.parseInt(st.nextToken());

        long nFac = factorial(n);
        long denom = (factorial(k)*factorial(n-k))%p;
        System.out.println(nFac*pow(denom,p-2)%p);
    }
    static long factorial(long a){
        long rtn = 1;
        while(a>1){
            rtn = (rtn*a)%p;
            a--;
        }
        return rtn;
    }
    static long pow(long base, long exp){
        long rtn = 1;
        while(exp>0){
            if(exp%2==1){
                rtn = (rtn*base)%p;
            }
            base = (base*base)%p;
            exp/=2;
        }
        return rtn;
    }
}
