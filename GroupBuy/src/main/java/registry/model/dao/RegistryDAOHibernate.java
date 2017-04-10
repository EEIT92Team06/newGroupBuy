package registry.model.dao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import login.model.MemberBean;
import registry.model.RegistryDAO;

public class RegistryDAOHibernate implements RegistryDAO {
	private SessionFactory sessionFactory;

	public RegistryDAOHibernate(SessionFactory sessionFactory) {
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
			System.out.println(memberAccount);
			return memberBean;
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public MemberBean insertMember(MemberBean memberBean) {
		String memberAccount=memberBean.getMemberAccount();
		MemberBean memberBean1=this.selectByAccount(memberAccount);
		if(memberBean1==null){
			Session session = sessionFactory.getCurrentSession();
			session.save(memberBean);
			return memberBean;
		}
		
		return null;
	}
	@Override
	public MemberBean updateMemberStatus(MemberBean memberBean){
		MemberBean temp=null;
		if(memberBean!=null){
			Session session=sessionFactory.getCurrentSession();
			temp=(MemberBean)session.get(MemberBean.class, memberBean.getMemberNo());
		    temp.setMemberStatus(9101);		    
		}
		return temp;
	}

}
