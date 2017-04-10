package wish.model.dao;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import member.model.MemberBean;
import spring.setters.WishJavaConfiguration;
import wish.model.WishMsgBean;
import wish.model.WishMsgDAO;

public class WishMsgDAOHibernate implements WishMsgDAO {

	private SessionFactory sessionFactory;
	public WishMsgDAOHibernate(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	private static final String SELECT_BY_WISHMSG_NO="select*from wishMsg where wishMsg_No=?";
	@Override
	public WishMsgBean select(int wishMsgNo) {
		return this.getSession().get(WishMsgBean.class, wishMsgNo);
	}

	private static final String SELECT_BY_WISH_NO="select w.wishMsg_No,w.wish_No,w.member_No,w.wishMsg_Content,m.member_NickName,m.member_Pic from member m join wishMsg w on m.member_No = w.member_No where wish_No= :wishNo order by wishMsg_No ASC";
	@Override
	public List<Object[]> selectByWishNo(int wishNo) {
		Query query = this.getSession().createNativeQuery(SELECT_BY_WISH_NO);
		query.setParameter("wishNo", wishNo);
		return query.getResultList();
	}

	private static final String INSERT = "insert into wishMsg(wish_No,member_No,wishMsg_Content) values (?,?,?)";
	@Override
	public Boolean insert(WishMsgBean bean) {
		if(bean!=null){
			WishMsgBean temp = this.select(bean.getWishMsgNo());
			if(temp==null){
				this.getSession().save(bean);
				return true;
			}
		}
		return false;
	}

	private static final String UPDATE="update wishMsg set wishMsg_Content=?  where wishMsg_No=?";
	@Override
	public WishMsgBean update(WishMsgBean bean) {
		if(bean!=null){
			WishMsgBean temp = this.select(bean.getWishMsgNo());
			if(temp!=null){
				temp.setWishMsgContent(bean.getWishMsgContent());
			}
			return temp;
		}
		return null;
	}

	private static final String DELETE="DELETE FROM wishMsg WHERE wishMsg_No = ?";
	@Override
	public Boolean delete(int wishMsgNo) {
		WishMsgBean bean = this.select(wishMsgNo);
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
          WishMsgDAOHibernate wishMsgDAOHibernate = (WishMsgDAOHibernate)context.getBean("wishMsgDAOHibernate");
          
          WishMsgBean bean = new WishMsgBean();
          
          bean.setWishMsgNo(5);
          
          bean.setMemberNo(1);
          bean.setWishMsgContent("QQQQQQ");
          bean.setWishNo(1);
          List<Object[]> xxx = wishMsgDAOHibernate.selectByWishNo(1);
          
          for(Object[] objArr : xxx){
  			for(int i=0;i<objArr.length;i++){
  				System.out.println(objArr[i]);
  			}
  		}
          sessionFactory.getCurrentSession().getTransaction().commit();
          ((ConfigurableApplicationContext)context).close();
	}

}
