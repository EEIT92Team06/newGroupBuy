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

import login.model.MemberBean;

public class RegistryService {
	private RegistryDAO registryDAO;

	public RegistryService(RegistryDAO registryDAO) {
		this.registryDAO = registryDAO;
	}

	public MemberBean addMember(MemberBean memberBean) {
		MemberBean memberBean1 = null;
		if (memberBean != null) {
			memberBean1 = registryDAO.insertMember(memberBean);
		}
		return memberBean1;
	}
	public MemberBean selectMemberAccount(String memberAccount){
		MemberBean memberBean=null;
		if(memberAccount!=null){
			memberBean=registryDAO.selectByAccount(memberAccount);
		}
		return memberBean;
	}
	public MemberBean updateMemberStatus(MemberBean memberBean){
		MemberBean temp=null;
		if(memberBean!=null){
			temp=registryDAO.updateMemberStatus(memberBean);
		}
		
		return temp;
	}
	public int sendRegistryMail(String memberAccount){
		int sendMailNum=0;
		String host = "smtp.gmail.com";
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
		String recipients = memberAccount;
		String subject = "這是主旨";
		String text = "Dear xxx!!這是文字內容";
		String account="";
		String context = "This is a test <h1>GroupBuy用戶您好!!請點擊下列連結開通帳號</h1>"
				+ "<a href='http://localhost:8080/GroupBuy/finishedRegistryServlet.do?memberAccount="
				+recipients
				+ "'>"
				+ "<h2>點此開通帳號</h2></a>";
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
			message.setSubject(subject);
			message.setText(text);
			message.setContent(context, type);

			Transport transport = session.getTransport("smtp");
			transport.connect(host, port, userName, password);
			Transport.send(message);
			sendMailNum=1;
			System.out.println("寄送email結束.");
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return sendMailNum;
	}
	public int updateStatus(String memberAccount){
		int updateNum=0;
		if(memberAccount!=null){
			MemberBean memberBean=registryDAO.selectByAccount(memberAccount);
			MemberBean temp=registryDAO.updateMemberStatus(memberBean);
			if(temp!=null){
				updateNum=1;
			}
		}
		return updateNum;
	}
}
