/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test_word;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;
import java.util.StringTokenizer;


/**
 *
 * @author ppatel
 */

public class Test_WordRead1 {
  public static void main(String[] args) throws Exception {
      Test_WordRead1 FirstConvert = new Test_WordRead1();
      FirstConvert.CovertFromText();
      
      
  }
      private int count_batch;
    private Date date;
    private Timestamp timeStampDate1;
      public void CovertFromText()
      {
      
      
      Scanner sc = null;
      
             
      
      try{
            //String fields = "SESSION_DATE,ROUTE_ID,ROUTE_DESCRIPTION,ROUTE_START_DATE,ROUTE_START_TIME,DEPOT_ID,DEPOT_TYPE,STOP_NUMBER,STOP_LOCATION,LOCATION_DESCRIPTION,ADDRESS_LINE1,ADDRESS_LINE2,CITY,PHONE_NUMBER,ZIP_CODE,STATE,LATITUDE,LONGITUDE,OPEN_CLOSE_TIME,TIME_WINDOWS,TRAVEL_TIME,DISTANCE,ARRIVAL_DATE,ARRIVAL_TIME,SERVICE_TIME,TOTAL _OF _SIZE1,TOTAL_ OF_ SIZE2,TOTAL_ OF_ SIZE3,DRIVER1_ID,PREVIOUS_LOCATION,DRIVER1_FIRST_NAME,DRIVER1_MIDDLE_NAME,DRIVER1_LAST_NAME,DRIVER2_ID,DRIVER2_FIRST_NAME,DRIVER2_MIDDLE_NAME,DRIVER2_LAST_NAME,ROUTE_EQUIPMENT_ID,ROUTE_EQUIPMENT_TYPE,ROUTE_EQUIPMENT_OWNER,TRIP_NUMBER,FIXED_SERVIC_ TIME,VARIABLE_SERVICE_TIME,PRE-ROUTE_TIME,STOP_TYPE,POST-ROUTE_TIME,ROUTE_DEPARTURE_TIME,ROUTE_ARRIVAL_TIME,ROUTE_COMPLETE_TIME,INTERNAL_ID";
            Connection conn = null;
            String sql = null;
            PreparedStatement stmt = null;
            
            System.out.println("Enter in Try block");
            Properties props = new Properties();
            String path = System.getProperty("user.dir")+"/test.properties";
            System.out.println("path is "+path);
            props.load(new FileInputStream(path));  
            
 
            String DRIVER = "com.ibm.as400.access.AS400JDBCDriver";       
            String URL = "jdbc:as400://72.14.164.60/;naming=system;libraries=OS61LXDTA:OS61LXCUST:LXLIB;transaction\n" +
                         "isolation=none";
            
            
 
            //Connect to iSeries                                        
            Class.forName(DRIVER);
            
            
            System.out.println("user id is "+props.getProperty("userId").trim());
            System.out.println("password  is "+props.getProperty("password").trim());
            System.out.println("filename   is "+props.getProperty("fileName1").trim());
            
            
            
            conn = DriverManager.getConnection(URL,props.getProperty("userId").trim(),props.getProperty("password").trim());  
 
            sql = "SELECT " + props.getProperty("fields1").trim() + " from " + props.getProperty("fileName1").trim();
            //sql = "SELECT * from " + props.getProperty("fileName1").trim();
            System.out.println("sql statement is "+sql);
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
 
            ResultSetMetaData metaData = rs.getMetaData();
            int colCount = metaData.getColumnCount();
            System.out.println("column count is "+colCount);
 
            String q = "";
            for (int c = 0; c < colCount; c++) {
                if(q.equalsIgnoreCase("")){
                    q = "?";
                }
                else{
                    q = q + ",?";
                }
            }
            System.out.println("value of q is "+q);
            sql = "INSERT into " +
            props.getProperty("fileName1").trim() +
            " (" + props.getProperty("fields1").trim() + ") VALUES(" + q + ")";
          //  sql = "INSERT into FDELIVERY_MAIN (SESSION_DATE ,ROUTE_ID,ROUTE_DESCRIPTION,ROUTE_START_DATE ,ROUTE_START_TIME,DEPOT_ID ,DEPOT_TYPE,STOP_NUMBER ,STOP_LOCATION,ADDRESS_LINE1 ,ADDRESS_LINE2 ,LOCATION_DESCRIPTION ,CITY ,PHONE_NUMBER,  ZIP_CODE ,STATE ,LATITUDE,LONGITUDE ,OPEN_CLOSE_TIME,TIME_WINDOWS ,TRAVEL_TIME ,DISTANCE ,ARRIVAL_DATE,ARRIVAL_TIME,SERVICE_TIME,TOTAL_OF_SIZE1 ,TOTAL_OF_SIZE2,TOTAL_OF_SIZE3,DRIVER1_ID ,PREVIOUS_LOCATION,DRIVER1_FIRST_NAME,DRIVER1_MIDDLE_NAME,DRIVER1_LAST_NAME,DRIVER2_ID,DRIVER2_FIRST_NAME,DRIVER2_MIDDLE_NAME,DRIVER2_LAST_NAME,ROUTE_EQUIPMENT_ID,ROUTE_EQUIPMENT_TYPE,ROUTE_EQUIPMENT_OWNER ,TRIP_NUMBER,FIXED_SERVICE_TIME,VARIABLE_SERVICE_TIME,PRE_ROUTE_TIME,STOP_TYPE ,POST_ROUTE_TIME,ROUTE_DEPARTURE_TIME,ROUTE_ARRIVAL_TIME ,ROUTE_COMPLETE_TIME,INTERNAL_ID) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            System.out.println(sql);
            stmt = conn.prepareStatement(sql);
          
          
          
          
          
          
          
          
          
          
          
          sc = new Scanner(new BufferedReader(new FileReader("C:\\A_Work\\ROUTE_NET\\RTEEXPORT_MAIN.txt")));
          
          while(sc.hasNextLine()){
            
            int columIndex = 0;  
            String line = sc.nextLine();
            Scanner scline = new Scanner(line);
            scline.useDelimiter(" *\\| *");
            
            
            while(scline.hasNext())
            {   boolean check_Value = true;
                String value = null;
                value = scline.next();
                System.out.print(value+",");
                columIndex+=1;
                
                if(value.trim() == null || value.trim().equals("")){
                    
                        check_Value= false;
                }
//            }
//            System.out.println();
            
            
            
//            for(int columIndex=1;columIndex<=colCount;columIndex++)
//            {
//               
                System.out.println("column index value is "+columIndex);
                 switch (metaData.getColumnType(columIndex)) {
 
                    case 1: // Char Datatype

                           // System.out.println("column index(case1) varialbe is ]]]]  "+columIndex);
                            stmt.setString(columIndex,value.trim());
                            System.out.println("char value set");
                            System.out.println("prepare statement"+columIndex+","+value.trim());
                         
                         break;
 
                    case 4: // integer datatype
                                if(check_Value==false)
                                    value = String.valueOf(0);
                                    
                                
                            
                                double temp1 = Double.parseDouble(value.trim());
                                stmt.setInt(columIndex,((int)temp1));
                                
                                System.out.println("integer value set");
                                System.out.println("prepare statement"+columIndex+","+value.trim());
                       
                        break;
                   
                        
                    case 3:// Decimal dataype
                               //Float temp3 = Float.parseFloat(value);
                                if(check_Value==false)
                                    value = String.valueOf(0.0);
                               
                                BigDecimal d = new BigDecimal(value);
                               stmt.setBigDecimal(columIndex, d);
                               System.out.println("flot value set");
                               System.out.println("prepare statement"+columIndex+","+d);
                               
                        break;
                    
                        
                      
                    case 91: // Date dataype
                        
                                DateFormat fromUser = new SimpleDateFormat("MM-dd-yyyy");
                                DateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");

                                try {
                                     
                                    
                                    String reformattedStr =  myFormat.format((Date)fromUser.parse(value.trim()));
                                    Date final_Date = myFormat.parse(reformattedStr);
                                    System.out.println("date datatype value ^^^"+final_Date);
                                    java.sql.Date sqlDate = new java.sql.Date(final_Date.getTime());
                                    System.out.println("SQL  date datatype value ^^^ "+sqlDate);
                                    stmt.setDate(columIndex, sqlDate);
                                    System.out.println("Date value set");
                                    System.out.println("prepare statement DATEEEEEEEEEEEEEEEEEEEEE<<<<<<<<<<"+columIndex+","+sqlDate);
                                     
                                } catch (ParseException e) {
                                    System.out.println("DATE EXCEPTION*********************************");
                                    e.printStackTrace();
                                }
                             
                        break;
                        
                    case 92:  // Time Data type
                           
                             int length = 0;
                             String open_Time = null;
                             String close_Time = null;
                             
                             if(columIndex>=19 && columIndex<=22){
                               if(check_Value){
                                     length = String.valueOf((int) Double.parseDouble(value.trim())).length();
                                     System.out.println("LENGTH IS >>>>>>>>>>>>>> "+length);

                                     if(length >=5){

                                        // OPEN TIME CALCULATION
                                         open_Time = String.valueOf((int)(Double.parseDouble(value.trim())/10000));
                                        
                                         // CLOSE TIME CALCULATION
                                         close_Time = String.valueOf((int)(Double.parseDouble(value.trim())%10000));
                                       
                                     }
                                     else if(length>=1 && length<=4)
                                     {
                                         open_Time = "00:00";
                                         System.out.println("Time set%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
                                         close_Time = String.valueOf((int)(Double.parseDouble(value.trim())));
                                     }
                               }
                               else{
                                   open_Time="00:00";
                                   close_Time="00:00";
                               }
                             
                               stmt.setTimestamp(columIndex, (Timestamp) convertdate(open_Time,length));
                                System.out.println("prepare statement set timestamp for open<<<<<<<<<<<<<"+columIndex+","+(Timestamp) convertdate(open_Time,length));
                               columIndex++;
                               stmt.setTimestamp(columIndex, (Timestamp) convertdate(close_Time,length));
                                System.out.println("prepare statement set timestamp for close<<<<<<<<<<"+columIndex+","+(Timestamp) convertdate(close_Time,length));
                             
                             }
                                     else{
                                        stmt.setTimestamp(columIndex, (Timestamp) convertdate(value,length));
                                        System.out.println("Time value set");
                                        System.out.println("prepare statement time stamp for else<<<<<<<<<<"+columIndex+","+(Timestamp) convertdate(value,length));
                                     }


                         break;    
                        
                    case 12:  // Varchar Date type
                            
                           // System.out.println("column index(case12) varialbe is ]]]]  "+columIndex);
//                        if(columIndex==40){
//                           if(value.equalsIgnoreCase("-"))
//                               System.out.println("innnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn");
//                              value = "Blank";
//                        }
//                       
                             stmt.setString(columIndex,value.trim());
                             System.out.println("Varchar value set");
                             System.out.println("prepare statement<<<<<<<<<<<"+columIndex+","+value.trim());
                        
                        
                            break;
                    
                    
                   
                    default:
                       
                    }
                }
                        stmt.executeUpdate();
                        System.out.println("DONEEEEEEEEEEEE !!!!!!!!!!!!!!!!!!!!!!!!!! >> !!!!!");
                        
//                      stmt.addBatch();
//                      ++count_batch;
                
                      
//            }
            
           
            
//      }
//                    stmt.executeBatch();
//                    int[] affectedRecords = stmt.executeBatch();
//                    System.out.println("Total record are inserted are :"+affectedRecords);
//      
      }}
      catch (FileNotFoundException e) {
            e.printStackTrace();
                 e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
                 e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
                 e.getMessage();
        }
     finally{
          if(sc!=null)
              sc.close();
      }
  }
      public Timestamp convertdate(String value, int length) throws ParseException
      {    
          System.out.println("<<<<<<value and length is>>>>>"+value+","+length);
          String str_time = null;
          DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
                
          
                    if(length>=1 && length<=8){ 
                          
                         String hour = String.valueOf((int)(Double.parseDouble(value)/100));
                         String minutes = String.valueOf((int)(Double.parseDouble(value)%100));

                         int temp = ((int) Double.parseDouble(value)%100) ;
                         int str_length = String.valueOf((int)(Double.parseDouble(value))).length()  ;
                            
                       if(str_length ==3 ){
                               if(temp==0)
                                    str_time = ("0"+hour+":0"+minutes+":00");
                               else
                                    str_time = ("0"+hour+":"+minutes+":00");
                            }
                            
                            else if(str_length==4){
                                if(temp==0)
                                        str_time = hour+":0"+minutes+":00";
                                else
                                        str_time = hour+":"+minutes+":00";
                            }
                            
                            else if(str_length==2){
                                minutes = String.valueOf((int)(Double.parseDouble(value)));
                                str_time = "00:"+minutes+":00";
                            }
                          
                                
                            
                     }
                     else{
                            str_time = value.trim()+":00";

                     }
                             System.out.println("after formatting Date"+str_time);
                             date = formatter.parse(str_time.trim()); 
                             timeStampDate1 = new Timestamp(date.getTime());
                             System.out.println("Time stamp object is "+timeStampDate1);
//                             stmt.setTimestamp(columIndex, (Timestamp) timeStampDate1);
//                             System.out.println("Time value set");
          return timeStampDate1;
      }
}
