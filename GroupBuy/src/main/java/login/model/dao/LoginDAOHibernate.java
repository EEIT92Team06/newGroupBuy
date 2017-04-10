package login.model.dao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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

}
