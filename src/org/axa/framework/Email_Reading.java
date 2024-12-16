package org.axa.framework;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.MimeMultipart;


public class Email_Reading {

	//public class GmailOTPReader {
	    public static void main(String[] args) {
	        final String username = "prmishra9313@gmail.com";
	        final String password = "9926185994";

	        Properties props = new Properties();
	        props.put("mail.store.protocol", "imaps");
	        props.put("mail.imap.ssl.enable", "true");
	        props.put("mail.imap.host", "imap.gmail.com");
	        props.put("mail.imap.port", "993");

	        try {
	            Session session = Session.getInstance(props, null);
	            Store store = session.getStore("imaps");
	            store.connect("imap.gmail.com", username, password);

	            Folder inbox = store.getFolder("inbox");
	            inbox.open(Folder.READ_ONLY);

	            Message[] messages = inbox.getMessages();
	            for (Message message : messages) {
	                if (message.getSubject().equals("【アクサダイレクト】認証番号のご案内")) {
	                    Object content = message.getContent();
	                    if (content instanceof String) {
	                        String otp = (String) content; // Extract the OTP from the email content
	                        System.out.println("OTP: " + otp);
	                    } else if (content instanceof Multipart) {
	                        Multipart multiPart = (Multipart) content;
	                        for (int i = 0; i < multiPart.getCount(); i++) {
	                            BodyPart bodyPart = multiPart.getBodyPart(i);
	                            if (bodyPart.getContentType().contains("text/plain")) {
	                                String otp = (String) bodyPart.getContent(); // Extract the OTP from the email content
	                                System.out.println("OTP: " + otp);
	                            }
	                        }
	                    }
	                }
	            }

	            inbox.close(false);
	            store.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}
