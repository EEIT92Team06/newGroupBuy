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
import wish.model.WishPictureBean;
import wish.model.WishPictureDAO;
import wish.model.WishPoolBean;

public class WishPictureDAOHibernate implements WishPictureDAO {
	private SessionFactory sessionFactory;
	public WishPictureDAOHibernate(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	private static final String SELECT_BY_WISHPICNO="select*from wishPicture where wishPicture_No=?";
	@Override
	public WishPictureBean select(int wishPicNo) {
		return this.getSession().get(WishPictureBean.class, wishPicNo);
	}

	private static final String SELECT_BY_WISH_NO="select*from wishPicture where wish_No= :wishNo";
	@Override
	public List<Object[]> selectByWishNo(int wishNo) {
		Query query = this.getSession().createNativeQuery(SELECT_BY_WISH_NO);
		query.setParameter("wishNo", wishNo);
		return query.getResultList();
	}

	public static final String INSERT = "insert into wishPicture (wish_No,wishPicture) values(?,?)";
	@Override
	public Boolean insert(WishPictureBean bean) {
		if(bean!=null){
			WishPictureBean temp = this.select(bean.getWishPictureNo());
			if(temp==null){
				this.getSession().save(bean);
				return true;
			}
		}
		return false;
	}

	public static final String UPDATE="update wishPicture set wishPicture=? where wishPicture_No=?";
	@Override
	public WishPictureBean update(WishPictureBean bean) {
		if(bean!=null){
			WishPictureBean temp = this.select(bean.getWishPictureNo());
			if(temp!=null){
				temp.setWishPicture(bean.getWishPicture());
			}
			return temp;
		}
		return null;
	}

	public static final String DELETE="DELETE FROM wishPicture WHERE wishPicture_No = ?";
	@Override
	public Boolean delete(int wishPicNo) {
		WishPictureBean bean = this.select(wishPicNo);
		if(bean!=null){
			this.getSession().delete(bean);
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(WishJavaConfiguration.class);
		SessionFactory sessionFactory = (SessionFactory)context.getBean("sessionFactory");
		sessionFactory.getCurrentSession().beginTransaction();
		WishPictureDAOHibernate dao = (WishPictureDAOHibernate)context.getBean("wishPictureDAOHibernate");
	
//		WishPictureBean bean = new WishPictureBean(); 
//		bean.setWishPictureNo(3);
//		bean.setWishNo(1);
//		bean.setWishPicture("ooo.jpg");
		List<Object[]> xxx = dao.selectByWishNo(2);
		for(Object[] objArr : xxx){
  			for(int i=0;i<objArr.length;i++){
  				System.out.println(objArr[i]);
  			}
  		}
		
		
		
		sessionFactory.getCurrentSession().getTransaction().commit();
		((ConfigurableApplicationContext)context).close();
	}

}
