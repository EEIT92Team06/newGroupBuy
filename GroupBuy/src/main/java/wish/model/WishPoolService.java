package wish.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import spring.setters.WishJavaConfiguration;

public class WishPoolService {
	private WishPoolDAO wishPoolDAO;
	public WishPoolService(WishPoolDAO wishPoolDAO) {
		this.wishPoolDAO = wishPoolDAO;
	}

	public List<WishPoolBean> select(WishPoolBean bean){//select wishPool table的所有資料
		List<WishPoolBean> result=null;
		if(bean!=null && bean.getWishNo()!=0){
			WishPoolBean temp = wishPoolDAO.select(bean.getWishNo());
			if(temp!=null){
				result = new ArrayList<WishPoolBean>();
				result.add(temp);
			}
		}else{
			result = wishPoolDAO.select();
		}
		return result;
	}
	
	public List<WishPoolBean> myWish(int memberNo){
		List<WishPoolBean> result = null;
		result = wishPoolDAO.selectMyWish(memberNo);
		if(result!=null){
			return result;
		}
		return null;
	}
	
	public Integer getWishNo(String cover){
		Integer wishNo = wishPoolDAO.selectByCover(cover);
		if(wishNo!=null){
			return wishNo;
		}
		return null;
	}
	
	public List<WishPoolBean> search(String titleKeyWord,int productType){
		List<WishPoolBean> result = null;
		if(productType==0){
			result = wishPoolDAO.selectForSearch(titleKeyWord);
		}else{
			result = wishPoolDAO.selectForSearch(titleKeyWord, productType);
		}
		return result;
	}
	
	public Boolean insert(WishPoolBean bean){
		Boolean result = false;
		if(bean!=null){
			result = wishPoolDAO.insert(bean);
		}
		return result;
	}
	
	public Boolean delete(int wishNo){
		if(wishNo!=0){
			wishPoolDAO.delete(wishNo);
			return true;
		}
		return false;
	}
	
	
//	public static void main(String[] arg){
//		ApplicationContext context = new AnnotationConfigApplicationContext(WishJavaConfiguration.class);
//		SessionFactory sessionFactory = (SessionFactory)context.getBean("sessionFactory");
//		sessionFactory.getCurrentSession().beginTransaction();
//		WishPoolService wishPoolService = (WishPoolService)context.getBean("wishPoolService");
//		
//		WishPoolBean bean = new WishPoolBean();
////		bean.setWishNo(7);
//        bean.setMemberNo(3);
//        bean.setProductType(1);
//        bean.setTitle("ABC");
//        bean.setProductName("xxx");
//        bean.setContent("123");
//        bean.setPrice(100);
//        bean.setSource("QQ");
//        bean.setCoverPic("xxx.jpg");
//		
//        
//		
//		sessionFactory.getCurrentSession().getTransaction().commit();
//		((ConfigurableApplicationContext)context).close();
//	}
	
}