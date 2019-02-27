/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package table;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Administrator
 */
public class Table {

    private static class Node {
    String name=null;//store the name
    String address=null;//store the address
    Node left;//store the left child
    Node right;//store the right child
    Node Parent;//store the parent
    Node(){
            this.name=name;
            this.address=address;
            this.left=null;
            this.right=null;
            this.Parent=null;
    }
    }
    private Node root=null;//initializing
    private int findheight(Node p){
        int deep = 0;
        if ((p!=null)&&((p.left!=null)||(p.right!=null))) {
            deep=Math.max(findheight(p.left),findheight(p.right))+1; 
        }
        return deep; 
    }    
    
    public boolean insert(String key, String value)
    {
        if (root==null){//insert the first element in this tree
            root = new Node();
            root.name=key;
            root.address=value;
        }else{
        Node current = root;// use current as a pointer to find the suitable position to insert element
        while (current != null){
            if (current.name.compareTo(key)==0){//in this case, the name already exists, so do nothing 
             return false;   
            }
            if (current.name.compareTo(key)>0){
                //go to left
                if (current.left==null) {//find a position, then put it in 
                    current.left=new Node();
                    current.left.Parent=current;
                    current.left.name=key;
                    current.left.address=value;
                    return true;
                }else{// try deeper
                 current=current.left;}
            }
            else
            { 
                if (current.right==null) {//go to the right
                    current.right=new Node();
                    current.right.Parent=current;
                    current.right.name=key;
                    current.right.address=value;     
                    return true;
                }else{//try deeper
                current=current.right;}
            }
                }}
    
    return true;
    }
    
    public int size;//use to count the total number of contacts
    private void midorderdisp(Node p){
        //use midorder traverse to sort the names;
    if (p==null) { return; }
    midorderdisp(p.left);//go to left
    System.out.println(p.name);
    System.out.println(p.address);
    System.out.println("        ---Height: "+findheight(p));
    //output
    System.out.println();
    midorderdisp(p.right);//go to right
    size++;//count the size(total number)
    }
    public int displayAll() {
        size=0;
        midorderdisp(root);// the names are sorting by midorder
        return(size);
}

    public Node find;// the varible find is used to store the searching result.
    private void search(Node p,String k){
        if (p==null) {
            return;
        }
       if (p.name.equals(k)) {
           find = p;//store the result in find
       }
        search(p.left,k);
        search(p.right,k);
   
    } 
    
   
    
    public String LookUp(String key){
        find=null;//initialize find
        search(root,key);// get the position of desired Node
        if (find==null) {return null;}// not found
        return find.address;// found it
    }
        
    public boolean update(String key,String value){
        find = null;//initialize
        search(root,key);//get position
        if (find==null) {return false;}//not exist
        find.address=value;//update the new address
        return true;
    }
 
