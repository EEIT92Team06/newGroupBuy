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
		List<WishPictureBean> list=null;
		List<Object[]> results = wishPictureDAO.selectByWishNo(wishNo);
        list = new ArrayList<WishPictureBean>();
        for(Object[] result : results){
        	 WishPictureBean bean = new WishPictureBean();
        	 bean.setWishPictureNo(Integer.parseInt(result[0].toString()));
        	 bean.setWishNo(Integer.parseInt(result[1].toString()));
        	 bean.setWishPicture(result[2].toString());
        	 
        	 list.add(bean);
        }
		return list;	
	}
	
	public Boolean insertPic(WishPictureBean bean){
		if(bean!=null){
			wishPictureDAO.insert(bean);
			return true;
		}
		return false;
	}
	
	public static void main (String[] arg){
		ApplicationContext context = new AnnotationConfigApplicationContext(WishJavaConfiguration.class);
		SessionFactory sessionFactory = (SessionFactory)context.getBean("sessionFactory");
		sessionFactory.getCurrentSession().beginTransaction();
		WishPictureService wishPictureService = (WishPictureService)context.getBean("wishPictureService");
		
		WishPictureBean bean = new WishPictureBean();
//		bean.setWishNo(1);
//		bean.setWishPicture("QQ0.jpg");
		List<WishPictureBean> xxx = wishPictureService.getWishPic(16);
		System.out.println(xxx);
		
		sessionFactory.getCurrentSession().getTransaction().commit();
		((ConfigurableApplicationContext)context).close();
	}
	
}
