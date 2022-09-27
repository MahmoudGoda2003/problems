import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

interface IExpressionEvaluator {
  
/**
* Takes a symbolic/numeric infix expression as input and converts it to
* postfix notation. There is no assumption on spaces between terms or the
* length of the term (e.g., two digits symbolic or numeric term)
*
* @param expression infix expression
* @return postfix expression
*/
  
public String infixToPostfix(String expression);
  
  
/**
* Evaluate a postfix numeric expression, with a single space separator
* @param expression postfix expression
* @return the expression evaluated value
*/
  
public int evaluate(String expression);

}


public class Evaluator implements IExpressionEvaluator {

public static int a,b,c;
/**
*line1: sacn the expression
*<br>line2: scan a value</br>
*<br>line3: scan b</br>
*line4: scan c value
*/
public static String scan(){
        Scanner scan=new Scanner(System.in);
        try{
        String string = scan.nextLine();
        String arr1 = scan.nextLine();
        String[] array1 = arr1.split("=");
        String arr2 = scan.nextLine();
        String[] array2 = arr2.split("=");
        String arr3 = scan.nextLine();
        String[] array3 = arr3.split("=");
        a=Integer.parseInt(array1[1]);
        b=Integer.parseInt(array2[1]);
        c=Integer.parseInt(array3[1]);
        return string;}
        catch(Exception e) {
       }
        return"Error";
}

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
  /***
  * size of the stack
  * @return number of elements in the stack
  */
  public int size();
}
/**
*
*/

class MyStack implements IStack {
        Node top = null;
        int size = 0;
      class Node{
          Object value;
          Node prev;
          Node(Object d,Node n){
            value = d;
            prev = n;
        }
     }
/**
*
*/

 public Object pop(){
     if (isEmpty()){return "Error";}
         Object x=top.value;
         top=top.prev;
         size--;
         return x;
    
 }        
/**
*
*/
  
public Object peek(){
if (isEmpty()){return "Error";}
        return top.value;
      
}
/**
*
*/
    
public void push(Object element){
  if (isEmpty()){
    Node node=new Node(element,null);
         top=node;size++;
  }else{
         Node node=new Node(element,top);
         top=node;size++;
     }    
 }
/**
*
*/

public boolean isEmpty(){
    if(top==null){return true;}
    return false;
}    
/**
*
*/
   
public int size(){
    return size;
}
}    
/**
*@param char to know which one need to be first
*@return char wigth
*/
public static int first(char x){
        switch (x)
        {
        case '+':
        case '-':
            return 1;
      
        case '*':
        case '/':
            return 2;
      
        case '^':
            return 3;
        }
        return 0;
} 
/**
*@param string to check if it is valid change it or, return Error if is invalid 
*@return valid string or Error
*/
public static String check(String expression){
  
    for(int i=0;i<expression.length();i++){
       
        if((i==0||i==expression.length()-1)&&(expression.charAt(i)=='*'||expression.charAt(i)=='^'||expression.charAt(i)=='/')){
            return "Error";
        }
        else if(expression.charAt(i)=='*'||expression.charAt(i)=='^'||expression.charAt(i)=='/'){
            if((!Character.isLetterOrDigit(expression.charAt(i-1))&&expression.charAt(i-1)!=')')){
                return "Error";
            }
        }else if(i!=expression.length()-1&&(expression.charAt(i)=='-'||expression.charAt(i)=='+')){
            if(i==0&&expression.charAt(i)==expression.charAt(i+1)){
                expression=expression.substring(2);
}else if(expression.charAt(i)==expression.charAt(i+1)&&(Character.isLetterOrDigit(expression.charAt(i-1))||expression.charAt(i-1)==')'&&i>0)){
                expression=expression.substring(0,i)+'+'+expression.substring(i+2);
            }else  if(expression.charAt(i)==expression.charAt(i+1)&&!Character.isLetterOrDigit(expression.charAt(i-1))){
                expression=expression.substring(0,i)+expression.substring(i+2);
            }else if(expression.charAt(i)!=expression.charAt(i+1)){
               if(i>0&&(!Character.isLetterOrDigit(expression.charAt(i-1))&&expression.charAt(i-1)!=')')) {
                   return "Error";
               }   
            }else{
                return "Error";
            }
                      
    }else if(i<expression.length()-1&&Character.isLetterOrDigit(expression.charAt(i))&&Character.isLetterOrDigit(expression.charAt(i+1))) {
           return "Error"; 
        }    
    }
    if(expression.charAt(expression.length()-1)=='-'||expression.charAt(expression.length()-1)=='+'){return "Error";}
    
    return expression;
}
                                                   
 /**
 *
 */
public String infixToPostfix(String expression){
    MyStack stack=new MyStack();
    String result="";
    expression=check(expression);
    if(expression=="Error"){return "Error";}
    
    for(int i=0;i<expression.length();i++){
       char x=expression.charAt(i);
        
        if(Character.isLetterOrDigit(x)){
            result+=x;
        }else if(x=='('){
            stack.push(x);
        }else if(x==')'){
           while(!stack.isEmpty()&&(char)stack.peek()!='('){
               result+=stack.pop();
           } 
            if(stack.isEmpty()){return "Error";}
            stack.pop();    
        }else{
            while(!stack.isEmpty()&&first(x)<=first((char)stack.peek())){
                result+=stack.pop();
            }
            stack.push(x);
        } 
    }
    while(!stack.isEmpty()){
        if((char)stack.peek()=='('){
            return "Error";
        }
               result+=stack.pop();
        }
    if(result.isEmpty()){return "Error";}
    return result; 
}    
 /**
 *
 */
public int evaluate(String expression){
    MyStack stack=new MyStack();
    
    for(int i=0;i<expression.length();i++){
       if(Character.isLetter(expression.charAt(i))){
           switch(expression.charAt(i)){
               case 'a': stack.push(a);break;
               case 'b': stack.push(b);break;
               case 'c': stack.push(c);break; 
               default: return -55454555;  
           }
       }else if(Character.isDigit(expression.charAt(i))){
        stack.push(Character.getNumericValue (expression.charAt(i)));
       }else{
         switch(expression.charAt(i)){
           case '+':if(stack.size()>=2){int x=(int)stack.pop();stack.push((int)stack.pop()+x);}break;
           case '-':if(stack.size()==1){stack.push(-(int)stack.pop());}else{int x=(int)stack.pop();stack.push((int)stack.pop()-x);}break;
           case '/':if(stack.size()>=2){int x=(int)stack.pop();stack.push((int)stack.pop()/x);}else{return -55454555;}break; 
           case '*':if(stack.size()>=2){int x=(int)stack.pop();stack.push((int)stack.pop()*x);}else{return -55454555;}break;
           case '^':if(stack.size()>=2){int x=(int)stack.pop();stack.push((int)Math.pow((int)stack.pop(),x));}else{return -55454555;}break;
               default: return -55454555;   
           }  
       }
}
    if(stack.size()!=1){return -55454555;}
    return (int)stack.peek();
} 
   /*
   *print the result of the code or Error if any thig was wrong 
   */
public static void main(String[] args) {
    Evaluator eval =new Evaluator();
    String temp=eval.infixToPostfix(scan());
    if(temp=="Error"){
        System.out.println(temp);
    }else{
        int x=eval.evaluate(temp);
        if(x==-55454555){System.out.println("Error");}
        else{
             System.out.println(temp);
             System.out.println(x);
        }
    }
    
    
    }
}