package com.controller;

import java.util.*;


import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 * 
 * @author dell
 */
public class SendEmail {
	public String SendEmail(String subject, String to, String message) {
		String from = "bookberriesbaroda@gmail.com";
		try {
			Properties props = System.getProperties();
			// -- Attaching to default Session, or we could start a new one

			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");
			props.put("mail.smtp.auth", "true");
			// props.put("mail.smtp.debug", "true");
			// props.put("mail.smtp.socketFactory.port", 465);
			// props.put("mail.smtp.socketFactory.class",
			// "javax.net.ssl.SSLSocketFactory");
			// props.put("mail.smtp.socketFactory.fallback", "false");

			SMTPAuthenticator auth = new SMTPAuthenticator();
			Session session = Session.getInstance(props, auth);
			// -- Create a new message --
			MimeMessage msg = new MimeMessage(session);
			// -- Set the FROM and TO fields --
			msg.setFrom(new InternetAddress(from));
			msg.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to, false));
			// msg.setRecipients(Message.RecipientType.CC,
			// InternetAddress.parse(to, false));
			// -- We could include CC recipients too --

			// if (cc != null)
			// msg.setRecipients(Message.RecipientType.CC
			// ,InternetAddress.parse(cc, false));
			// -- Set the subject and body text --
			msg.setSubject(subject);
			message = "<body><table style=\"width:100%;background-color:rgba(0,0,0,0.05);\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tr style=\"background-color:#FF3333;\"><td><div style=\"display:inline-block;padding:10px 20px;\"><span style=\"font-family: 'Arial', 'Century Gothic', 'Trebuchet MS';font-weight:800;color:white;font-size: 40px;line-height:40px;\">book</span><br><span style=\"font-family: 'Arial', 'Century Gothic', 'Trebuchet MS';font-weight:300;color:white;font-size: 40px;line-height:40px;\">berries</span></div></td></tr><tr style=\"background-color:rgba(0,0,0,0.05);\"><td><div style=\"padding:20px;\"><p style=\"font-family:'Arial', 'Century Gothic', 'Trebuchet MS';font-size:16px;color:rgba(0,0,0,0.87);\">Hi,</p><p style=\"font-family:'Arial', 'Century Gothic', 'Trebuchet MS';font-size:16px;color:rgba(0,0,0,0.87);\">"
					+ message + "</p></div></td></tr></table></body>";
			msg.setContent(message, "text/html; charset=UTF-8");
			//msg.setText(message, "text/html; charset=UTF-8", "html");
			// -- Set some other header information --

			msg.setSentDate(new Date());
			// -- Send the message --

			Transport.send(msg);
			return "success";
			// return 0;
		} catch (Exception ex) {

			ex.printStackTrace();

			return ex.getMessage();
			// return -1;

		}
	}

	private class SMTPAuthenticator extends javax.mail.Authenticator {

		@Override
		public PasswordAuthentication getPasswordAuthentication() {

			String username = "bookberriesbaroda@gmail.com";

			String password = "bookberries281996";

			return new PasswordAuthentication(username, password);

		}

	}

}
