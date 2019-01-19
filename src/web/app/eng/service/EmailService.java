package web.app.eng.service;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import web.app.eng.dto.Post;
import web.app.eng.dto.User;

public class EmailService {
	
	private static String getIPv4Address(String networkInterfaceName) throws SocketException {
		Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
		for (NetworkInterface networkInterface : Collections.list(networkInterfaces)) {
			if (networkInterface.getName().equals(networkInterfaceName)) {
				Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
				for (InetAddress inetAddress : Collections.list(inetAddresses)) {
					if (inetAddress instanceof Inet4Address) {
						return inetAddress.getHostAddress();
					}
				}
			}
		}
		return "";
	}
	
	public static void sendEmail(String toAddress, String title, String content) throws MessagingException {
		final Properties properties = new Properties();
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.auth", "true");
		
		properties.put("mail.user", "unswbook.server@gmail.com");
		properties.put("mail.password", "zaq12345");
		Authenticator authenticator = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				String user     = properties.getProperty("mail.user");
				String password = properties.getProperty("mail.password");
				return new PasswordAuthentication(user, password);
			}
		};
		
		Session session = Session.getInstance(properties, authenticator);
		MimeMessage mimeMessage = new MimeMessage(session);
		InternetAddress from = new InternetAddress(properties.getProperty("mail.user"));
		mimeMessage.setFrom(from);
		InternetAddress to = new InternetAddress(toAddress);
		mimeMessage.setRecipient(RecipientType.TO, to);
		mimeMessage.setSubject(title);
		mimeMessage.setContent(content, "text/html;charset=UTF-8");
		Transport.send(mimeMessage);
	}
	
	public static void sendVerificationEmail(User user) throws MessagingException, SocketException {
		String serverIPv4Address = getIPv4Address("wlan0");
		String to = user.getEmail();
		String title = "UNSWBook Account: Email address verification";
		String content = "<p>" +
				"This is an automatically generated email from UNSWBook.<br/>" +
				"<br/>" +
				"--------------------------------------------------<br/>" +
				"Hi " + user.getFirstname() + " " + user.getSurname() + ",<br/>" +
				"<br/>" +
				"Thank you for registering for a UNSWBook Account.<br/>" +
				"<br/>" +
				"Help us secure your account by verifying your email.<br/>" +
				"<a href=\"http://" + serverIPv4Address + ":8080/Ass2SocialMedia/userControl?action=confirm" + "&username=" + user.getUsername() + "\" target=\"_top\">" +
				"Confirm Email Address" +
				"</a><br/>" +
				"<br/>" +
				"If you do not know why you have received this email, please delete this email.<br/>" +
				"<br/>" +
				"--------------------------------------------------<br/>" +
				"You cannot reply to this email address." +
				"</p>";
		sendEmail(to, title, content);
	}
	
	public static void sendFriendRequest(User user, String username, String email) throws MessagingException, SocketException {
		String serverIPv4Address = getIPv4Address("wlan0");
		String to = email;
		String title = user.getFirstname() + " " + user.getSurname() + " sent you a friend request on UNSWBook";
		String content = "<p>" +
				"This is an automatically generated email from UNSWBook.<br/>" +
				"<br/>" +
				"--------------------------------------------------<br/>" +
				"<br/>" +
				user.getFirstname() + " " + user.getSurname() + " sent you a friend request on UNSWBook.<br/>" +
				"<a href=\"http://" + serverIPv4Address + ":8080/Ass2SocialMedia/userControl?action=acceptFriend" + 
				"&subject=" + user.getUsername() + "&object1=" + username + "\" target=\"_top\">" +
				"Accept Friend Request" +
				"</a><br/>" +
				"<br/>" +
				"If you do not know why you have received this email, please delete this email.<br/>" +
				"<br/>" +
				"--------------------------------------------------<br/>" +
				"You cannot reply to this email address." +
				"</p>";
		sendEmail(to, title, content);
	}
	
	public static void sendBullyingNotification(Post post, List<String> bullyingKeywords) throws MessagingException, SocketException {
		String to = "unswbook.server@gmail.com";
		String title = post.getPoster() + "'s post contains reference to bullying";
		String keywords = "";
		for (String bullyingKeyword : bullyingKeywords) {
			keywords += (bullyingKeyword + ", ");
		}
		keywords = keywords.substring(0, keywords.length() - 2);
		String content = "<p>" +
				"This is an automatically generated email from UNSWBook.<br/>" +
				"<br/>" +
				"--------------------------------------------------<br/>" +
				"<br/>" +
				post.getPoster() + "'s post contains reference to bullying.<br/>" +
				"<br/>" +
				"Keywords:<br/>" +
				keywords + "<br/>" +
				"<br/>" +
				"If you do not know why you have received this email, please delete this email.<br/>" +
				"<br/>" +
				"--------------------------------------------------<br/>" +
				"You cannot reply to this email address." +
				"</p>";
		sendEmail(to, title, content);
	}
	
}
