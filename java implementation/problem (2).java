import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
	public double average(int[] array) {
           int sum=0;
        for(int i=0;i<array.length;i++){
            sum+=array[i];
        }
        return (double)sum/array.length;
    }
    public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
        String str = scan.nextLine().replaceAll("\\[|\\]", "");
        String[] str1 = str.split(", ");
        int[] array = new int[str1.length];
        if (str1.length == 1 && str1[0].isEmpty()){
            array = new int[]{};
           System.out.println(0.0);
        }else {
            for(int i = 0; i < str1.length; ++i){
               array[i] = Integer.parseInt(str1[i]);}
            System.out.println(new Solution().average(array));
        }
    }
}