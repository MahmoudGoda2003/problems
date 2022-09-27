import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
	public int[] sumEvenOdd(int[] array) {
    	int[] arr={0,0};
        for(int i=0;i<array.length;i++ ){
            if (array[i]%2==0){
                arr[0]+=array[i];
            }else{arr[1]+=array[i];}
        }
        return arr;  
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String str = scan.nextLine().replaceAll("\\[|\\]", "");
        String[] str1 = str.split(", ");
        int[] array = new int[str1.length];
        if (str1.length == 1 && str1[0].isEmpty()){
            array = new int[]{};
           System.out.println("[0, 0]");
        }else {
            for(int i = 0; i < str1.length; ++i){
               array[i] = Integer.parseInt(str1[i]);}
             int[] arr=new Solution().sumEvenOdd(array);
             System.out.print(Arrays.toString(arr));
        }
    }
    }