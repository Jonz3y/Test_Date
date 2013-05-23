package test_word;

/*
Java Swing, 2nd Edition
By Marc Loy, Robert Eckstein, Dave Wood, James Elliott, Brian Cole
ISBN: 0-596-00408-7
Publisher: O'Reilly 
*/
//SimpleFileChooser.java
//A simple file chooser to see what it takes to make one of these work.
//
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class SimpleFileChooser extends JFrame {

public SimpleFileChooser() {
 super("File Chooser Test Frame");
 setSize(350, 200);
 setDefaultCloseOperation(EXIT_ON_CLOSE);

 Container c = getContentPane();
 c.setLayout(new FlowLayout());
 
 JButton openButton = new JButton("Open");
 JButton saveButton = new JButton("Save");
 JButton dirButton = new JButton("Pick Dir");
 final JLabel statusbar = 
              new JLabel("Output of your selection will go here");
  final JLabel statusbar1 = 
              new JLabel("parth String");

 // Create a file chooser that opens up as an Open dialog
 openButton.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent ae) {
     JFileChooser chooser = new JFileChooser();
     chooser.setMultiSelectionEnabled(true);
     int option = chooser.showOpenDialog(SimpleFileChooser.this);
     if (option == JFileChooser.APPROVE_OPTION) {
       File[] sf = chooser.getSelectedFiles();
        String path = sf[0].getAbsolutePath();
       String filelist = "nothing";
       if (sf.length > 0) filelist = sf[0].getName();
       for (int i = 1; i < sf.length; i++) {
         filelist += ", " + sf[i].getName();
       }
       statusbar.setText("You chose " + filelist);
       statusbar1.setText("path " + path);
       
     }
     else {
       statusbar.setText("You canceled.");
     }
   }
 });

 // Create a file chooser that opens up as a Save dialog
 saveButton.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent ae) {
     JFileChooser chooser = new JFileChooser();
     int option = chooser.showSaveDialog(SimpleFileChooser.this);
     if (option == JFileChooser.APPROVE_OPTION) {
       statusbar.setText("You saved " + ((chooser.getSelectedFile()!=null)?
                         chooser.getSelectedFile().getName():"nothing"));
     }
     else {
       statusbar.setText("You canceled.");
     }
   }
 });

 // Create a file chooser that allows you to pick a directory
 // rather than a file
 dirButton.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent ae) {
     JFileChooser chooser = new JFileChooser();
     chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
     int option = chooser.showOpenDialog(SimpleFileChooser.this);
     if (option == JFileChooser.APPROVE_OPTION) {
       statusbar.setText("You opened " + ((chooser.getSelectedFile()!=null)?
                         chooser.getSelectedFile().getName():"nothing"));
     }
     else {
       statusbar.setText("You canceled.");
     }
   }
 });

 c.add(openButton);
 c.add(saveButton);
 c.add(dirButton);
 c.add(statusbar);
 c.add(statusbar1);
}

public static void main(String args[]) {
 SimpleFileChooser sfc = new SimpleFileChooser();
 sfc.setVisible(true);
}
}