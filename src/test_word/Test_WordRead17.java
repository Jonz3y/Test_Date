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
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author ppatel
 */

public class Test_WordRead17 {
    
    ArrayList<String> list = new ArrayList<String>();
    String element= null;
  public static void main(String[] args) throws SQLException {
      
      Test_WordRead17 FirstConvert = new Test_WordRead17();
      FirstConvert.CovertFromText();
      
      
  }
      private int count_batch;
    private Date date;
    private Timestamp timeStampDate1;
      public void CovertFromText() throws SQLException
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
          
          
          
          
          
          
          
          
          
          
          
          sc = new Scanner(new BufferedReader(new FileReader("C:\\All_file\\Roadnet_20(20).UP")));
          int count = 0 ;
          while(sc.hasNextLine()){
            list.clear();
            int columIndex = 0;
            int main_column_index = 0;
                
            
            String line = sc.nextLine();
            Scanner scline = new Scanner(line);
            scline.useDelimiter(" *\\| *");
            count++;
            
            
            while(scline.hasNext())
            {   
               
                boolean enter = true; 
                main_column_index++;
                
                if(main_column_index==8 ||main_column_index==9 || main_column_index==10 || main_column_index==11 || main_column_index==12 || main_column_index==13 || main_column_index==14 ||   main_column_index==17){
                    System.out.println("IGNORED COLUMN INDEX ######### "+main_column_index);
                    scline.next();
                    //enter = false;
               }
                
               else {
                System.out.println("main_column_index ###########"+main_column_index);
                 

//                 System.out.println("Enter value is  "+enter);
                
//                if(enter==true){
//                System.out.println("enter in loop");
                boolean check_Value = true;
                String value = null;
                value = scline.next();
//                System.out.print(value+",");
                columIndex+=1;
               
                
                if(value.trim() == null || value.trim().equals("")){
                    
                        check_Value= false;
                }

                
                
//                System.out.println("COLUM_INDEX FOR PREPARESTATEMENT value is "+columIndex);
                 
                
                switch (metaData.getColumnType(columIndex)) {
 
                    case 1: // Char Datatype

                            element = null;
                            stmt.setString(columIndex,value.trim());
                            element = "'"+value.trim()+"'";
                            list.add(element);
//                            System.out.println("char value set");
                            System.out.println("CHAR prepare statement"+columIndex+","+value.trim());
                         
                         break;
 
                    case 4: // integer datatype
                                element = null;
                                if(check_Value==false)
                                    value = String.valueOf(0);
                                    
                                
                            
                                double temp1 = Double.parseDouble(value.trim());
                                stmt.setInt(columIndex,((int)temp1));
                                if(main_column_index==1){
                                element = "'"+(String.valueOf((int)temp1/100))+"'";
                                list.add(element);
                                }
                                else{
                                    element = "'"+(String.valueOf((int)temp1))+"'";
                                list.add(element);
                                }
                                
//                                System.out.println("integer value set");
                                System.out.println("INT prepare statement"+columIndex+","+value.trim());
                       
                        break;
                   
                        
                    case 3:// Decimal dataype
                                element = null;
                                if(check_Value==false)
                                    value = String.valueOf(0.0);
                               
                               BigDecimal d = new BigDecimal(value.trim());
                               stmt.setBigDecimal(columIndex, d);
                               element = "'"+d+"'";
                               list.add(String.valueOf(d));
                               
//                               System.out.println("flot value set");
                               System.out.println("DECIMAL prepare statement"+columIndex+","+d);
                               
                        break;
                    
                        
                      
                    case 91: // Date dataype
                             
                                element = null;
                                DateFormat fromUser = new SimpleDateFormat("MM-dd-yyyy");
                                DateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
                                String reformattedStr = null;
                                
                         
                        
                                if(main_column_index==15){
                                    String temp =  value.substring(0, 2)+"-"+value.substring(2, 4)+"-"+value.substring(4, 8);
                                    
                                    reformattedStr =  myFormat.format((Date)fromUser.parse(temp.trim()));
                                    element = "'"+reformattedStr+"'";
                                    list.add(element);

                                }
                                else{
                                    reformattedStr =  myFormat.format((Date)myFormat.parse(value.trim()));
                                    element = "'"+reformattedStr+"'";
                                    list.add(element);
                            }
                        
                       
                                try {
                                    System.out.println("REFORMATTED DATE "+reformattedStr);
                                    Date final_Date = myFormat.parse(reformattedStr);
                                    System.out.println("date datatype value ^^^"+final_Date);
                                    java.sql.Date sqlDate = new java.sql.Date(final_Date.getTime());
                                    System.out.println("SQL  date datatype value ^^^ "+sqlDate);
                                    System.out.println("DATE prepare statement DATEEEEEEEEEEEEEEEEEEEEE<<<<<<<<<<"+columIndex+","+sqlDate);
                                    
                                    
                                    if(main_column_index==15)
                                        stmt.setDate(10, sqlDate);
                                     else   
                                    stmt.setDate(columIndex, sqlDate);
//                                  
                                    
//                                    System.out.println("Date value set");
                                    
                                     
                                } catch (ParseException e) {
                                    System.out.println("DATE EXCEPTION*********************************");
                                    e.printStackTrace();
                                }
                             
                        break;
                        
                    case 92:  // Time Data type

                        String srt_time = null;
                        
                        if(value.trim().length()>4){
                                if(!value.trim().substring(2,3).equalsIgnoreCase(":")){
                            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! inside");
                            for(int i=1;i<=8;i++){
                                  System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! pointer chnage ");
                                scline.next();
                            }
                                value = scline.next();
                               
                        }
                        }    
                        
                        if(main_column_index==37 || main_column_index==39)
                        {
                           
                            if(value.trim().length()<1){
                                System.out.println("if value is blacnk #########################");// if Time filed is null.
                                 stmt.setTimestamp(columIndex, (Timestamp) convertdate("00:00"));
                                 columIndex++;
                                 stmt.setTimestamp(columIndex, (Timestamp) convertdate("00:00"));
                                
                            }
                            
                            
                            else if(value.trim().length()==5)  // if only open or close time exist in Time field.
                            {
                                 System.out.println("if open or close time #########################");
                                 stmt.setTimestamp(columIndex, (Timestamp) convertdate("00:00"));
                                 columIndex++;
                                 stmt.setTimestamp(columIndex, (Timestamp) convertdate(value.trim()));
                            }
                            
                            else{                              // Both open and close time exist.
                            
                            System.out.println("###################"+value.trim().substring(0, 5));
                             System.out.println("###################"+value.trim().substring(0, 10));
                            
                            stmt.setTimestamp(columIndex, (Timestamp) convertdate(value.trim().substring(0, 5)));
                            columIndex++;
                            stmt.setTimestamp(columIndex, (Timestamp) convertdate(value.trim().substring(5, 10)));
                            }
                        }
                        else                                // simple time filed like i.e (11:05)
                            stmt.setTimestamp(columIndex, (Timestamp) convertdate(value.trim()));
                        
                        
                         break;    
                        
                    case 12:  // Varchar Date type
                            
                             if(columIndex!=6){
                             element = null;
                             
                             if(main_column_index==16)
                                 stmt.setString(11,value.trim());
                             else
                             stmt.setString(columIndex,value.trim());
                             element = "'"+value.trim()+"'";
                             list.add(element);
                             System.out.println("varchar");
                              System.out.println("VARCHAR prepare statement>>>>>>"+columIndex+","+value.trim());
                             }
                          
                             else{
                                 element = null;
                                 System.out.println("VARCHAR prepare statement >>>>>>>"+columIndex+","+value.trim().substring(0, 2));
                                 stmt.setString(columIndex,value.trim().substring(0, 2));
                                    element = "'"+value.trim().substring(0, 2)+"'";
                                    list.add(element);
                                 
                                 columIndex++;
                                 
                                 
                                 element = null;
                                 System.out.println("VARCHAR prepare statement >>>>>"+columIndex+","+value.trim().substring(2, 4));
                                 stmt.setString(columIndex,value.trim().substring(2, 4));
                                     element = "'"+value.trim().substring(2, 4)+"'";
                                     list.add(element);
                                 
                                 columIndex++;
                                 
                                 
                                 element = null;
                                 System.out.println("VARCHAR prepare statement >>>>>>"+columIndex+","+value.trim().substring(4, 12));
                                 stmt.setString(columIndex,value.trim().substring(4, 12));
                                     element = "'"+value.trim().substring(4, 12)+"'";
                                     list.add(element);
                             }
                           
                        if (main_column_index==69){
                                                                  
                                 System.out.println("#####################$$$$$$$$$$$$$$$$%%%%%%%%%%%%%%%%%%%%%%%%########################################################");
                                 
                               columIndex++;
                               
                               DateFormat toformat = new SimpleDateFormat("MM/dd/yy");
                               DateFormat myFormat1 = new SimpleDateFormat("yyyy-MM-dd");
                               String duedate = null;
                                
                                
                                
                            if(!list.get(37).trim().equalsIgnoreCase("'c.c'")){

                                     duedate  = getduedate();

                                  if(duedate.trim().length()>2){
                                    System.out.println("Final Arrival Date is######################### "+duedate);
                                    reformattedStr =  myFormat1.format((Date)toformat.parse(duedate.trim()));
                                    element = "'"+reformattedStr+"'";
                                    list.add(reformattedStr);
                                     try {
                                        Date final_date = myFormat1.parse(reformattedStr);
                                        System.out.println("date datatype value ^^^"+final_date);
                                        java.sql.Date sqlDate = new java.sql.Date(final_date.getTime());
                                        System.out.println("SQL  date datatype value ^^^ "+sqlDate);
                                        stmt.setDate(columIndex, sqlDate);

                                        System.out.println("Date value set");
                                        System.out.println("DATE prepare statement DATEEEEEEEEEEEEEEEEEEEEE >>>>>>"+columIndex+","+sqlDate);

                                    } catch (SQLException e) {
                                        System.out.println("DATE EXCEPTION*********************************");
                                        e.printStackTrace();
                                    }
                                    }

                                    else{
                                        System.out.println("date is nullllllllllllllllllllllllllllllll");
                                        stmt.setNull(columIndex, java.sql.Types.DATE);
                                    }

                               }
                              else{

                                    int date_length = list.get(9).trim().length();
                                    Date  final_date = ((Date)myFormat1.parse(list.get(9).trim().substring(1, (date_length-1))));
                                    System.out.println("date datatype value ^^^"+final_date);
                                    java.sql.Date sqlDate = new java.sql.Date(final_date.getTime());
                                    System.out.println("SQL  date datatype value ^^^ "+sqlDate);

                                    stmt.setDate(columIndex, sqlDate);

                              }
                             }
                               
                          
                            
                        
                        
                            break;
                    
                    
                   
                    default:
                 
                    }

            
            }
                      
            }                     
          
                        stmt.executeUpdate();
                        System.out.println("DONEEEEEEEEEEEE !!!!!!!!!!!!!!!!!!!!!!!!!! >> !!!!!");

                    System.out.println("ROW ...>>"+count);
          }
          
      
      if(stmt!=null)
          stmt.close();
      if(rs!=null)
          rs.close();
      
      } 
      
      
      
      catch (FileNotFoundException e) {
            e.printStackTrace();
                 e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
                 e.getMessage();
        } catch (SQLException e) {
            e.printStackTrace();
                 e.getMessage();
        }
      catch (Exception e) {
            e.printStackTrace();
                 e.getMessage();
        }
     finally{
          if(sc!=null)
              sc.close();
         
      }
  }
      /**
     *
     * @param value
     * @return
     * @throws ParseException
     * @throws SQLException
     */
    public Timestamp convertdate(String value) throws ParseException,SQLException
      {    
          element = null;
          DateFormat formatter = new SimpleDateFormat("HH:mm");
                             String str_time = null;
                             str_time=value.trim();
                             System.out.println("before formatting Date"+str_time);
                             
                             element = "'"+str_time+":00"+"'";
                             list.add(element);
                             
                             date = formatter.parse(str_time.trim()); 
                             timeStampDate1 = new Timestamp(date.getTime());
                             System.out.println("Time stamp object is "+timeStampDate1);

                             
          return timeStampDate1;
      
      }

    private String getduedate() {
            ResultSet rs = null;
            ResultSet rs1 = null;
            String value = null;
            String value1 = null;
        try {
            DateFormat myFormat1 = new SimpleDateFormat("yyyy-MM-dd");
                             

//            System.out.println("Driver load succussfully for getduedate method");
            
            Connection conn2 = DriverManager.getConnection("jdbc:as400://72.14.164.60/;"
                                                             + "naming=system;libraries=OS61LXDTA:OS61LXCUST:LXLIB;transaction\n" +
                                                              "isolation=none","ppatel","papranav");
            Statement statement = conn2.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                  ResultSet.CONCUR_UPDATABLE);
            
       System.out.println("date "+list.get(10));
       System.out.println("store no "+list.get(0));
       System.out.println("open time "+list.get(33));
       System.out.println("close "+list.get(34));
       System.out.println("dccccc "+list.get(5));
       System.out.println("lw "+list.get(6));
       for(int i = 0; i<list.size();i++)
       {
           System.out.println("Indix: "+i+" value: "+list.get(i));
       }

//       
//        rs = statement.executeQuery("select \n" +
//                                        "date("+list.get(10)+") + (case when sched.del_week_name = 'WEEK1'\n" +
//                                        "then (int(deldweek.weekdayno)   - int(truckdweek.weekdayno))\n" +
//                                        "when sched.del_week_name = 'WEEK2'\n" +
//                                        "then (int(deldweek.weekdayno)   +7 - int(truckdweek.weekdayno))\n" +
//                                        "when sched.del_week_name = 'WEEK3'\n" +
//                                        "then (int(deldweek.weekdayno)   +14 - int(truckdweek.weekdayno)) end) days as final_arrive_date\n" +
//                                        "from fstore_schedule1 as sched\n" +
//                                        ", commodity_def as commo\n" +
//                                        ", week_day as truckdweek, week_day as deldweek\n" +
//
//                                        "where sched.store_no = "+list.get(0)+"\n" +
//                                        "and substr(upper(dayname("+list.get(10)+")),1,3) = sched.truck_day\n" +
//                                        "and  sched.del_time_from = "+list.get(33)+"\n" +
//                                        "and  sched.del_time_to = "+list.get(34)+"\n" +
//                                        "and commo.dc =  "+list.get(5)+"\n" +
//                                        "and commo.lw = "+list.get(6)+"\n" +
//                                        "and commo.commodity = sched.commodity\n" +
//                                        "\n" +
//                                        "and sched.truck_day =  truckdweek.weekday\n" +
//                                        "\n" +
//                                        "and sched.del_day = deldweek.weekday");
      rs = statement.executeQuery("select \n" +
                                        "date("+list.get(10)+") + (case when truckdweek.weekdayno > deldweek.weekdayno\n" +
                                        "then \n" +
                                        "	(case when sched.del_week_name = 'WEEK1'\n" +
                                        "	then (7 - (int(truckdweek.weekdayno)) + int(deldweek.weekdayno))\n" +
                                        "	when sched.del_week_name = 'WEEK2'\n" +
                                        "	then (7 - (int(truckdweek.weekdayno))+ int(deldweek.weekdayno) +7)\n" +
                                        "	when sched.del_week_name = 'WEEK3'\n" +
                                        "	then (7 - (int(truckdweek.weekdayno))+ int(deldweek.weekdayno)+14) end)\n" +
                                        "\n" +
                                        "when truckdweek.weekdayno <= deldweek.weekdayno\n" +
                                        "	then (case when sched.del_week_name = 'WEEK1'\n" +
                                        "	then (int(deldweek.weekdayno)   - int(truckdweek.weekdayno))\n" +
                                        "	when sched.del_week_name = 'WEEK2'\n" +
                                        "	then (int(deldweek.weekdayno)   +7 - int(truckdweek.weekdayno))\n" +
                                        "	when sched.del_week_name = 'WEEK3'\n" +
                                        "	then (int(deldweek.weekdayno)   +14 - int(truckdweek.weekdayno)) end)end) days as final_arrive_date \n" +
                                        "\n" +
                                        "from fstore_schedule1 as sched, commodity_def as commo, week_day as truckdweek, week_day as deldweek\n" +
                                        "\n" +
                                        "where sched.store_no = "+list.get(0)+"\n" +
                                        "and substr(upper(dayname("+list.get(10)+")),1,3) = sched.truck_day\n" +
                                        "and  sched.del_time_from = "+list.get(33)+"\n" +
                                        "and  sched.del_time_to = "+list.get(34)+"\n" +
                                        "and commo.dc =  "+list.get(5)+"\n" +
                                        "and commo.lw = "+list.get(6)+"\n" +
                                        "and commo.commodity = sched.commodity\n" +
                                        "and sched.truck_day =  truckdweek.weekday\n" +
                                        "and sched.del_day = deldweek.weekday" );
         
//          if(!rs.wasNull())
//          {
//              System.out.println("inside record ");
//              if(rs.next())
//              {
//                  System.out.println("inside record in while loops ");
//                  value = rs.getString("final_arrive_date");
//              }
//              else{
//                    rs1 = null;
//                    value1 ="";
//                   System.out.println("else ");
        

             if (rs.first()) {
                  
//                  System.out.println("inside record in while loops ");
                  value = rs.getString("final_arrive_date");

             }
             else{
                 
                  
                 rs1 = null; 
//                 System.out.println("inside else loop");
                 rs1 = statement.executeQuery("select \n" +
                                        "date("+list.get(10)+") + (case when truckdweek.weekdayno > deldweek.weekdayno\n" +
                                        "then \n" +
                                        "	(case when sched.del_week_name = 'WEEK1'\n" +
                                        "	then (7 - (int(truckdweek.weekdayno)) + int(deldweek.weekdayno))\n" +
                                        "	when sched.del_week_name = 'WEEK2'\n" +
                                        "	then (7 - (int(truckdweek.weekdayno))+ int(deldweek.weekdayno) +7)\n" +
                                        "	when sched.del_week_name = 'WEEK3'\n" +
                                        "	then (7 - (int(truckdweek.weekdayno))+ int(deldweek.weekdayno)+14) end)\n" +
                                        "\n" +
                                        "when truckdweek.weekdayno <= deldweek.weekdayno\n" +
                                        "	then (case when sched.del_week_name = 'WEEK1'\n" +
                                        "	then (int(deldweek.weekdayno)   - int(truckdweek.weekdayno))\n" +
                                        "	when sched.del_week_name = 'WEEK2'\n" +
                                        "	then (int(deldweek.weekdayno)   +7 - int(truckdweek.weekdayno))\n" +
                                        "	when sched.del_week_name = 'WEEK3'\n" +
                                        "	then (int(deldweek.weekdayno)   +14 - int(truckdweek.weekdayno)) end)end) days as final_arrive_date \n" +
                                        "\n" +
                                        "from fstore_schedule1 as sched, commodity_def as commo, week_day as truckdweek, week_day as deldweek\n" +
                                        "\n" +
                                        "where sched.store_no = "+list.get(0)+"\n" +
                                        "and substr(upper(dayname("+list.get(10)+")),1,3) = sched.truck_day\n" +
                                        "and commo.dc =  "+list.get(5)+"\n" +
                                        "and commo.lw = "+list.get(6)+"\n" +
                                        "and commo.commodity = sched.commodity\n" +
                                        "and sched.truck_day =  truckdweek.weekday\n" +
                                        "and sched.del_day = deldweek.weekday");
                 
                 
                 System.out.println("select \n" +
                                        "date("+list.get(10)+") + (case when truckdweek.weekdayno > deldweek.weekdayno\n" +
                                        "then \n" +
                                        "	(case when sched.del_week_name = 'WEEK1'\n" +
                                        "	then (7 - (int(truckdweek.weekdayno)) + int(deldweek.weekdayno))\n" +
                                        "	when sched.del_week_name = 'WEEK2'\n" +
                                        "	then (7 - (int(truckdweek.weekdayno))+ int(deldweek.weekdayno) +7)\n" +
                                        "	when sched.del_week_name = 'WEEK3'\n" +
                                        "	then (7 - (int(truckdweek.weekdayno))+ int(deldweek.weekdayno)+14) end)\n" +
                                        "\n" +
                                        "when truckdweek.weekdayno <= deldweek.weekdayno\n" +
                                        "	then (case when sched.del_week_name = 'WEEK1'\n" +
                                        "	then (int(deldweek.weekdayno)   - int(truckdweek.weekdayno))\n" +
                                        "	when sched.del_week_name = 'WEEK2'\n" +
                                        "	then (int(deldweek.weekdayno)   +7 - int(truckdweek.weekdayno))\n" +
                                        "	when sched.del_week_name = 'WEEK3'\n" +
                                        "	then (int(deldweek.weekdayno)   +14 - int(truckdweek.weekdayno)) end)end) days as final_arrive_date \n" +
                                        "\n" +
                                        "from fstore_schedule1 as sched, commodity_def as commo, week_day as truckdweek, week_day as deldweek\n" +
                                        "\n" +
                                        "where sched.store_no = "+list.get(0)+"\n" +
                                        "and substr(upper(dayname("+list.get(10)+")),1,3) = sched.truck_day\n" +
                                       "and commo.dc =  "+list.get(5)+"\n" +
                                        "and commo.lw = "+list.get(6)+"\n" +
                                        "and commo.commodity = sched.commodity\n" +
                                        "and sched.truck_day =  truckdweek.weekday\n" +
                                        "and sched.del_day = deldweek.weekday");
                                            
                 
                                               
                                                
                                                if(rs1.first()){
//                                                    System.out.println("insdie second loop");
//                                                    System.out.println("value is @@@@@@@@@@@"+rs1.getString("final_arrive_date"));
                                                     value = rs1.getString("final_arrive_date");
                                                     
                                                 }
                                                else{
                                                    System.out.println("no value ");
                                                    value = "";
                                                }
//                                                if(rs1.wasNull())
//                                                    System.out.println("result set is null ");
                                           
//                                          System.out.println("value is $$$$$$$$$$$$$$$$"+value.trim());     
      
                                            
             }              
//                                                 if (rs!=null){
//                                                  System.out.println("inside record in second while loops ");
                                               
                                              
                   
              
                                             
                  }

                 
         catch (Exception ex) {
            ex.printStackTrace();
        }
//            if(!value.trim().equalsIgnoreCase("") || value.trim()!=null)
                return value.trim(); 
//            else
//                return value1.trim();
      }
      
}

    