    public boolean delete(String key){
        Node P;//P is used to store the parent Node 
        if (LookUp(key)!=null) {
            if ((find.left==null)&&(find.right==null)) {//in this case, the node has no child
                P=find.Parent;
                if (P==null) {//in this case, this tree has only one element:the root
                    root=null;
                }else{
                    if (P.left==find){//find out which side the Node to be deleted is
                        P.left=null;//the case of left side 
                    }else{
                       P.right = null; //the case of right side
                    }}
                return true;
            }else {
                if (find.left==null) {
                    boolean exist =false;
                    Node RightSubMin=find.right;//use RightSubMin to locate the Node having smallest key(name) in the right sub tree
                    while (RightSubMin.left!=null) {
                        RightSubMin=RightSubMin.left;
                        exist = true;
                    }
                    find.name=RightSubMin.name;//replace the desired Node by the Smallest one
                    find.address=RightSubMin.address;
                    if (exist) {
                    P=RightSubMin.Parent;
                    P.left=RightSubMin.right;//move the child of the smallest node to the position where the smallest one used to take
                    if (RightSubMin.right!=null) {RightSubMin.right.Parent=P;}} else {
                        find.right=null;
                    }
                    return true;
                   }else{
                    boolean exist =false;
                    Node leftSubMax=find.left;// use leftSubMax to locate the Node having greatest key(name)in the left sub tree
                    while (leftSubMax.right!=null) {
                        leftSubMax=leftSubMax.right;
                        exist=true; 
                    }
                    find.name=leftSubMax.name;//replace the desired Node by the greatest one
                    find.address=leftSubMax.address;
                    if (exist) {
                    P=leftSubMax.Parent;
                    P.right=leftSubMax.left;// move the child of the greatest one to the position where the greatest one used to take
                    if (leftSubMax.left!=null) {leftSubMax.left.Parent=P;}} 
                    else{
                    find.left=null;
                    }
                    return true;
                }
                
                    }}
        else{
            return false;//not exist
        }
        }
    private void preorder(Node p,BufferedWriter out) throws IOException{
        if (p==null) {return;}// use preorder to output the data to file
        out.write(p.name+"\r\n");
        out.write(p.address+"\r\n"+"\r\n");
        preorder(p.left,out);
        preorder(p.right,out);
    }
 public void save() throws IOException{// the data will be saved to a file
     File writename = new File("Save.txt");//create the file 
        try { 
            writename.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(Table.class.getName()).log(Level.SEVERE, null, ex);
        }        
        try (BufferedWriter out = new BufferedWriter(new FileWriter(writename))) {
            preorder(root,out);//put the Nodes to recusion, and write the data out
            out.flush();//colse the file
        }
 }
    /**
     *
     * @param args
     */
    public static void main(String[] args) throws IOException {
     Table mytable = new Table(); //mytable
     File inputfile = new File("contacts.txt");//read the data from the file    
     		if (inputfile.isFile() && inputfile.exists()) {   
			try {
                            Scanner expression = new Scanner(inputfile);
				while (expression.hasNextLine()) {
                                    // process one expression at one time 
                                        String name = expression.nextLine();//get the name
                                        String address = expression.nextLine();//get the address
                                        if (mytable.insert(name,address)){}//there are some repeating data, but no need to stop
                                        }
			} catch (FileNotFoundException e) {
			}    
		} 
        Scanner sc=new Scanner(System.in);
        //input data 
        while (1>0) { 
            //it is an endless loop. it will not stop unless receives the command "Quit and Save"
        System.out.println();
        System.out.println("Add a contact (a)");
        System.out.println("Look up a contact (l)");
        System.out.println("Update address (u)");
        System.out.println("Delete a contact (d)");
        System.out.println("Display all contact (ac)");
        System.out.println("Save and Quit (q)\n");
        System.out.print("-->");
        String command = sc.nextLine();//get the input from user
        
        System.out.println();//line feed
        if (command.equals("q")) {
            mytable.save();//write all the information to a file 
        break;
        }else{
            if (command.equals("a")){
                System.out.print("Name:");
                String Key=sc.nextLine();//get name
                System.out.print("Address:");
                String value=sc.nextLine();//get address
                if (!mytable.insert(Key, value)){
                    System.out.println("This contact already exists!");//insert failed
                }
            }else{
                if (command.equals("ac")) {//the contacts will be displayed in midorder traverse
                    System.out.println("The number of contacts: "+mytable.displayAll());//as well as the counted heights and the total size
                }else{
                    if (command.equals("l")){//look up 
                        System.out.print("Name:");
                        String name=sc.nextLine();//get the name
                        if (mytable.LookUp(name)==null) {
                            System.out.println("contact does not exist!");//lookingup failed
                        }else {
                        System.out.println(mytable.LookUp(name));}//give the result
                    }else{
                        if (command.equals("u")){//updating
                            System.out.print("Name:");
                            String name=sc.nextLine();//get the name
                            if (mytable.LookUp(name)==null) {
                                System.out.println("contact does not exist!");//not found
                            }else {
                            System.out.print("The old address is "+mytable.LookUp(name)+". Input the new one:");
                            //display the old one and get the new one
                            String address=sc.nextLine();
                            mytable.update(name,address); //relapcing
                            } }else{
                            if (command.equals("d")){//delete
                                System.out.print("Name:");
                                String name=sc.nextLine();//get the name
                                if (!mytable.delete(name)) {
                                 System.out.println("contact does not exist!");//not exist
                            }
                                    }
                    }
                }
            }
        }
        }
    }
    
}}
