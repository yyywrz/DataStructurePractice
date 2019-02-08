
package queuesimulator;
import java.util.Scanner;
/**
 *
 * @author Roger Wei
 */
public class QueueSimulator {
    private final Scanner sc = new Scanner(System.in);
    private void display(String[] a){
        if (a==null) return;
        System.out.println("There are "+a.length+" elements in queue.");
        for (String a1 : a) {
            System.out.print(a1 + "  ");
        }
        System.out.println("\n");
    }
    
    private void command(String c){
        String[] a=null;
        int size=0;
        while (!c.equals("quit")) {
            System.out.println();
            display(a);
            System.out.println("Add an element - enqueue()");
            System.out.println("Remove the top element - dequeue");
            System.out.println("Quit - quit");
            System.out.print("input command:");
            c = sc.nextLine();
            if (c.equals("quit")){
                break;
            }else{
                if (c.equals("dequeue")) {
                    if (size==0){System.out.println("Illeage input!");}else{
                    System.out.println("("+a[size-1]+")is removed");
                    size--;
                    String[] b = new String[size];
                    for (int i=0;i<size;i++){
                        b[i]=a[i];
                    }
                    a =new String[size];
                    a=b;
                } }else{
                    if (c.length()<9){
                        System.out.println("Illeage input!");
                    } else {
                    if (c.substring(0,8).equals("enqueue(") && (c.substring(c.length()-1,c.length()).equals(")"))){
                        String[] b = new String[size];
                        b=a;
                        size++;
                        a = new String[size];
                        a[0]=c.substring(8, c.length()-1);
                        for (int i=1;i<size;i++){
                            a[i]=b[i-1];
                        }
                    }else{
                        System.out.println("Illeage input!");
                    }}
                }
            }
                 
        }
    }
    
    public static void main(String[] args) {
        QueueSimulator Q = new QueueSimulator();
        Q.command("");
    }
    
}
