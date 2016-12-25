import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class DavisStaircase {

    static long[] countStore;
    static long countWays(int sum, List<Integer> steps) {
        if ( sum == 0)
            return 1;
        long count = 0;
        for(int i=0; i<steps.size(); i++) {
            if(sum >= steps.get(i)) {
                if(countStore[sum-steps.get(i)]==-1){
                    countStore[sum-steps.get(i)] = countWays(sum - steps.get(i), steps);
                    count += countStore[sum-steps.get(i)];
                }
                else
                    count += countStore[sum-steps.get(i)];
            }
                
        }
        return count;
        
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int s = in.nextInt();
        List<Integer> steps = new ArrayList<>();
        steps.add(1); steps.add(2); steps.add(3);
        for(int a0 = 0; a0 < s; a0++){
            int n = in.nextInt();
            
            countStore = new long[n];
            for(int i=0; i<n; i++) {
                countStore[i]=-1;
            }
                
            System.out.println(countWays(n,steps));
        }
    }
}
