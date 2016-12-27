import java.io.*;
import java.util.*;

/*
* Example
* www.checkdomain.com => [check domain]
* www.bigrock.com => [big rock]
* #honestyhour => [honesty hour]
* #beinghuman => [being human]
*/

public class HashTagURLSegmentation {
    static String dictionary;

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        dictionary = loadWords();
        Scanner scan = new Scanner(System.in);
        int n = Integer.parseInt(scan.nextLine());
        for(int i=0; i<n; i++) {
            String str = scan.nextLine();
            str = getString(str);
            List<String> tokens = new ArrayList<>();
            isNextValidWord(str,tokens);
            displayTokens(tokens);
        }
    }
    
    public static boolean isNextValidWord(String str, List<String> list) 
    {
        if(str.length()==0) {
            return true;
        }
        int i=str.length();
        
        while(i>=0) {
            
            while(i>0 && !isValidWord(str.substring(0,i)) ) {
                i--;
            }
            
            String word = str.substring(0,i);
            if(isValidWord(word))
                System.err.println(word);
            if(isValidWord(word) && isNextValidWord(str.substring(i),list)) {
                list.add(0,word);
                return true;
            }
            else {
                 i--;
            }
        }
        
        
        return false;
            
    }
    
    public static boolean isValidWord(String str) {
        if(dictionary.contains(" "+str+" ") )
            return true;
        else {
            try {
                Double.parseDouble(str);
                return true;
                
            }
            catch (Exception e){
                return false;
            }
        }

    }
    
    public static String getString(String str) {
        
        if(isHashTag(str)) {
            str = getHashTagString(str);
        }
        else {
            str = getURLString(str);
        }            
        
        return str;
    }
    
    public static String getHashTagString(String str) {
        return str.substring(1);
    }
    
    public static String getURLString(String str) {
        if(str.length()>4) {
            if(str.substring(0,4).equals("www."))
                str = str.substring(4);
        }
        
        int i = str.indexOf('.');
        return str.substring(0,i);
        
    }
    
    public static boolean isHashTag(String str) {
        return str.charAt(0)=='#';
    }
    
    public static void displayTokens(List<String> tokens) {
        for(int i=0; i<tokens.size()-1; i++) {
            System.out.print(tokens.get(i) + " ");
        }
        System.out.println(tokens.get(tokens.size()-1));
    }
    
    public static String loadWords() {
        
        String res = new String(" ");
        try {
            Scanner scan = new Scanner(new File("words.txt"));
            while(scan.hasNext()) {
                String word =  scan.next();
                res = res.concat(word.toLowerCase() + " ");
            }
        } catch(Exception e) {}
        
        
        return res;
    }
}
