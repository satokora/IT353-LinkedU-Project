/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mail;

/**
 *
 * @author BillyLim
 */
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class JavaMailApp {

    public static void main(String[] args) {

        // Recipient's email ID needs to be mentioned.
        String to = "zochism@ilstu.edu";

        // Sender's email ID needs to be mentioned
        String from = "zochism@ilstu.edu";

        // Assuming you are sending email from this host
        String host = "smtp.ilstu.edu";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.user", "yourID"); // if needed
        properties.setProperty("mail.password", "yourPassword"); // if needed

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);

        try
        {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("Congratulations!");

            // Send the actual HTML message, as big as you like
            message.setContent("<h1>You have been promoted to the VP!</h1>",
                    "text/html");

            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        }
        
        catch (MessagingException mex)
        {
            mex.printStackTrace();
        }
    }
}
