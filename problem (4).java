import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
	public static int [] move(int[]array,int indix){
        int x;
        for(int i=indix;i<array.length-1;i++){
            x=array[i];
            array[i]=array[i+1];
            array[i+1]=x;
    }
        
        return array;
    }
    public int[] moveValue(int[] array, int value) {
        for(int i=array.length-1;i>=0;i--){
            if(array[i]==value){
                array=move(array,i);
            }
        }
        return array;
    }
    public static void main(String[] args) {
         Scanner scan = new Scanner(System.in);
        String str = scan.nextLine().replaceAll("\\[|\\]", "");
        int value =scan.nextInt();
        String[] str1 = str.split(", ");
        int[] array = new int[str1.length];    
         if (str1.length == 1 && str1[0].isEmpty()){
            array = new int[]{};
           System.out.println("[]");}
        else{for(int i = 0; i < str1.length; ++i){
            array[i] = Integer.parseInt(str1[i]);}
         System.out.println(Arrays.toString(new Solution().moveValue(array,value)));}
            
    
  }
}