import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

interface IStack {
  
  /*** Removes the element at the top of stack and returnsthat element.
  * @return top of stack element, or through exception if empty
  */
  
  public Object pop();
  
  /*** Get the element at the top of stack without removing it from stack.
  * @return top of stack element, or through exception if empty
  */
  
  public Object peek();
  
  /*** Pushes an item onto the top of this stack.
  * @param object to insert*
  */
  
  public void push(Object element);
  
  /*** Tests if this stack is empty
  * @return true if stack empty
  */
  public boolean isEmpty();
  
  public int size();
}


public class MyStack implements IStack {
        Node top = null;
        int size = 0;
     static class Node{
          Object value;
          Node prev;
          Node(Object d,Node n){
            value = d;
            prev = n;
        }
     }
 public Object pop(){
     if (isEmpty()){return "Error";}
         Object x=top.value;
         top=top.prev;
         size--;
         return x;
    
 }        
  
public Object peek(){
if (isEmpty()){return "Error";}
        return top.value;
      
}
    
public void push(Object element){
  if (isEmpty()){
    Node node=new Node(element,null);
         top=node;size++;
  }else{
         Node node=new Node(element,top);
         top=node;size++;
     }    
 }
       
public boolean isEmpty(){
    if(top==null){return true;}
    return false;
}    
   
public int size(){
    return size;
}
    
public static void p_stack(MyStack stack){
        if(stack.size() > 1){
            System.out.print("[");
            while(stack.size>1){
                System.out.print(stack.pop() + ", ");}
            System.out.print(stack.pop() + "");
            System.out.print("]");
        }else if(stack.size == 1){
            System.out.print("[" + stack.pop() + "]");
        }else{
            System.out.print("["+"]");
        }
}    
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        MyStack stack=new MyStack();
        String arr = scan.nextLine().replaceAll("\\[|\\]","");
        String[] array = arr.split(", ");
         if(!array[0].equals("")){
            for(int i=array.length-1;i>=0;i--){
               stack.push(array[i]);   
        }
         }
       String op=scan.nextLine();
        switch(op){
                
          case "pop":if(stack.size!=0){stack.pop();p_stack(stack);}else{System.out.println(stack.pop());};break;
          
          case "push":stack.push(scan.next());p_stack(stack);break;      
                
          case "peek":System.out.println(stack.peek());break;
                
          case "size":System.out.println(stack.size());break; 
                
          case "isEmpty":if(stack.isEmpty()){System.out.println("True");}else{System.out.println("False");}break;      
                
          default: System.out.println("Error");
        }
        
       
        
        
        
        
        
        
        
        
        
    }
}