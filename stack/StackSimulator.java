
package stacksimulator;
import java.util.Scanner;
/**
 * @author Roger Wei
 */
public class StackSimulator {
    
    private static class Node {
        String name;
        Node next;
        Node(){
            this.name=null;
            this.next=null;
        }
    }
    private Node head =null;
    
    private final Scanner sc = new Scanner(System.in);

    private int width=2;
    
    private void display(){
        if (head==null) {
            System.out.println("Stack is Empty!");
        }else{
            Node current =head;
            for (int i=0;i<width;i++){
                System.out.print("-");
            }
            System.out.println();
            while (current != null){
                System.out.print("|"+current.name);
                for (int i=current.name.length();i<width-2;i++){
                    System.out.print(" ");
                }
                System.out.println("|");
                for (int i=0;i<width;i++){
                    System.out.print("-");
                }
                System.out.println("\n");
                current=current.next;
          }
           
    }
    }
    
    private void push(String s){
        Node top = new Node();
        if (s.length()+2>width) {
            width=s.length()+2;
        }
        top.name=s;
        if (head==null) {
            head = top;
        }else{
            top.next = head;
            head = top;
        }
        
    }

    private String pop(){
        if (head==null) { return "Stack is Empty!";}
        String name = head.name;
        head=head.next;
        return ("("+name+") is removed");
    }
    
    private void command(String c){
        while (!c.equals("quit")) {
            System.out.println();
            display();
            System.out.println("Add an element - push()");
            System.out.println("Remove the top element - pop");
            System.out.println("Qiut - quit");
            System.out.print("input command:");
            c = sc.nextLine();
            if (c.equals("quit")){
                break;
            }else{
                if (c.equals("pop")) {
                    System.out.println(pop());
                }else{
                    if (c.length()<6){
                        System.out.println("Illeage input!");
                    } else {
                    if (c.substring(0,5).equals("push(") && (c.substring(c.length()-1,c.length()).equals(")"))){
                        push(c.substring(5, c.length()-1));
                    }else{
                        System.out.println("Illeage input!");
                    }}
                }
            }
                 
        }
    }
    
    public static void main(String[] args) {
    StackSimulator Stack = new StackSimulator();
    Stack.command("");
    }
    
}
