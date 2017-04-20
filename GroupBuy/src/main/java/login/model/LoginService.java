package login.model;

import java.sql.Timestamp;
import java.util.Date;

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
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.cfg.xml");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		sessionFactory.getCurrentSession().beginTransaction();
		LoginService loginService = (LoginService) context.getBean("loginService");

//		boolean xxx = loginService.checkStatus(5);
//		System.out.println("xxx : "+ xxx);
		
		String xxx = loginService.AfterBanTime(4);
		System.out.println("xxx : " + xxx);
		
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
	
	public boolean checkStatus(int memberNo){
		int memberStatus = (int)loginDAO.selectMemberStatus(memberNo);
		if(memberStatus == 9103){
			Timestamp now = new Timestamp(new Date().getTime());
			Timestamp select = (Timestamp)loginDAO.selectban(memberNo);
			return now.after(select);
		}else{
			return true;
		}
	}
	
	public String AfterBanTime(int memberNo){
		int memberStatus = (int)loginDAO.selectMemberStatus(memberNo);
		if(memberStatus == 9103){
			Timestamp now = new Timestamp(new Date().getTime());
			Timestamp select = (Timestamp)loginDAO.selectban(memberNo);
			if(now.after(select)){
				loginDAO.delete(memberNo);
				loginDAO.update(memberNo);
				return "成功改回正常狀態";
			}
			return "水桶時間尚未結束";
		}else{
			return "不是水桶會員";	
		}
		
	}
	
	public Timestamp selectban(int memberNo){
			Timestamp select = (Timestamp)loginDAO.selectban(memberNo);
            return select;
	}
	
}
