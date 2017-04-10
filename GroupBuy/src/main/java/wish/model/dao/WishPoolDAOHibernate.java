package wish.model.dao;


import java.util.List;


import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import spring.setters.WishJavaConfiguration;
import wish.model.WishPoolBean;
import wish.model.WishPoolDAO;

public class WishPoolDAOHibernate implements WishPoolDAO {
	private SessionFactory sessionFactory;

	public WishPoolDAOHibernate(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	private static final String SELECT_BY_WISH_NO = "select * from wish where wish_No=?";
	@Override
	public WishPoolBean select(int wishNo) {
		return this.getSession().get(WishPoolBean.class, wishNo);
	}

	private static final String SELECT_ALL = "select * from wish order by wish_No DESC";
	@Override
	public List<WishPoolBean> select() {
		return this.getSession().createQuery("from WishPoolBean order by wish_No desc",WishPoolBean.class).getResultList();
	}

	private static final String SELECT_BY_MEMBER_NO = "select * from wish where member_No=?";
	@Override
	public List<WishPoolBean> selectMyWish(int memberNo) {
		Query query = this.getSession().createQuery("from WishPoolBean where member_No=?",WishPoolBean.class);
		query.setParameter(0, memberNo);
		return query.getResultList();
	}

	private static final String GET_WISH_NO_BY_COVER = "select wish_No from wish where wish_CoverPic=?";

	@Override
	public Integer selectByCover(String cover) {
		Query query = this.getSession().createQuery("select wish.wishNo from WishPoolBean as wish where wish.coverPic=?");
		query.setParameter(0, cover);
		return (Integer) query.getSingleResult();
	}

	private static final String SEARCH_ALL = "select * from wish where wish_Title like ? order by wish_No DESC";
    @Override
	public List<WishPoolBean> selectForSearch(String titleKeyWord) {
		Query query = this.getSession().createQuery("from WishPoolBean where title like ? order by wishNo desc",WishPoolBean.class);
		query.setParameter(0, "%"+titleKeyWord+"%");
    	return query.getResultList();
	}

	private static final String SEARCH_WITH_TYPE = "select * from wish where wish_Title like ? and productType_No=? order by wish_No DESC;";
    @Override
	public List<WishPoolBean> selectForSearch(String titleKeyWord, int productType) {
		Query query = this.getSession().createQuery("from WishPoolBean where title like ? and productType=? order by wishNo desc");
    	query.setParameter(0, "%"+titleKeyWord+"%");
    	query.setParameter(1, productType);
    	return query.getResultList();
	}

	private static final String INSERT = "insert into wish (member_No,productType_No,"
			+ "wish_Title,wish_ProductName,wish_Content,wish_ProuductPrice,wish_Source,wish_CoverPic)"
			+ " values (?,?,?,?,?,?,?,?)";
	@Override
	public Boolean insert(WishPoolBean bean) {
		if(bean!=null){
			WishPoolBean temp = this.select(bean.getWishNo());
			if(temp==null){
				this.getSession().save(bean);
				return true;
			}
		}
		return false;
	}

	public static final String DELETE = "DELETE FROM wish WHERE wish_No = ?";

	@Override
	public Boolean delete(int wishNo) {
		WishPoolBean bean = this.select(wishNo);
		if(bean!=null){
			this.getSession().delete(bean);
			return true;
		}
		return false;
	}

	private static final String UPDATE = "update wish set productType_No=?, wish_Title=?, wish_ProductName=?, wish_Content=?, wish_ProuductPrice=?, wish_Source=?,wish_CoverPic=? where wish_No=?";
	@Override
	public WishPoolBean update(WishPoolBean bean) {
		if(bean!=null){
			WishPoolBean temp = this.select(bean.getWishNo());
			if(temp!=null){
				temp.setProductType(bean.getProductType());
				temp.setTitle(bean.getTitle());
				temp.setProductName(bean.getProductName());
				temp.setContent(bean.getContent());
				temp.setPrice(bean.getPrice());
				temp.setSource(bean.getSource());
				temp.setCoverPic(bean.getCoverPic());
			}
			return temp;
		}
		return null;
	}

	public static void main(String[] arg) {
//		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
		ApplicationContext context = new AnnotationConfigApplicationContext(WishJavaConfiguration.class);
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		sessionFactory.getCurrentSession().beginTransaction();

		WishPoolDAOHibernate dao = (WishPoolDAOHibernate) context.getBean("wishPoolDAOHibernate");
		WishPoolBean select = dao.select(10);
		System.out.println(select);
//        WishPoolBean bean = new WishPoolBean();
//        bean.setWishNo(6);
//        bean.setMemberNo(3);
//        bean.setProductType(1);
//        bean.setTitle("ABC");
//        bean.setProductName("xxx");
//        bean.setContent("123");
//        bean.setPrice(100);
//        bean.setSource("QQ");
//        bean.setCoverPic("xxx.jpg");
////        WishPoolBean update = dao.update(bean);
////        System.out.println(update);
//        Boolean delete = dao.delete(6);
//        System.out.println(delete);
        
		sessionFactory.getCurrentSession().getTransaction().commit();
		// context.close();
		((ConfigurableApplicationContext) context).close();
	}

}