package bstree;

/**
 *
 * @author Roger Wei
 */
import java.util.Scanner;

public class BSTree {
    
    private final Scanner sc = new Scanner(System.in);
    
    private class Node{
        int value;
        Node left;
        Node right;
        Node(){
            this.left=null;
            this.right=null;
            this.value = value;
        }
    }
    
    private Node head = null;
    
    private void insert(int k){
        Node current =new Node();
        current.value = k;
        if (head==null){
            head = current;
        } else{ 
            Node p = head;
            Node q =p;
            while (q!=null){
                p=q;
                if (k>p.value) {
                    q=q.left;
                }else{
                    q=q.right;}
            }
            
            if (k>p.value){p.left=current;} else {p.right=current;}
            
        }
    }
    
    private void display(Node p){
        if (p.left!=null){ display(p.left);}
            System.out.print(p.value+" ");
        if (p.right!=null){ display(p.right);}
    }
    
    public static void main(String[] args) {
        BSTree mytree = new BSTree();
        System.out.print("n=");
        int n = mytree.sc.nextInt();
        System.out.println("input sequence");
        for (int i=0;i<n;i++){
            mytree.insert(mytree.sc.nextInt());
                    }
        mytree.display(mytree.head);
    }
}
