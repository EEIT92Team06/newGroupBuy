package eeit9212.model;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class OrderInfoService {
	
		private OrderInfoDAO orderInfoDAO;
		private OrderInfoDetailsDAO orderInfoDetailsDAO;
		
		public OrderInfoService(OrderInfoDAO orderInfoDAO, OrderInfoDetailsDAO orderInfoDetailsDAO) {
			this.orderInfoDAO = orderInfoDAO;
			this.orderInfoDetailsDAO = orderInfoDetailsDAO;
			
		}
		
		public static void main(String args[]){
		
			ApplicationContext context = new ClassPathXmlApplicationContext("eeit9212/beansHibernate.xml");
			SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
			sessionFactory.getCurrentSession().beginTransaction();
			OrderInfoService orderInfoService = (OrderInfoService) context.getBean("orderInfoService");
			System.out.println(orderInfoService.selectMyOrderInfoByNo(10));
			 OrderInfoBean insert=orderInfoService.insertAndUpdateTransfer(10,1102,"0913246578","aaaaa","asasasas");
			 System.out.println("insertOrderInfoAfterSuccess="+insert);
			 System.out.println(orderInfoService.selectMyOrderInfoByNo(10));
			 
			List<OrderInfoBean> select = orderInfoService.selectMyGroupOrderInfo(1);
			System.out.println(select);
			List<OrderInfoDetailsBean> select1 = orderInfoService.selectOrderInfoDetails(1);
			System.out.println(select1);
			OrderInfoDetailsBean select2=orderInfoService.selectOneOrderInfoDetails(1, 6);
			System.out.println(select2);
//			List<GroupInfoDetailsBean> select3=groupInfoService.selectGroupInfoDetail(1);
//			System.out.println(select3);
			sessionFactory.getCurrentSession().getTransaction().commit();
			((ConfigurableApplicationContext) context).close();
		}
		public int updatePackageNo(String packageNo,int orderInfoNo){
			int result=-1;
			result=orderInfoDAO.updatePackageNo(packageNo, orderInfoNo);
			return result;		
		}
		public int updateOrderInfoStatusByOrderStatusNo(int groupInfoNo,int orderInfoStatusNo,int whereOrderInfoStatusNo){
			int result=-1;
			result=orderInfoDAO.updateOrderInfoStatusByOrderStatusNo(groupInfoNo, orderInfoStatusNo, whereOrderInfoStatusNo);
			return result;
		}
		public int updateOrderInfoStatusByGroupInfoNo(int groupInfoNo,int orderInfoStatusNo){
			int result=-1;
			result=orderInfoDAO.updateOrderInfoStatusByGroupInfoNo(groupInfoNo, orderInfoStatusNo);
			return result;
		}
		public OrderInfoBean selectMyOrderInfoByNo(int orderInfoNo) {
			OrderInfoBean result=null;
			result=orderInfoDAO.selectMyOrderInfoByNo(orderInfoNo);	
			return result;
		}
		public OrderInfoBean insertAndUpdateTransfer(int orderInfoNo,int orderInfoStatusNo,String orderInfoAfterSuccessPhone,
				String orderInfoAfterSuccessDestination,String orderInfoAfterSuccessBankAccount){
			OrderInfoBean result=null;
			int insert=orderInfoDAO.insertOrderInfoAfterSuccess(orderInfoNo, orderInfoAfterSuccessPhone, orderInfoAfterSuccessDestination, orderInfoAfterSuccessBankAccount);
			if(insert!=-1){
				result=orderInfoDAO.updateOrderInfoStatus(orderInfoStatusNo, orderInfoNo);
			}		
			return result;
		}
		
		
		public OrderInfoBean updateOrderInfoStatus(int orderInfoStatusNo,int orderInfoNo){
			OrderInfoBean result=null;
			result=orderInfoDAO.updateOrderInfoStatus(orderInfoStatusNo, orderInfoNo);
			return result;		
		}
		public double selectTotalPrice(int orderInfoNo) {
			double result=-1;
			result=orderInfoDAO.selectTotalPrice(orderInfoNo);
			return result;
		}
		
		public List<OrderInfoBean> selectMyGroupOrderInfo(int groupInfoNo){
			List<OrderInfoBean> result = null;
			result=orderInfoDAO.selectMyGroupOrderInfo(groupInfoNo);
			return result;
		}
		public List<OrderInfoDetailsBean> selectOrderInfoDetails(int orderInfoNo){
			List<OrderInfoDetailsBean> result = null;
			result  = orderInfoDetailsDAO.selectOrderInfoDetails(orderInfoNo);
			
			return result;
		}
	
		public OrderInfoDetailsBean selectOneOrderInfoDetails(int orderInfoNo,int groupInfoDetailsNo) {
			OrderInfoDetailsBean result = null;
			result=orderInfoDetailsDAO.selectOneOrderInfoDetails(orderInfoNo, groupInfoDetailsNo);
			
			return result;
		}
}
