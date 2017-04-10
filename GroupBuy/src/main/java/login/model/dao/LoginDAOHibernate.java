package login.model.dao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import login.model.LoginDAO;
import login.model.MemberBean;

public class LoginDAOHibernate implements LoginDAO {
	private SessionFactory sessionFactory;

	public LoginDAOHibernate(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.cfg.xml");
		SessionFactory sessionFactory = (SessionFactory)context.getBean("sessionFactory");
		sessionFactory.getCurrentSession().beginTransaction();
		LoginDAOHibernate loginDAOHibernate = (LoginDAOHibernate) context.getBean("loginDAOHibernate");
//		Timestamp ooo = new Timestamp(new Date().getTime());
//		Timestamp xx = (Timestamp)loginDAOHibernate.selectban(4);
//		System.out.println("xx : " + xx);
//		boolean ddd = ooo.after(xx);
//		System.out.println("dd : " + ddd);
		
//		int xxx = (int)loginDAOHibernate.selectMemberStatus(4);
//		System.out.println("xxx : "+ xxx);

		System.out.println(loginDAOHibernate.delete(2));
		System.out.println(loginDAOHibernate.update(2));
		
		sessionFactory.getCurrentSession().getTransaction().commit();
		((ConfigurableApplicationContext) context).close();
	}
	
	private final static String SELECT_BY_ACCOUNT = "from MemberBean where memberAccount=?";

	@Override
	public MemberBean selectByAccount(String memberAccount) {
		MemberBean memberBean = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery(SELECT_BY_ACCOUNT, MemberBean.class);
			query.setParameter(0, memberAccount);
			memberBean = (MemberBean) query.getSingleResult();

		} catch (NoResultException e) {
			return memberBean;
		}
		return memberBean;
	}
	
	private static String SELECT_BY_No = " select ban_Time from ban where member_No = ?";
	@Override
	public Object selectban(int memberNo) {
		Session session = sessionFactory.getCurrentSession();
		NativeQuery query = session.createNativeQuery(SELECT_BY_No);
		query.setParameter(1, memberNo);
		return query.getSingleResult();
	}
	private static String SELECT_MEMBERSTATUS = 
			" select member_Status from member where member_No = ?";
	public Object selectMemberStatus(int memberNo){
		Session session = sessionFactory.getCurrentSession();
		NativeQuery query = session.createNativeQuery(SELECT_MEMBERSTATUS);
		query.setParameter(1, memberNo);
		return query.getSingleResult();
	}
	
	private static String DELETEBAN = 
			" delete ban where member_No = ?";
	@Override
	public int delete(int memberNo){
		Session session = sessionFactory.getCurrentSession();
		NativeQuery query = session.createNativeQuery(DELETEBAN);
		query.setParameter(1, memberNo);
		return query.executeUpdate();
	}
	
	private static final String UPDATEMEMBERSTATUS = "update member set member_Status=9101 where member_No = ?";
	@Override
	public int update(int memberNo) {
		Session session = sessionFactory.getCurrentSession();
		NativeQuery query = session.createNativeQuery(UPDATEMEMBERSTATUS);
		query.setParameter(1, memberNo);
		return query.executeUpdate();
	}
	 
}
