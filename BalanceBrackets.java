import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class BalanceBrackets {
    
    public static boolean isBalanced(String expression) {
        Stack<Character> stack = new Stack<>();
        for(int i=0; i<expression.length(); i++) {
            if(expression.charAt(i)=='(' || expression.charAt(i)=='[' || expression.charAt(i)=='{') {
                stack.push(expression.charAt(i));
            }
            else {
                if(stack.empty())
                    return false;
                if(expression.charAt(i)==')') {
                    if(stack.peek()=='(') 
                        stack.pop();
                    else
                        return false;
                }
                else if(expression.charAt(i)==']' ){
                    if(stack.peek()=='[') 
                        stack.pop();
                    else
                        return false;
                }
                else if(expression.charAt(i)=='}'){
                    if(stack.peek()=='{') 
                        stack.pop();
                    else
                        return false;
                }
            }
        }
        if(stack.size()>0)
            return false;
        return true;
    }
  
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int a0 = 0; a0 < t; a0++) {
            String expression = in.next();
            System.out.println( (isBalanced(expression)) ? "YES" : "NO" );
        }
    }
}
