package test_word;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.PrintStream;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import static java.util.concurrent.TimeUnit.SECONDS;



/**
 *
 * @author ppatel
 */

    
public class Test_Main {
     String date = null; 
                        
      
     public static void main(String[] args) {
    // Get the scheduler
       
    ScheduledExecutorService scheduler =
      Executors.newSingleThreadScheduledExecutor();

    // Get a handle, starting now, with a 10 second delay

    final ScheduledFuture<?> timeHandle =
      scheduler.scheduleAtFixedRate(new TimePrinter(System.out), 0, 60, TimeUnit.MINUTES);    
    
    // Schedule the event, and run for 1 hour (60 * 60 seconds)
   
    scheduler.schedule(new Runnable() {
      public void run() {
        timeHandle.cancel(false);
      }
    }, 50*365 , TimeUnit.DAYS);

  }

 }
class TimePrinter implements Runnable  {
    private final PrintStream out;

   
        public TimePrinter(PrintStream out) {
        this.out = out;
        }

        public void run(){
                 try {
                     
                  System.out.println("start to run after interval  : >>> ");
                  FolderFetchIMAP obj = new FolderFetchIMAP();
                  obj.getmail();
                  
     
            } catch (Exception ex) {
                 ex.printStackTrace();
            }

       }
   }

 

   

    