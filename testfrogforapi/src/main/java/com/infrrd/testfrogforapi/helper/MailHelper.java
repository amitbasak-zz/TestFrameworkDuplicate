package com.infrrd.testfrogforapi.helper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailHelper {
	
	public static void mailer(String from, String password, List<String> toList, String subject, String body, List<String> attachDocumentList){

		final String pwrd = password;
		final String username = from;// change accordingly

		// Assuming you are sending email through relay.jangosmtp.net

		Properties props = new Properties();
		
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		// Get the Session object.
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, pwrd);
			}
		});

		try {
			// Create a default MimeMessage object.
			Message message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			for (String to : toList) {
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			}

			// Set Subject: header field
			message.setSubject(subject);

			// Message will be multi-part
			Multipart multipart = new MimeMultipart();

			// Now set the body content
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText(body);
			messageBodyPart.setContent(body, "text/plain");
			multipart.addBodyPart(messageBodyPart);

			// Also set the attachment part
			//List<String> attachDocList = Arrays.asList("report/SanityTestReport.csv", "test-output/emailable-report.html");
			
			for (String directoryPath : attachDocumentList) {
				BodyPart messageAttachmentPart = new MimeBodyPart();
				File file = new File(directoryPath);
				DataSource source = new FileDataSource(file);
				messageAttachmentPart.setDataHandler(new DataHandler(source));
				messageAttachmentPart.setFileName(directoryPath);
				multipart.addBodyPart(messageAttachmentPart);
			}

			// Set content for multi-part message
			message.setContent(multipart);

			// Send message
			Transport.send(message);

			System.out.println("Sent message successfully....");
		
		} catch (Exception e) {
			System.out.println("Not able to send email");
		}

	}

}
