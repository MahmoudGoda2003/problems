import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.awt.Point;


interface IPlayersFinder {
    
    java.awt.Point[] findPlayers(String[] photo, int team, int threshold);
}


public class PlayersFinder implements IPlayersFinder{
    
    
public static  Scanner scan = new Scanner(System.in);
static int maxX=0,maxY=0,minX=0,minY=0,counter=0,z=0;
        
public static void print(Point[]answer){
    
System.out.print("[");  
for(int i=0;i<z;i++){
    System.out.print("("+answer[i].x+", "+answer[i].y+")");
    if(i!=z-1){
    System.out.print(", ");
    }
}
    System.out.print("]"); 
}

    
    
public static java.awt.Point[] sort(Point[] answer){
    Point temp=new Point(0,0);
    for(int i=0;i<z;i++){
        for(int j=i+1;j<z;j++){
            if (answer[i].x>answer[j].x){
                temp=answer[i];
                answer[i]=answer[j];
                answer[j]=temp;
            }else if (answer[i].x==answer[j].x&&answer[i].y>answer[j].y){
                temp=answer[i];
                answer[i]=answer[j];
                answer[j]=temp;
            }
        }
    }
    return answer;
}
    
    
    
public  java.awt.Point[] findPlayers(String[] array,int ind,int area){    
    Point[]answer=new Point[(array[0].length()*array.length)/2]; 
    String[][] arr=new String[array.length][array[0].length()];
    
    for(int i=0;i<array.length;i++){
         arr[i]=array[i].split(""); 
     }
    
    for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[0].length;j++){
                maxX=j;maxY=i;minX=j;minY=i;counter=0;
                center(arr,ind,i,j);
                if (counter*4>=area){
                    answer[z]=new Point(maxX+minX+1,maxY+minY+1);z++;
                }
            }
        }
    return answer;
    
    }

public static void center (String[][] array,int ind,int i,int j){
    if(j<0||i<0||i>=array.length||j>=array[0].length||!array[i][j].equals(Integer.toString(ind))){
        return ;
    }
    array[i][j]="a";
    counter++;
    if (i<minY){
        minY=i;
    }else if (i>maxY){
        maxY=i;
    }else if(j<minX){
        minX=j;
    }else if (j>maxX){
        maxX=j;
    }
     center(array,ind,i+1,j);
     center(array,ind,i-1,j); 
     center(array,ind,i,j+1);
     center(array,ind,i,j-1);
    }
       
       

    
public static void main(String[] args) {
        PlayersFinder find =new PlayersFinder();    
    
        String input =scan.nextLine();
        String[] xy=input.split(", ");
        int x=Integer.parseInt(xy[1]);
        int  y=Integer.parseInt(xy[0]);
        if(x==0&&y==0){
        System.out.println("[]");
       }else{
        String[] array=new String[y];
        for(int i=0;i<y;i++){
            array[i]=scan.nextLine();
        }
        int ind=Integer.parseInt(scan.nextLine()); 
        int area=Integer.parseInt(scan.nextLine());
        print(sort(find.findPlayers(array,ind,area)));
        }  
    }  
}