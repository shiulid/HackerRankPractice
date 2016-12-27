import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/*
* Get minimum number of jumps required to go from c0 to cn
* sample input:
* 7
* 0 0 1 0 0 1 0
*/

public class JumpingClouds {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int c[] = new int[n];
        for(int c_i=0; c_i < n; c_i++){
            c[c_i] = in.nextInt();
        }
        Clouds clouds = new Clouds(c);
        System.out.println(clouds.getMinJumps(0));
        
    }
}

class Clouds {
    int[] descr;
    int[] storeVals;
    public Clouds(int[] c) {
        descr = c;
        storeVals = new int[descr.length];
    } 
    
    public int getMinJumps(int start) {
        if(start == descr.length-1) {
            return 0;
        }
        
        else if(descr[start] == 1){
            storeVals[start] = 1000;
        }
        
        else if(start == descr.length-2){
            storeVals[start] = 1;
        }
        
        if(storeVals[start]==0) {
            int val = Math.min(1+(getMinJumps(start+1)), 1+getMinJumps(start+2));
            storeVals[start] = val;
        }
        return storeVals[start];
    }
 
}
