import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
	public int fibonacci(int n) {
    	int f1=1,f2=1,f=0;
        if(n==1){return 0;}
        else if (n==2||n==3){return 1;}
        else{
            for(int i=4;i<=n;i++){
                f=f1+f2;
                f1=f2;
                f2=f;
            }
            return f;
        }     
    }
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        int n =scan.nextInt();
        
        System.out.println(new Solution().fibonacci(n));
    }
}