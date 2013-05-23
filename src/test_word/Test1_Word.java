/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test_word;

import java.io.File;
import java.util.Scanner;

public class Test1_Word {
  public static void main(String[] args) throws Exception {
    File file = new File("C:\\A_Work\\data.txt");
    Scanner scanner = new Scanner(file);
    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();

      Scanner lineScanner = new Scanner(line);
      lineScanner.useDelimiter(",");
      while (lineScanner.hasNext()) {
        String part = lineScanner.next();
        System.out.print(part);
      }
      System.out.println();
    }
  }
}