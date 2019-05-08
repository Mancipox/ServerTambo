/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package awsSES;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author usuario
 */
public class GmailPostman implements PostMan {
      String from,to,subject,body;


    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public void send() {
        String resourceName = "config.properties";
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		Properties config = new Properties();
		try(InputStream resourceStream = loader.getResourceAsStream(resourceName)) {
		    config.load(resourceStream);
		} catch (IOException ex) {
              Logger.getLogger(GmailPostman.class.getName()).log(Level.SEVERE, null, ex);
          }
	final String gmailPassword = "mancidog123";
       Properties props = System.getProperties();
    props.put("mail.smtp.host", "smtp.gmail.com");  //El servidor SMTP de Google
    props.put("mail.smtp.user", this.from);
    props.put("mail.smtp.clave", gmailPassword);    //La clave de la cuenta
    props.put("mail.smtp.auth", "true");    //Usar autenticación mediante usuario y clave
    props.put("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
    props.put("mail.smtp.port", "587"); //El puerto SMTP seguro de Google

    Session session = Session.getDefaultInstance(props);
    MimeMessage message = new MimeMessage(session);

    try {
        message.setFrom(new InternetAddress(this.from));
        message.addRecipients(Message.RecipientType.TO,InternetAddress.parse(this.to));   //Se podrían añadir varios de la misma manera
        message.setSubject(this.subject);
        message.setText(this.body);
        Transport transport = session.getTransport("smtp");
        transport.connect("smtp.gmail.com", this.from, gmailPassword);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }
    catch (MessagingException me) {
        me.printStackTrace();   //Si se produce un error
    }  }
    
    @Override
    public PostMan withFrom(String from) {
this.from = from;
return this;
    }

    @Override
    public PostMan withSubject(String subject) {
         this.subject = subject;
    return this;
    }

    @Override
    public PostMan withBody(String body) {
    this.body=body;
    return this;
    }

    @Override
    public PostMan withTo(String to) {
         this.to = to;
    return this;
    }
}
