package eeit9212.model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import eeit9212.model.OrderInfoDetailsBean;
import eeit9212.model.OrderInfoDetailsDAO;

public class OrderInfoDetailsDAOHibernate implements OrderInfoDetailsDAO {
	private SessionFactory sessionFactory;

	public OrderInfoDetailsDAOHibernate(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("eeit9212/beansHibernate.xml");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		sessionFactory.getCurrentSession().beginTransaction();
		OrderInfoDetailsDAO orderInfoDetailsDAOJdbc = new OrderInfoDetailsDAOHibernate(sessionFactory);
		OrderInfoDetailsBean select1=orderInfoDetailsDAOJdbc.selectOneOrderInfoDetails(1, 6);
		System.out.println(select1);
		
		List<OrderInfoDetailsBean> select = orderInfoDetailsDAOJdbc.selectOrderInfoDetails(1);
		System.out.println(select);

		for (OrderInfoDetailsBean bean : select) {
			System.out.print(bean.getOrderInfoDetailsNo() + "  ");
			System.out.print(bean.getGroupInfoDetailsProdcutName() + "  ");
			System.out.print(bean.getProductTotalPriceByQt() + "  ");
			System.out.println(bean.getOrderInfoDetailsProductQt());
		}
		
		
		sessionFactory.getCurrentSession().getTransaction().commit();
		((ConfigurableApplicationContext) context).close();
		
	}

	public OrderInfoDetailsBean selectOneOrderInfoDetails(int orderInfoNo,int groupInfoDetailsNo) {
		
		String selectOneOrderInfoDetails="from OrderInfoDetailsBean where orderInfo_No=? and groupInfoDetails_No=?";
		
		OrderInfoDetailsBean result=null;
		Query<OrderInfoDetailsBean> query = this.getSession().createQuery(selectOneOrderInfoDetails);
		query.setParameter(0, orderInfoNo);
		query.setParameter(1, groupInfoDetailsNo);
		try{
			result = query.getSingleResult();
			}catch(Exception e){
				System.out.println("selectOneOrderInfoDetails查無資料");
			}
		return result;
	}
	

	@Override
	public List<OrderInfoDetailsBean> selectOrderInfoDetails(int orderInfoNo) {
		
		String selectOrderInfoDetails="from OrderInfoDetailsBean where orderInfo_No=?";	
		List<OrderInfoDetailsBean> result = null;
		Query<OrderInfoDetailsBean> query = this.getSession().createQuery(selectOrderInfoDetails);
		query.setParameter(0, orderInfoNo);
		result=query.getResultList();
		return result;
	}

}
