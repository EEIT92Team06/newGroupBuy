package searchgroup.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import searchgroup.model.GroupMsgBean;
import searchgroup.model.OrderBean;
import searchgroup.model.OrderDAO;
import searchgroup.model.OrderDetailsBean;

public class OrderDAOhb implements OrderDAO {
	private SessionFactory sessionFactory;
	public OrderDAOhb(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	public static void main(String args[]){
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.cfg.xml");
		SessionFactory sessionFactory = (SessionFactory)context.getBean("sessionFactory");
		sessionFactory.getCurrentSession().beginTransaction();
		OrderDAO orderDAO = (OrderDAO) context.getBean("orderDAO");
		
		OrderBean orderBean = new OrderBean();
		OrderDetailsBean orderDetailsBean1 = new OrderDetailsBean(orderBean, 6, 3);
		OrderDetailsBean orderDetailsBean2 = new OrderDetailsBean(orderBean, 7, 4);

		Set<OrderDetailsBean> orderDetails = new HashSet<OrderDetailsBean>();
		orderDetails.add(orderDetailsBean1);
		orderDetails.add(orderDetailsBean2);
		
		orderBean.setGroupInfoNo(1);
		orderBean.setMemberNo(5);
		orderBean.setOrderInfoStatusNo(1001);
		orderBean.setOrderDetails(orderDetails); 
		
		orderDAO.insert(orderBean);
		
		sessionFactory.getCurrentSession().getTransaction().commit();
		((ConfigurableApplicationContext) context).close();
	}
	
	@Override
	public void insert(OrderBean orderBean) {
		Session session = this.getSession();
		session.save(orderBean);
	}


}
