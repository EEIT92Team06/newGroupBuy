package registry.model;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Gmail_TLS {

	public static void main(String[] args) {

		// 使用之前必須先安裝javamail api
		// 接著將mail.jar拷貝到%JAVA_HOME%\jre\lib\ext目錄下
		// 再把jar檔加進專案
		// 設定下列資訊
		// https://support.google.com/mail/answer/7104828?hl=en&visit_id=1-636265424040596537-1524597440&rd=3
		String host = "smtp.gmail.com";// smtp為網際網路中大部分使用者的寄信標準協定
		int port = 587;
		final String userName = "eeit9204@gmail.com";
		final String password = "sa123456";
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.port", port);
		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}
		});
		// 信件內容
		String address = "eeit9204@gmail.com";
		String personal = "GroupBuy團購網";
		String recipients = "eeit9220@gmail.com";
		String subject = "這是主旨";
		String text = "Dear xxx!!這是文字內容";
		String context = "This is a test <h1>GroupBuy用戶您好!!請點擊下列連結開通帳號</h1>"
				+ "<a href='http://localhost:8080/GroupBuy/secure/index.jsp'><h2>點此開通帳號</h2></a>";
		String type = "text/html; charset=UTF-8";
		try {
			Message message = new MimeMessage(session);
			try {
				message.setFrom(new InternetAddress(address, personal));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			// 設定收件人
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipients));
			// 主旨
			message.setSubject(subject);
			// 內容
			message.setText(text);
			// 增加附件
			// BodyPart messageBodyPart = new MimeBodyPart();
			// DataSource source = new FileDataSource(affix);
			// 增加附件的内容
			// messageBodyPart.setDataHandler(new DataHandler(source));
			// 附件標題
			// 使用base64編碼,讓傳送過程內容不會變亂碼
			// sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
			// messageBodyPart.setFileName(MimeUtility.encodeText(filename,"BIG5","B"));
			// multipart.addBodyPart(messageBodyPart);
			message.setContent(context, type);

			Transport transport = session.getTransport("smtp");
			transport.connect(host, port, userName, password);
			Transport.send(message);
			System.out.println("寄送email結束.");
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
