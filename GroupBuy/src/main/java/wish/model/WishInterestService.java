package wish.model;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import spring.setters.WishJavaConfiguration;

public class WishInterestService {
    private WishInterestDAO wishInterestDAO;
	public WishInterestService(WishInterestDAO wishInterestDAO) {
		this.wishInterestDAO = wishInterestDAO;
	}
    public int likeCount(int wishNo){
    	int count = wishInterestDAO.count(wishNo);
    	return count;
    }
    
    public boolean likeInsert(WishInterestBean bean){
    	Boolean result=false;
    	if(bean!=null){
    		result = wishInterestDAO.insert(bean);
    	}
    	return result;
    }
    
    public boolean likeDelete(WishInterestBean bean){
    	Boolean result = false;
    	if(bean!=null){
    		result = wishInterestDAO.delete(bean);
    	}
    	return result;
    }
    
    public boolean likeOrNot(int wishNo,int memberNo){
    	WishInterestBean result = wishInterestDAO.selectByWM(wishNo, memberNo);
    	if(result!=null){
    		return true;
    	}
    	return false;
    }
//	public static void main (String[] arg){
//		ApplicationContext context = new AnnotationConfigApplicationContext(WishJavaConfiguration.class);
//		SessionFactory sessionFactory = (SessionFactory)context.getBean("sessionFactory");
//		sessionFactory.getCurrentSession().beginTransaction();
//		WishInterestService wishInterestService = (WishInterestService)context.getBean("wishInterestService");
//		
//		WishInterestBean bean = new WishInterestBean();
//		bean.setWishNo(1);
//		bean.setMemberNo(1);
//		boolean xxx = wishInterestService.likeOrNot(3, 2);
//		System.out.println(xxx);
//		
//		sessionFactory.getCurrentSession().getTransaction().commit();
//		((ConfigurableApplicationContext)context).close();
//	}
}
