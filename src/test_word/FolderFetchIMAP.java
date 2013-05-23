package test_word;




import java.io.*;
import java.util.*;
import javax.mail.*;
import javax.mail.Flags.Flag;
import javax.mail.internet.*;

import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.imap.IMAPMessage;
import java.text.SimpleDateFormat;
import javax.mail.search.FlagTerm;


public class FolderFetchIMAP {
    
        IMAPFolder folder = null;
        Store store = null;
        String subject = null;
        Flag flag = null;
        String saveDirectory = "C:\\All_file_New\\testfolder";
        Date date = null;
        public static final String DATE_FORMAT_NOW = "HHmmssSSS";


    public static void main(String[] args)  {
    }
    
    public void getmail() throws MessagingException{   
        try 
        {
           int count = 0;
       
          Properties props = System.getProperties();
          props.setProperty("mail.store.protocol", "imaps");

          Session session = Session.getDefaultInstance(props, null);

          store = session.getStore("imaps");
          store.connect("imap.googlemail.com","automation@logisticsalliance.com", "b6webU7u");

          folder = (IMAPFolder) store.getFolder("roadnetexport"); // This doesn't work for other email account
          //folder = (IMAPFolder) store.getFolder("inbox"); This works for both email account


          if(!folder.isOpen())
          folder.open(Folder.READ_WRITE);
          
            Flags seen = new Flags(Flags.Flag.SEEN);
            FlagTerm unseenFlagTerm = new FlagTerm(seen, false);
            Message arrayMessages[] = folder.search(unseenFlagTerm);
            
//            MimeMessage source = (MimeMessage) folder.getMessage(1);
//            MimeMessage copy = new MimeMessage(source);
//           Message[] arrayMessages = folder.getMessages();
 
            for (int i = 0; i < arrayMessages.length; i++) {
                Message message = arrayMessages[i];
                Address[] fromAddress = message.getFrom();
                String from = fromAddress[0].toString();
                //String subject = message.getSubject();
                String sentDate = message.getSentDate().toString();
 
                String contentType = message.getContentType();
                String messageContent = "";
 
                // store attachment file name, separated by comma
                String attachFiles = "";
 
                if (contentType.contains("multipart")) {
                    // content may contain attachments
                    Multipart multiPart = (Multipart) message.getContent();
                    int numberOfParts = multiPart.getCount();
                    for (int partCount = 0; partCount < numberOfParts; partCount++) {
                        MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(partCount);
                        if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
                            // this part is attachment
                            String fileName = part.getFileName();
                           
                            
                            Calendar cal = Calendar.getInstance();
                            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
                            String temp = sdf.format(cal.getTime()).trim();
                            fileName = fileName+temp;
                            System.out.println(">>>>>>>>>>>>>>>>>"+fileName);
                            attachFiles += fileName + ", ";
                            part.saveFile(saveDirectory + File.separator + fileName);
                        } else {
                            // this part may be the message content
                            messageContent = part.getContent().toString();
                        }
                    }
 
                    if (attachFiles.length() > 1) {
                        attachFiles = attachFiles.substring(0, attachFiles.length() - 2);
                    }
                } else if (contentType.contains("text/plain")
                        || contentType.contains("text/html")) {
                    Object content = message.getContent();
                    if (content != null) {
                        messageContent = content.toString();
                    }
                }
 
                // print out details of each message
                System.out.println("Message #" + (i + 1) + ":");
                System.out.println("\t From: " + from);
                System.out.println("\t Subject: " + subject);
                System.out.println("\t Sent Date: " + sentDate);
//              System.out.println("\t Message: " + messageContent);
                System.out.println("\t Attachments: " + attachFiles);
            }
 
        
        } catch (NoSuchProviderException ex) {
            System.out.println("No provider for pop3.");
            ex.printStackTrace();
        } catch (MessagingException ex) {
            System.out.println("Could not connect to the message store");
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
 
        finally 
        {
          if (folder != null && folder.isOpen()) { folder.close(true); }
          if (store != null) { store.close(); }
        }

    }



}