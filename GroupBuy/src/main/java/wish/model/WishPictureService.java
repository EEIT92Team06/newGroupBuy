package wish.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import spring.setters.WishJavaConfiguration;

public class WishPictureService {

	private WishPictureDAO wishPictureDAO;
	public WishPictureService(wish.model.WishPictureDAO wishPictureDAO) {
		this.wishPictureDAO = wishPictureDAO;
	}
	
	public List<WishPictureBean> getWishPic(int wishNo){
		List<WishPictureBean> result = wishPictureDAO.selectByWishNo(wishNo);
		if(result!=null){
			return result;
		}
		return null;
	}
	
	public Boolean insertPic(WishPictureBean bean){
		if(bean!=null){
			wishPictureDAO.insert(bean);
			return true;
		}
		return false;
	}
	
//	public static void main (String[] arg){
//		ApplicationContext context = new AnnotationConfigApplicationContext(WishJavaConfiguration.class);
//		SessionFactory sessionFactory = (SessionFactory)context.getBean("sessionFactory");
//		sessionFactory.getCurrentSession().beginTransaction();
//		WishPictureService wishPictureService = (WishPictureService)context.getBean("wishPictureService");
//		
//		WishPictureBean bean = new WishPictureBean();
//		bean.setWishNo(1);
//		bean.setWishPicture("QQ0.jpg");
//		Boolean xxx = wishPictureService.insertPic(bean);
//		System.out.println(xxx);
//		
//		sessionFactory.getCurrentSession().getTransaction().commit();
//		((ConfigurableApplicationContext)context).close();
//	}
	
}
