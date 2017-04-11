package wish.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import spring.setters.WishJavaConfiguration;

public class WishMsgService {
	private WishMsgDAO wishMsgDAO;
	
	public WishMsgService(WishMsgDAO wishMsgDAO) {
		this.wishMsgDAO = wishMsgDAO;
	}
	
	public WishMsgBean selectByMsgNo(int MsgNo){
		WishMsgBean result = wishMsgDAO.select(MsgNo);
		if(result!=null){
			return result;
		}
		return null;
	}

	public List<WishMsgBean> getWishMsg(int wishNo){
		List<WishMsgBean> list=null;
		List<Object[]> results = wishMsgDAO.selectByWishNo(wishNo);
        list = new ArrayList<WishMsgBean>();
        for(Object[] result : results){
        	WishMsgBean bean = new WishMsgBean();
        	 bean.setWishMsgNo(Integer.parseInt(result[0].toString()));
        	 bean.setWishNo(Integer.parseInt(result[1].toString()));
        	 bean.setMemberNo(Integer.parseInt(result[2].toString()));
        	 bean.setWishMsgContent(result[3].toString());
        	 bean.setNickName(result[4].toString());
        	 bean.setMemberPic(result[5].toString());
        	 list.add(bean);
        }
		return list;		
	}
	
	public Boolean insert(WishMsgBean bean){
		Boolean result = false;
		if(bean!=null){
			result = wishMsgDAO.insert(bean);
		}
		return result;
	}
	
	public WishMsgBean update(WishMsgBean bean){
		WishMsgBean result = null;
		if(bean!=null){
			result = wishMsgDAO.update(bean);
		}
		return result;
	}
	
	public Boolean delete(WishMsgBean bean){
		boolean result = false;
		if(bean!=null){
		    result = wishMsgDAO.delete(bean.getWishMsgNo());	
		}
		return result;
	}
	
	public static void main(String[] arg){
		ApplicationContext context = new AnnotationConfigApplicationContext(WishJavaConfiguration.class);
		SessionFactory sessionFactory = (SessionFactory)context.getBean("sessionFactory");
		sessionFactory.getCurrentSession().beginTransaction();
		WishMsgService wishMsgService = (WishMsgService)context.getBean("wishMsgService");
//		WishMsgBean bean = new WishMsgBean();
//		bean.setWishMsgNo(6);
//		bean.setWishNo(1);
//		bean.setMemberNo(1);
//		bean.setWishMsgContent("XXXXXXXXx");
		List<WishMsgBean> xxx = wishMsgService.getWishMsg(10);
		System.out.println(xxx);
		
		sessionFactory.getCurrentSession().getTransaction().commit();
		((ConfigurableApplicationContext)context).close();
	}
}
