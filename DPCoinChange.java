import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class DPCoinChange {
    static long[][] store;
    public static long makeChange(int[] coins,int startIndex, int money) {
        if(money==0)
            return 1;
        long count = 0;
        for(int i=startIndex; i<coins.length; i++) {
            if(money>=coins[i]) {
                if(store[i][money-coins[i]]==0)
                    store[i][money-coins[i]] = makeChange(coins,i,money-coins[i]);
                count += store[i][money-coins[i]];
            }
        }
        return count;
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int coins[] = new int[m];
        for(int coins_i=0; coins_i < m; coins_i++){
            coins[coins_i] = in.nextInt();
        }
        store = new long[m][n];
        System.out.println(makeChange(coins,0, n));
    }
}
