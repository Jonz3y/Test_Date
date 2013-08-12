
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import static test_word.Test_WordRead18_truck_date12_client_caching.cache;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ppatel
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    
    public static ArrayList<String>[] cache = null;
    public static int COLUMN_NO = 66;
    
    public static void main(String[] args) throws UnknownHostException, IOException {
        // TODO code application logic here
//          for(int i=0;i<COLUMN_NO;i++){
//          cache[i] = new ArrayList<String>();
//          
//      }
//          cache[0].add("hello");
//          
//          System.out.println(">>>> "+cache[0].get(0));
          
          
//          ArrayList[] test = new ArrayList[4];
//          
//          for(int i=0;i<test.length;i++)
//              test[i] = new ArrayList<String>();
//
////            test[3] = new ArrayList<String>();
//            
//          test[0].add("HI00");
//          test[0].
//          test[1].add("HI11");
//          test[2].add("HI22");
//          
//          test[3].add("HI333");
//          
//          for(int i=0;i<test.length;i++)
//          {
//              for(int j=0;j<test[i].size();j++)
//              {
//                  System.out.println(">>>>>>>"+test[i]);
//              }
//          }
//           

          //  System.out.println(test[3].get(0));
        
        
        String ipAddress = "127.0.0.1";
        InetAddress inet = InetAddress.getByName(ipAddress);

        System.out.println("Sending Ping Request to " + ipAddress);
        System.out.println(inet.isReachable(5000) ? "Host is reachable" : "Host is NOT reachable");

                          
        }
    }

