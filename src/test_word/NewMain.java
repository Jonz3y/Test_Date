/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test_word;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ppatel
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
            Date now = new Date(); // java.util.Date, NOT java.sql.Date or java.sql.Timestamp!
            String format1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").format(now);
            String format2 = new SimpleDateFormat("MMMM,yyyy").format(new Date());
            String format3 = new SimpleDateFormat("yyyyMMddHHmmss").format(now);

        System.out.println(format1);
        System.out.println(format2);
        System.out.println(format3);
    }
}
