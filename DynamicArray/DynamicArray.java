package dynamicarray;
import java.util.Scanner;

/**
 * @author Roger_Wei
 */
public class DynamicArray {
    
    public Scanner sc =new Scanner(System.in);
    
    public String[] Darray = new String[1];
    
    public int capcity=1;
    
    public int size = 0;

    public void input(String s){
        size++;
        if (capcity<size) {
            capcity = 2*capcity;
            String[] temp = Darray;
            Darray = new String[capcity];
            System.arraycopy(temp, 0, Darray, 0, size-1);
        }
        Darray[size-1]=s;
    }
    public void display(){
        System.out.println();
        for (int i=0;i<size;i++){
             System.out.println((i+1)+". "+Darray[i]);
       }
    }
    
    public void remove(){
        display();
        System.out.print("Which one will be removed? input a number:");
        int k = sc.nextInt();
        if ((k>size)||(k<0)) {return;}
        size--;
        for(int i=k-1;i<size;i++){
            Darray[i]=Darray[i+1];
        }
        Darray[size]=null;
        if (size*3<=capcity) {
            capcity =capcity/2;
            String[] temp = Darray;
            Darray = new String[capcity];
            System.arraycopy(temp, 0, Darray, 0, size);
        }
    }
    
    private void command(String c){
           loop:while (!c.equals("q")) {
            System.out.println();
            System.out.println("Current contacts:"+size);
            System.out.println("Input new name (i)");
            System.out.println("Remove a name (r)");
            System.out.println("Display all names(d)");
            System.out.println("Qiut (q)");
            System.out.print("input command:");
            switch (sc.nextLine()){
                    default: 
                        System.out.println("please input a proper command!");
                        break;
                        
                    case "q": 
                        break loop;
                        
                    case "i":
                        System.out.print("input name:");
                        input(sc.nextLine());
                        break;
                        
                    case "d":
                        display();
                        break;
                        
                    case "r":
                        remove();
                        
            }
        }
    }
    
    public static void main(String[] args) {
        
        DynamicArray myList = new DynamicArray();
        
        myList.command("");
        
    }
    
}
