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

public class Test_WordRead12 {
  public static void main(String[] args) throws Exception {
      Test_WordRead12 FirstConvert = new Test_WordRead12();
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
 
           // sql = "SELECT " + props.getProperty("fields1").trim() + " from " + props.getProperty("fileName1").trim();
            sql = "SELECT * from " + props.getProperty("fileName1").trim();
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
          
          
          
          
          
          
          
          
          
          
          
          sc = new Scanner(new BufferedReader(new FileReader("C:\\A_Work\\roadnet.txt")));
          int count = 0 ;
          while(sc.hasNextLine()){
            
            int columIndex = 0;
            int column_count = 0;
            
            String line = sc.nextLine();
            Scanner scline = new Scanner(line);
            scline.useDelimiter(" *\\| *");
            count++;
            
            
            while(scline.hasNext())
            {   
               
                boolean enter = true; 
                column_count++;
                
                if(column_count==9 || column_count==10 || column_count==11 || column_count==12 || column_count==13 || column_count==14 ||  column_count==16 || column_count==17){
                    System.out.println("value of column count is "+column_count);
                    scline.next();
                    //enter = false;
               }
                
               else {
                System.out.println("column count is >>>>>>>>>>>>>>>>"+column_count);
                 
//                 if((column_count>=9 && column_count<=14) ||  column_count==16 || column_count==17){
//                     enter = false;
//                     System.out.println("deop column and come inside "+enter);
//                 }
//                 
                 System.out.println("Enter value is  "+enter);
                
//                if(enter==true){
                System.out.println("enter in loop");
                boolean check_Value = true;
                String value = null;
                value = scline.next();
                System.out.print(value+",");
                columIndex+=1;
               
                
                if(value.trim() == null || value.trim().equals("")){
                    
                        check_Value= false;
                }

                
                
                System.out.println("column index value is "+columIndex);
                 
                
                switch (metaData.getColumnType(columIndex)) {
 
                    case 1: // Char Datatype

                           
                            stmt.setString(columIndex,value.trim());
                            System.out.println("char value set");
                            System.out.println("CHAR prepare statement"+columIndex+","+value.trim());
                         
                         break;
 
                    case 4: // integer datatype
                                if(check_Value==false)
                                    value = String.valueOf(0);
                                    
                                
                            
                                double temp1 = Double.parseDouble(value.trim());
                                stmt.setInt(columIndex,((int)temp1));
                                
                                System.out.println("integer value set");
                                System.out.println("INT prepare statement"+columIndex+","+value.trim());
                       
                        break;
                   
                        
                    case 3:// Decimal dataype
                               
                                if(check_Value==false)
                                    value = String.valueOf(0.0);
                               
                                BigDecimal d = new BigDecimal(value);
                               stmt.setBigDecimal(columIndex, d);
                               System.out.println("flot value set");
                               System.out.println("DECIMAL prepare statement"+columIndex+","+d);
                               
                        break;
                    
                        
                      
                    case 91: // Date dataype
                                
                                DateFormat fromUser = new SimpleDateFormat("MM-dd-yyyy");
                                DateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
                                String reformattedStr = null;
                        
                                if(column_count==15){
                                    String temp =  value.substring(0, 2)+"-"+value.substring(2, 4)+"-"+value.substring(4, 8);
                                    
                                    reformattedStr =  myFormat.format((Date)fromUser.parse(temp.trim()));

                                }
                                else
                                    reformattedStr =  myFormat.format((Date)myFormat.parse(value.trim()));
                        
                               
                                try {
                                    Date final_Date = myFormat.parse(reformattedStr);
                                    System.out.println("date datatype value ^^^"+final_Date);
                                    java.sql.Date sqlDate = new java.sql.Date(final_Date.getTime());
                                    System.out.println("SQL  date datatype value ^^^ "+sqlDate);
                                    stmt.setDate(columIndex, sqlDate);
                                    System.out.println("Date value set");
                                    System.out.println("DATE prepare statement DATEEEEEEEEEEEEEEEEEEEEE<<<<<<<<<<"+columIndex+","+sqlDate);
                                     
                                } catch (ParseException e) {
                                    System.out.println("DATE EXCEPTION*********************************");
                                    e.printStackTrace();
                                }
                             
                        break;
                        
                    case 92:  // Time Data type

                        String srt_time = null;
                        
                        if(column_count==37 || column_count==39)
                        {
                           
                            if(value.trim().length()<=5)
                            {
                                 stmt.setTimestamp(columIndex, (Timestamp) convertdate("00:00"));
                                 columIndex++;
                                 stmt.setTimestamp(columIndex, (Timestamp) convertdate(value.trim()));
                            }
                            
                            else{
                            
                            System.out.println("###################"+value.trim().substring(0, 5));
                             System.out.println("###################"+value.trim().substring(0, 10));
                            
                            stmt.setTimestamp(columIndex, (Timestamp) convertdate(value.trim().substring(0, 5)));
                            columIndex++;
                            stmt.setTimestamp(columIndex, (Timestamp) convertdate(value.trim().substring(5, 10)));
                            }
                        }
                        else
                            stmt.setTimestamp(columIndex, (Timestamp) convertdate(value.trim()));
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                         break;    
                        
                    case 12:  // Varchar Date type
                            
                             if(columIndex!=6){
                             stmt.setString(columIndex,value.trim());
                             System.out.println("varchar");
                             }
                             else{
//                                 System.out.println("DC "+value.trim().substring(0, 2));
//                                   System.out.println("lw "+value.trim().substring(2, 4));
//                                     System.out.println("on "+value.trim().substring(4, 12));
                                 System.out.println("VARCHAR prepare statement<<<<<<<<<<<"+columIndex+","+value.trim().substring(0, 2));
                                 stmt.setString(columIndex,value.trim().substring(0, 2));
                                 columIndex++;
                                 
                                 System.out.println("VARCHAR prepare statement<<<<<<<<<<<"+columIndex+","+value.trim().substring(2, 4));
                                 stmt.setString(columIndex,value.trim().substring(2, 4));
                                 columIndex++;
                                 
                                 System.out.println("VARCHAR prepare statement<<<<<<<<<<<"+columIndex+","+value.trim().substring(4, 12));
                                 stmt.setString(columIndex,value.trim().substring(4, 12));
                             }
                               
                             System.out.println("Varchar value set");
                             System.out.println("VARCHAR prepare statement<<<<<<<<<<<"+columIndex+","+value.trim());
                        
                        
                            break;
                    
                    
                   
                    default:
                 
                    }
                
                
                
                
//                }
            
            }
                      
            }              
          
                        stmt.executeUpdate();
                        System.out.println("DONEEEEEEEEEEEE !!!!!!!!!!!!!!!!!!!!!!!!!! >> !!!!!");

                    System.out.println("ROW**************************************************************"+count);
          }
          } 
      
      
      
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
      public Timestamp convertdate(String value) throws ParseException
      {    
//          System.out.println("<<<<<<value and length is>>>>>"+value+","+length);
//          String str_time = null;
//          DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
//                
//                    
//                    if(length>=1 && length<=8){
//                        
//                       if(value.trim().equalsIgnoreCase("null"))
//                            str_time="00:00:00";
//                        else
//                        {
//                         String hour = String.valueOf((int)(Double.parseDouble(value)/100));
//                         String minutes = String.valueOf((int)(Double.parseDouble(value)%100));
//
//                         int temp = ((int) Double.parseDouble(value)%100) ;
//                         int str_length = String.valueOf((int)(Double.parseDouble(value))).length()  ;
//                            
//                       if(str_length ==3 ){
//                               if(temp==0)
//                                    str_time = ("0"+hour+":0"+minutes+":00");
//                               else
//                                    str_time = ("0"+hour+":"+minutes+":00");
//                            }
//                            
//                            else if(str_length==4){
//                                if(temp==0)
//                                        str_time = hour+":0"+minutes+":00";
//                                else
//                                        str_time = hour+":"+minutes+":00";
//                            }
//                            
//                            else if(str_length==2){
//                                minutes = String.valueOf((int)(Double.parseDouble(value)));
//                                str_time = "00:"+minutes+":00";
//                            }
//                          
//                                
//                            
//                     }
//                    
//                            
//                        
//                        
//                    }
//                     else{
//                            str_time = value.trim()+":00";
//
//                     }   
                             DateFormat formatter = new SimpleDateFormat("HH:mm");
                             String str_time = null;
                             str_time=value.trim();
                             System.out.println("before formatting Date"+str_time);
                             date = formatter.parse(str_time.trim()); 
                             timeStampDate1 = new Timestamp(date.getTime());
                             System.out.println("Time stamp object is "+timeStampDate1);
//                             stmt.setTimestamp(columIndex, (Timestamp) timeStampDate1);
//                             System.out.println("Time value set");
          return timeStampDate1;
      }
}
