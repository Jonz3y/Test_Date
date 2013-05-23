/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test_date;

import java.text.DateFormat;
import java.util.Date;

/**
 *
 * @author ppatel
 */ import java.util.*;
import java.text.*;
public class Test_Date {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
     

 try {  String str_date="Jun-11-07";
 DateFormat formatter ; 
 Date date ; 
  formatter = new SimpleDateFormat("dd-MMM-yy");
  date = (Date)formatter.parse(str_date);  
 System.out.println("Today is " +date );
  } catch (ParseException e)
  {System.out.println("Exception :"+e);
  System.out.println("  ** Prenez note qu’il ne s’agit");
  }  
 
 }
        
    }

