import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.EmptyStackException;

interface ILinkedList {

    /**
     * Inserts a specified element at the specified position in the list.
     * @param index The index you want to insert the node in
     * @param element
     */
    void add(int index, Object element);

    /**
     * Inserts the specified element at the end of the list.
     * @param element
     */

    void add(Object element);
    /**
     * @param index
     * @return the element at the specified position in this list.
     */
    Object get(int index);

    /**
     * Replaces the element at the specified position in this list with the
     * specified element.
     * @param index
     * @param element
     */
    void set(int index, Object element);
    /**
     * Removes all of the elements from this list.
     */
    void clear();
    /**
     * @return true if this list contains no elements.
     */
    boolean isEmpty();
    /**
     * Removes the element at the specified position in this list.
     * @param index
     */
    void remove(int index);
    /**
     * @return the number of elements in this list.
     */
    int size();
    /**
     * @param fromIndex
     * @param toIndex
     * @return a view of the portion of this list between the specified fromIndex and toIndex, inclusively.
     */
    ILinkedList sublist(int fromIndex, int toIndex);
    /**
     * @param o
     * @return true if this list contains an element with the same value as the specified element.
     */
    boolean contains(Object o);


}



interface IMazeSolver {
    /**
     * Read the maze file, and solve it using Breadth First Search
     * @param maze maze file
     * @return the coordinates of the found path from point ’S’
     *
    to point ’E’ inclusive, or null if no path is found.
     */
    public int[][] solveBFS(java.io.File maze);
    /**
     * Read the maze file, and solve it using Depth First Search
     * @param maze maze file
     * @return the coordinates of the found path from point ’S’
     *
    to point ’E’ inclusive, or null if no path is found.
     */
    public int[][] solveDFS(java.io.File maze);

}

interface IQueue {
    /*** Inserts an item at the queue front.*/
    public void enqueue(Object item);
    /*** Removes the object at the queue rear and returns it.*/
    public Object dequeue();
    /*** Tests if this queue is empty.*/
    public boolean isEmpty();
    /*** Returns the number of elements in the queue*/
    public int size();
}

interface IStack {

    /**
     * Removes the element at the top of stack and returns that element.
     * @return top of stack element, or through exception if empty
     */
    public Object pop();

    /**
     * Get the element at the top of stack without removing it from stack
     * @return top of stack element, or through exception if empty
     */
    public Object peek();

    /**
     * Pushes an item onto the top of this stack.
     * @param element object to insert
     */
    public void push(Object element);

    /**
     * Tests if this stack is empty
     * @return true if stack empty
     */
    public boolean isEmpty();

    /**
     * Gets the size of stack
     * @return current size
     */
    public int size();
}

class SLNode {
    /**
     * Element of the current node.
     */
    private Object element;
    /**
     * Reference to the next node.
     */
    private SLNode next;

    /**
     * Default constructor
     * @param element
     * @param next
     */
    public SLNode(Object element, SLNode next) {
        this.element = element;
        this.next = next;
    }

    public Object getElement() {
        return element;
    }

    public void setElement(Object element) {
        this.element = element;
    }

    public SLNode getNext() {
        return next;
    }

    public void setNext(SLNode next) {
        this.next = next;
    }
}

class SingleLinkedList implements ILinkedList {
    private int length;
    private SLNode head;

    public SLNode getHead() {
        return head;
    }
    public SingleLinkedList() {
        head = null;
        length = 0;
    }

    public int getLength() {return length;}

    @Override
    public void add(int index, Object element) {
        // create a new node
        SLNode node = new SLNode(element, null);
        // Reference to head node, made to iterate the list
        SLNode temp = head;
        int i=0; // iterator
        if(index < 0 || index > size()) {
            // if user inputs a negative index
            System.out.println("Error");
        }else if(isEmpty()) {
            head = node;
            length++;
        }else if(index == 0) {
            // add from the start of the list
            node.setNext(head);
            head = node;
            length++;
        }else{
             // iterate until the index - 1
            while(temp.getNext() != null && i < index-1) {
                temp = temp.getNext();
                i++;
            }
            node.setNext(temp.getNext());
            temp.setNext(node);
            length++;
        }
    }

