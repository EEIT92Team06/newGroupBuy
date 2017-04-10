package wish.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import spring.setters.WishJavaConfiguration;
import wish.model.WishInterestBean;
import wish.model.WishInterestDAO;

public class WishInterestDAOHibernate implements WishInterestDAO {
	private SessionFactory sessionFactory;
	public WishInterestDAOHibernate(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	private static final String SELECT_BY_WISH_NO="select * from wishInterestMember where wish_No = ?";
	@Override
	public List<WishInterestBean> select(int wishNo) {
		Query query = this.getSession().createQuery("from WishInterestBean where wish_No=?",WishInterestBean.class);
		query.setParameter(0, wishNo);
		return query.getResultList();
	}

	private static final String COUNT="select count(wish_No) as count from wishInterestMember where wish_No= :wishNo";
	@Override
	public int count(int wishNo) {
		Query query = this.getSession().createNativeQuery(COUNT);
		query.setParameter("wishNo", wishNo);
		return (int) query.getSingleResult();
	}

	private static final String INSERT="insert into wishInterestMember (wish_No,member_No) values (?,?)";
	@Override
	public Boolean insert(WishInterestBean bean) {
		if(bean!=null){
			WishInterestBean temp = this.selectByWM(bean.getWishNo(), bean.getMemberNo());
			if(temp==null){
				this.getSession().save(bean);
				return true;
			}
		}
		return false;
	}

	private static final String DELETE="DELETE FROM wishInterestMember WHERE (wish_No=? and member_No=?)";
	@Override
	public Boolean delete(WishInterestBean bean) {
		if(bean!=null){
			WishInterestBean temp = this.selectByWM(bean.getWishNo(), bean.getMemberNo());
			if(temp!=null){
				this.getSession().delete(temp);
				return true;
			}
		}
		return false;
	}
	
	private static final String SELECT_BY_WM_NO="select*from wishInterestMember where wish_No= :wishNo and member_No= :memberNo";
	@Override
	public WishInterestBean selectByWM(int wishNo,int memberNo){
		Query query = this.getSession().createNativeQuery(SELECT_BY_WM_NO,WishInterestBean.class);
		query.setParameter("wishNo", wishNo);
		query.setParameter("memberNo", memberNo);
		WishInterestBean result;
		try {
			result = (WishInterestBean) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
		return result;
	}

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(WishJavaConfiguration.class);
		SessionFactory sessionFactory = (SessionFactory)context.getBean("sessionFactory");
		sessionFactory.getCurrentSession().beginTransaction();
		WishInterestDAOHibernate dao = (WishInterestDAOHibernate)context.getBean("wishInterestDAOHibernate");
        WishInterestBean bean = new WishInterestBean();
		bean.setWishNo(1);
		bean.setMemberNo(1);
		Boolean xxx = dao.delete(bean);
		System.out.println(xxx);
		
		sessionFactory.getCurrentSession().getTransaction().commit();
		((ConfigurableApplicationContext)context).close();
	}

}
