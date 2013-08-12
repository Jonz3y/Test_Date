
import java.util.*;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ppatel
 */
public class Dictionary {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        // TODO code application logic here
        
       
        Hashtable d = new Hashtable(); 

 

    d.put("1", "abhishek");

    d.put("2","rohtash" );

    d.put("3","abhishek" );

    d.put("4","sanjoli" );

    d.put("10","amit" );

    System.out.println(d.get("10"));

//    System.out.println(d.remove("10")+"  has been removed");

    System.out.println("the  value of key 10 = " +d.get("10"));

 

 for (Enumeration e = d.keys();e.hasMoreElements();){
  
           
         String temp = String.valueOf(e.nextElement());
         System.out.println(temp);
         if(temp.trim().equals("2"))
         System.out.print(d.get(temp));
         

 }
    }
}