    @Override
    public void add(Object element) {
        add(length, element);
    }

    @Override
    public Object get(int index) {
        SLNode temp = head;
        int i = 0;
        if(isEmpty() || index >= length || index < 0){
            System.out.println("Error");
            return null;
        }else{
            while(temp.getNext()!=null && i < index){
                temp = temp.getNext();
                i++;
            }
            return temp.getElement();
        }
    }

    @Override
    public void set(int index, Object element) {
        SLNode temp = head;
        int i = 0;
        if(isEmpty() || index<0 || index >= length){
            System.out.println("Error");
        }else{
            while(temp.getNext() != null && i < index){
                temp = temp.getNext();
                i++;
            }
            temp.setElement(element);
        }
    }

    @Override
    public void clear() {
        head = null;
        length = 0;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public void remove(int index) {
        SLNode current = head;
        SLNode delete_node;
        if(head == null || index >= length || index < 0) {
            System.out.println("Error");
        }else if(index == 0){ // delete from front
            head = head.getNext();
            current.setNext(null);
            length--;
        }else if(index == length-1){ // delete from tail
            for (int i = 0; i < index-1; i++){
                current = current.getNext();
            }
            current.setNext(null);
            length--;
        }else{ // delete from middle
            // iterate through the list until index - 1 node
            for (int i = 0; i < index-1; i++){
                current = current.getNext();
            }
            delete_node = current.getNext();
            current.setNext(delete_node.getNext()); // change the k-1 node to point to the node after the deleted one
            delete_node.setNext(null);
            length--;
        }
    }

    @Override
    public int size() {
        //TODO delete this function. redundant.
        if(isEmpty())
            return 0;
        return length;
    }

    @Override
    public ILinkedList sublist(int fromIndex, int toIndex) {
        SingleLinkedList myList = new SingleLinkedList();
        SLNode temp = head;
        // iterate until first node
        if(head == null || fromIndex > toIndex || fromIndex < 0 || toIndex < 0 || toIndex >= length || fromIndex >= length){
            System.out.println("Error");
            return null;
        }else {
            for (int i = 0; i < fromIndex; i++) {
                temp = temp.getNext();
            }
            //iterate until last index
            for (int i = fromIndex; i <= toIndex; i++) {
                myList.add(temp.getElement());
                temp = temp.getNext();
            }
            return myList;
        }
    }

    @Override
    public boolean contains(Object o) {
        SLNode current = head;
        for (int i =0; i < size(); i++) {
            if(o == current.getElement()){
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    public void printList(SingleLinkedList myList){
        Object temp;
        if(myList == null){
            System.out.println("Error");
        }else{
            for (int i = 0; i < myList.getLength(); i++) {
                temp = myList.get(i);
                System.out.println(temp);
            }
        }
    }
}



class Stack implements IStack{
    private SingleLinkedList myStack;

    public Stack() {
        myStack = new SingleLinkedList();
    }

    @Override
    public Object pop() {
        if(myStack.isEmpty()){
            throw new EmptyStackException();
        }else{
            Object removed = myStack.get(0);
            myStack.remove(0);
            return removed;
        }
    }

    @Override
    public Object peek() {
        if(myStack.isEmpty()) {
            throw new EmptyStackException();

        }
        else
         return myStack.get(0);
    }

    @Override
    public void push(Object element) throws OutOfMemoryError { // TODO OutOfMemoryException handle it.
        myStack.add(0,element);
    }

    @Override
    public boolean isEmpty() {
        return myStack.isEmpty();
    }

    @Override
    public int size() {
        return myStack.size();
    }

    /**
     * prints stack content
     */
    public void traceStack(){
        myStack.printList(myStack);
    }

}

class LinkedListQueue implements IQueue{
    SingleLinkedList queue;

    public LinkedListQueue(){
        queue = new SingleLinkedList();
    }
    @Override
    public void enqueue(Object item) {
        queue.add(item);
    }

    @Override
    public Object dequeue() {
        if(queue.isEmpty()){
            throw new IllegalStateException();
        }
        Object temp = queue.get(0);
        queue.remove(0);
        return temp;
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public int size() {
        return queue.size();
    }

    public static void PrintQueueBrackets(LinkedListQueue q){
        int[] arr = new int[q.size()];
        for (int i = arr.length - 1; i >= 0; i--){
            arr[i] = (Integer)q.dequeue();
        }
        Object temp;
        if(arr.length == 0){
            System.out.print("[]");
        }else{
            System.out.print("[");
            for (int i = 0; i < arr.length; i++) {
                if(arr.length - i == 1){
                    System.out.print(arr[i] + "]");
                    break;
                }
                System.out.print(arr[i] + ", ");
            }
        }

    }
}



public class MazeSolver implements IMazeSolver{

    private static boolean isValid(int i, int j, String[] matrix){
        if(i < 0 || j < 0 || i >= matrix.length|| j >= matrix[0].length() || matrix[i].charAt(j) == '#')
            return false;
        else
            return true;
    }

    private static Point[] printPath(Point[][] backtrack, Point e, Point s){
        int i = 0;

        Point[] sol = new Point[backtrack.length*backtrack[0].length];
        sol[i] = e;

        while(backtrack[sol[i].x][sol[i].y] != null){
            int tx = sol[i].x;
            int ty = sol[i].y;

            sol[++i] = backtrack[tx][ty];
        }

        for (int j = i; j > 0; j--){
            System.out.print("{" + sol[j].x + "," + " " + sol[j].y + "}, ");
        }
        System.out.print("{" + sol[0].x + "," + " " + sol[0].y + "}");


        return sol;
    }

    @Override
    public int[][] solveBFS(File maze) {
        // open file
        Scanner sc = null;
        try {
            sc = new Scanner(maze);
        } catch (FileNotFoundException e) {
            System.out.println("Error");
        }
        // read file elements
        int x = sc.nextInt();
        int y = sc.nextInt();
        // read matrix
        String[] matrix = new String[x];
        int counter = 0;
        sc.nextLine();
        while(sc.hasNextLine())
            matrix[counter++] = sc.nextLine();

        // search for S
        Point t = new Point();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length(); j++) {
                if(matrix[i].charAt(j) == 'S'){
                    t = new Point(i,j);
                    break;
                }
            }
        }
        Point s = t;
        // visited array
        boolean[][] visited = new boolean[matrix.length][matrix[0].length()];

        // backtrack array
        Point[][] backtrack = new Point[matrix.length][matrix[0].length()];

        // BFS
        // initalize queue
        System.out.print("BFS: ");
        LinkedListQueue q = new LinkedListQueue();

        q.enqueue(t);
        visited[t.x][t.y] = true;

        while(q.size() != 0){

            // visit
            t = (Point) q.dequeue();

            // process
            if(matrix[t.x].charAt(t.y) == 'E'){
                //System.out.print("{" + t.x + "," + " " + t.y + "}");
                int[][] sol = new int[1][2];
                sol[0][0] = t.x;
                sol[0][1] = t.y;
                //printPath
                printPath(backtrack, t, s);
                return  sol;
            }

            //System.out.print("{" + t.x + "," + " " + t.y + "}, ");

            // get neighbours
            int[] neighborX = {1,-1, 0 , 0};
            int[] neighborY = {0 , 0, 1, -1};
            for( int i = 0; i < 4; i++){
                int neighX = t.x + neighborX[i];
                int neighY = t.y + neighborY[i];
                if(isValid(neighX,neighY, matrix) && !visited[neighX][neighY]){
                    q.enqueue(new Point(neighX, neighY));
                    // mark as visited
                    visited[neighX][neighY] = true;
                    backtrack[neighX][neighY] = t;
                }
            }

        }


        return null;
    }

    @Override
    public int[][] solveDFS(File maze) {
        // open file
        Scanner sc = null;
        try {
            sc = new Scanner(maze);
        } catch (FileNotFoundException e) {
            System.out.println("Error");
        }
        // read file elements
        int x = sc.nextInt();
        int y = sc.nextInt();
        // read matrix
        String[] matrix = new String[x];
        int counter = 0;
        sc.nextLine();
        while(sc.hasNextLine())
            matrix[counter++] = sc.nextLine();

        // search for S
        Point t = new Point();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length(); j++) {
                if(matrix[i].charAt(j) == 'S')
                    t = new Point(i,j);
            }
        }
        Point s = t;
        // visited array
        boolean[][] visited = new boolean[matrix.length][matrix[0].length()];
        Point[][] backtrack = new Point[matrix.length][matrix[0].length()];
        // BFS
        // initalize queue
        System.out.print("DFS: ");
        Stack q = new Stack();

        q.push(t);
        visited[t.x][t.y] = true;

        while(q.size() != 0){
            // visit
            t = (Point) q.pop();

            // process
            if(matrix[t.x].charAt(t.y) == 'E'){
                //System.out.print("{" + t.x + "," + " " + t.y + "}");
                int[][] sol = new int[1][2];
                sol[0][0] = t.x;
                sol[0][1] = t.y;
                //printPath
                printPath(backtrack, t, s);
                return  sol;
            }

            //System.out.print("{" + t.x + "," + " " + t.y + "}, ");

            // get neighbours
            int[] neighborX = {1,-1, 0 , 0};
            int[] neighborY = {0 , 0, 1, -1};
            for( int i = 0; i < 4; i++){
                int neighX = t.x + neighborX[i];
                int neighY = t.y + neighborY[i];
                if(isValid(neighX,neighY, matrix) && !visited[neighX][neighY]){
                    q.push(new Point(neighX, neighY));
                    // mark as visited
                    visited[neighX][neighY] = true;
                    backtrack[neighX][neighY] = t;
                }
            }

        }

        return null;
    }

    public static void main(String[] args) throws FileNotFoundException {
        File maze = new File("C:\\Users\\pc\\Downloads\\Telegram Desktop\\MazeSolver\\Test.txt");
        MazeSolver myObj = new MazeSolver();
        Scanner scan = new Scanner(System.in);
        
        String y;
	    System.out.println("Maze Solver: Enter 0, 1, 2 or 3");
	    System.out.println("0. Exit");
	    System.out.println("1. BFS");
	    System.out.println("2. DFS");
	    System.out.println("3. BFS & DFS");
	    System.out.println();
	    
	    while (true){
	        y = scan.nextLine();
	        if (y.equals("0")){
	            System.out.println("thank you");
	        	return;
	        } else if (y.equals("1")){
	        	int[][] x = myObj.solveBFS(maze);
	        	if(x== null)
	                System.out.println("ERROR: NO EXIT FOUND");
	            System.out.println();
	            break;
	        } else if (y.equals("2")){
	        	int[][] z = myObj.solveDFS(maze);
	        	System.out.println();
	        	if (z == null)
	                System.out.println("ERROR: NO EXIT FOUND");
	            break;
	        }else if(y.equals("3")){
	        	int[][] x = myObj.solveBFS(maze);
	        	System.out.println();
	        	int[][] z = myObj.solveDFS(maze);
	        	System.out.println();
	        	if (x==null || z == null)
	                System.out.println("ERROR: NO EXIT FOUND");
	           break;
	        }else {
	            System.out.println("Please, Enter a valid number");
	        }    
	    }
        
        
        
        
        
        
        



    }
}