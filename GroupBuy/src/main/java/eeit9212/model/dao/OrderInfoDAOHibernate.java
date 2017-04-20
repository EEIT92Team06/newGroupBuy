package eeit9212.model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import eeit9212.model.OrderInfoAfterSuccessBean;
import eeit9212.model.OrderInfoBean;
import eeit9212.model.OrderInfoDAO;

public class OrderInfoDAOHibernate implements OrderInfoDAO {
	private SessionFactory sessionFactory;

	public OrderInfoDAOHibernate(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public static void main(String args[]) {

		ApplicationContext context = new ClassPathXmlApplicationContext("eeit9212/beansHibernate.xml");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		sessionFactory.getCurrentSession().beginTransaction();
		OrderInfoDAO orderInfoDAOJdbc = new OrderInfoDAOHibernate(sessionFactory);

		// System.out.println(orderInfoDAOJdbc.updateOrderInfoStatusByOrderStatusNo(1,
		// 1301, 1005));
		// System.out.println(orderInfoDAOJdbc.updateOrderInfoStatusByGroupInfoNo(15,
		// 1004));
		// System.out.println(orderInfoDAOJdbc.updatePackageNo("asasas", 6));
		 int
		 insert=orderInfoDAOJdbc.insertOrderInfoAfterSuccess(8,"0913246578","aaaaa","asasasas");
		 System.out.println("insertOrderInfoAfterSuccess="+insert);

		OrderInfoBean update = orderInfoDAOJdbc.updateOrderInfoStatus(1001, 5);
		System.out.println("update=" + update);

		System.out.println(orderInfoDAOJdbc.selectTotalPrice(1));
		System.out.println(orderInfoDAOJdbc.selectMyOrderInfoByNo(1));

		List<OrderInfoBean> select = orderInfoDAOJdbc.selectMyGroupOrderInfo(1);
		System.out.println(select);
		for (OrderInfoBean bean : select) {
			System.out.print(bean.getOrderInfoNo() + " ");
			System.out.print(bean.getMemberNo() + " ");
			System.out.print(bean.getMemberName() + " ");
			System.out.print(bean.getGroupAttendanceTotalSuccess() + " ");
			System.out.print(bean.getGroupAttendanceTotalQt() + " ");
			System.out.print(bean.getOrderInfoPriceTotal() + " ");
			System.out.print(bean.getOrderInfoStatusNo() + " ");
			System.out.print(bean.getOrderInfoStatus() + " ");
			System.out.print(bean.getOrderInfoAfterSuccessPackageNo() + " ");
			System.out.print(bean.getOrderInfoAfterSuccessPayTime() + " ");
			System.out.print(bean.getOrderInfoAfterSuccessPhone() + " ");
			System.out.print(bean.getOrderInfoAfterSuccessDestination() + " ");
			System.out.println(bean.getOrderInfoAfterSuccessBankAccount());
		}
		sessionFactory.getCurrentSession().getTransaction().commit();
		((ConfigurableApplicationContext) context).close();
	}

	public int updateOrderInfoStatusByOrderStatusNo(int groupInfoNo, int orderInfoStatusNo,
			int whereOrderInfoStatusNo) {

		String updateOrderInfoStatusByOrderStatusNo = "update orderInfo set orderInfoStatus_No=? where groupInfo_No=? and orderInfoStatus_No=?";
		int result = -1;
		NativeQuery<?> query = this.getSession().createNativeQuery(updateOrderInfoStatusByOrderStatusNo);
		query.setParameter(1, orderInfoStatusNo);
		query.setParameter(2, groupInfoNo);
		query.setParameter(3, whereOrderInfoStatusNo);
		result = query.executeUpdate();
		if (result == 0) {
			result = -1;
		}
		return result;
	}

	public int updateOrderInfoStatusByGroupInfoNo(int groupInfoNo, int orderInfoStatusNo) {

		String updateOrderInfoStatusByGroupInfoNo = "update orderInfo set orderInfoStatus_No=? where groupInfo_No=?";
		int result = -1;
		NativeQuery<?> query = this.getSession().createNativeQuery(updateOrderInfoStatusByGroupInfoNo);
		query.setParameter(1, orderInfoStatusNo);
		query.setParameter(2, groupInfoNo);
		result = query.executeUpdate();
		if (result == 0) {
			result = -1;
		}
		return result;
	}

	public int updatePackageNo(String packageNo, int orderInfoNo) {

		String updatePackageNo = "update orderInfoAfterSuccess set orderInfoAfterSuccess_PackageNo=? where orderInfo_No=?;";
		int result = -1;
		NativeQuery<?> query = this.getSession().createNativeQuery(updatePackageNo);
		query.setParameter(1, packageNo);
		query.setParameter(2, orderInfoNo);
		result = query.executeUpdate();
		if (result == 0) {
			result = -1;
		}

		return result;

	}

	public int insertOrderInfoAfterSuccess(int orderInfoNo, String orderInfoAfterSuccessPhone,
			String orderInfoAfterSuccessDestination, String orderInfoAfterSuccessBankAccount) {
		String insertOrderInfoAfterSuccess = "insert into orderInfoAfterSuccess values(?,null,getdate(),?,?,?)";
		System.out.println("是誰呼叫這方法的? " + orderInfoNo);
		int result = -1;
		if (this.getSession().get(OrderInfoAfterSuccessBean.class, orderInfoNo) == null) {
			NativeQuery<?> query = this.getSession().createNativeQuery(insertOrderInfoAfterSuccess);
			query.setParameter(1, orderInfoNo);
			query.setParameter(2, orderInfoAfterSuccessPhone);
			query.setParameter(3, orderInfoAfterSuccessDestination);
			query.setParameter(4, orderInfoAfterSuccessBankAccount);
			try {
				result = query.executeUpdate();
			} catch (Exception e) {
				result = -1;
			}
		}
		return result;
	}

	public OrderInfoBean selectMyOrderInfoByNo(int orderInfoNo) {

		String selectMyGroupOrderInfo = "from eeit9212.model.OrderInfoBean where orderInfo_No=?";
		OrderInfoBean result = null;
		Query<OrderInfoBean> query = this.getSession().createQuery(selectMyGroupOrderInfo);
		query.setParameter(0, orderInfoNo);
		try {
			result = query.getSingleResult();
		} catch (Exception e) {
			System.out.println("selectMyOrderInfoByNo查無資料");
		}
		return result;

	}

	public OrderInfoBean updateOrderInfoStatus(int orderInfoStatusNo, int orderInfoNo) {
		String updateOrderInfoStatus = "update orderInfo set orderInfoStatus_No=? where orderInfo_No=?";

		OrderInfoBean result = null;
		NativeQuery<?> query = this.getSession().createNativeQuery(updateOrderInfoStatus);
		query.setParameter(1, orderInfoStatusNo);
		query.setParameter(2, orderInfoNo);

		try {
			int rs = query.executeUpdate();
			if (rs == 0) {
				result = null;
			} else {
				result = this.selectMyOrderInfoByNo(orderInfoNo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("updateOrderInfoStatus更新失敗");
		}

		return result;
	}

	public double selectTotalPrice(int orderInfoNo) {

		String selectTotalPrice = "select * from selectTotalPrice where orderInfo_No=?";
		double result = -1;
		NativeQuery<Object[]> query = this.getSession().createNativeQuery(selectTotalPrice);
		query.setParameter(1, orderInfoNo);
		try {
			result = Double.parseDouble(query.getSingleResult()[1].toString());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("selectTotalPrice查無資料");
		}
		return result;
	}

	public List<OrderInfoBean> selectMyGroupOrderInfo(int groupInfoNo) {

		String selectMyGroupOrderInfo = "from eeit9212.model.OrderInfoBean where groupInfo_No=? order by orderInfo_No desc";
		List<OrderInfoBean> result = null;
		Query<OrderInfoBean> query = this.getSession().createQuery(selectMyGroupOrderInfo);
		query.setParameter(0, groupInfoNo);
		result = query.getResultList();
		return result;

	}

}
