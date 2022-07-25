import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
	public int[][] transpose(int[][] array) {
    	int [][] arr =new int[array.length][array.length];
          for(int i=0;i<array.length;i++){
              for (int j=0;j<array.length;j++){
                  arr[i][j]=array[j][i];
              }
          }
        return arr;
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String str = scan.nextLine().replaceAll("\\[|\\]", "");
        String[] str1 = str.split(", ");
        int n=(int)Math.sqrt(str1.length);
        int z=0;
        int[][] array = new int[n][n];
        if (str1.length == 1 && str1[0].isEmpty()){
            array = new int[][]{{}};
           System.out.println("[[]]");}
        else {
            for(int i=0;i<n; i++){
                for(int j=0;j<n;j++){
               array[i][j] = Integer.parseInt(str1[z]);z++;}
            }
            
            System.out.println(Arrays.deepToString(new Solution().transpose(array)));
        }
    }
}