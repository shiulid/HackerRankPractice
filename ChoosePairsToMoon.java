import java.io.*;
import java.util.*;

/*
* Compute in how many ways they can pick a pair of astronauts belonging to different countries. 
* Assume that you are provided enough pairs to let you identify the groups of astronauts 
* even though you might not know their country directly.
* Sample Input:
* 4 2
* 0 1
* 2 3
* ouput: 4 
*/
public class ChoosePairsToMoon {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        Helper obj = new Helper(n);
        int I = scan.nextInt();
        for(int i=0; i<I; i++){
            obj.addPair(scan.nextInt(), scan.nextInt());
        }
        System.out.print(obj.getPairCount());
        
    }
}

class Helper {
    int[] a;
    int highestLabel;
    Map<Integer,Long> map;
    
    public Helper(int n) {
        a = new int[n];
        highestLabel = 0;
        map = new HashMap<>();
    }
    
    public void addPair(int i, int j) {
        if(a[i]==0 && a[j]==0) {
            highestLabel += 1;
            a[i] = highestLabel;
            a[j] = highestLabel;
        }
        else if(a[i]==0) {
            a[i] = a[j];
        }
        else if(a[j]==0) {
            a[j] = a[i];
        }
        else {
            if(a[i]>a[j]) {
                relabelWith(j,a[i]);
            }
            else if(a[j]>a[i]) {
                relabelWith(i,a[j]);
            }
        }
    }
    
    void relabelWith(int index, int label) {
        int l = a[index];
        for(int i=0; i<a.length; i++) {
            if(a[i]==l)
                a[i]=label;
        }
    }
    
    ArrayList<Long> getDistinctCounts() {
        
        for(int i=0; i<a.length; i++) {
            if(map.containsKey(a[i])) 
                map.put(a[i], map.get(a[i])+1);
            else
                map.put(a[i],1l);
            
        }
        
        return (new ArrayList<Long>(map.values()));
    } 
    
    public long getPairCount() {
        List<Long> counts = getDistinctCounts();
        long sum = 0;
        for(int i=0; i<counts.size(); i++) {
            sum += counts.get(i);
        }
        
        long total = 0;
        for(int i=0; i<counts.size(); i++) {
            total += (counts.get(i) * (sum - counts.get(i)));
        }
        long d = 0;
        if(map.containsKey(0))
            d = (map.get(0)*(map.get(0)-1))/2;
        return total/2  + d;
    }
    
}
