package login.model;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LoginService {
	private LoginDAO loginDAO;

	public LoginService(LoginDAO loginDAO) {
		this.loginDAO = loginDAO;
	}

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		sessionFactory.getCurrentSession().beginTransaction();
		LoginService loginService = (LoginService) context.getBean("loginService");
		MemberBean memberBean = loginService.login("eeit9211@gmail.com", "sa123456");
		System.out.println("memberBean=" + memberBean);
		sessionFactory.getCurrentSession().getTransaction().commit();

		((ConfigurableApplicationContext) context).close();
	}

	public MemberBean login(String memberAccount, String memberPassword) {
		MemberBean memberBean = loginDAO.selectByAccount(memberAccount);
		if (memberBean != null) {
			if (memberPassword.equals(memberBean.getMemberPassword())) {
				return memberBean;
			}
		}
		return null;
	}
	public int checkRegistryStatus(String memberAccount){
		int statusNum=9100;
		if(memberAccount!=null){
			MemberBean memberBean=loginDAO.selectByAccount(memberAccount);
			statusNum=memberBean.getMemberStatus();
			if(statusNum>=9101){
				return statusNum;
			}
		}
		return statusNum;
	}
}
