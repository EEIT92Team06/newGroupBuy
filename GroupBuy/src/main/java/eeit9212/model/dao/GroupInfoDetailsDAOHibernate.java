package eeit9212.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import eeit9212.model.GroupInfoDetailsBean;
import eeit9212.model.GroupInfoDetailsDAO;
import eeit9212.model.OrderInfoDetailsBean;

public class GroupInfoDetailsDAOHibernate implements GroupInfoDetailsDAO {

	private SessionFactory sessionFactory;

	public GroupInfoDetailsDAOHibernate(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("eeit9212/beansHibernate.xml");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		sessionFactory.getCurrentSession().beginTransaction();
		GroupInfoDetailsDAO groupInfoDetailsDAOJdbc = new GroupInfoDetailsDAOHibernate(sessionFactory);
		List<GroupInfoDetailsBean> select=groupInfoDetailsDAOJdbc.selectGroupInfoDetail(1);
		System.out.println(select);
		sessionFactory.getCurrentSession().getTransaction().commit();
		((ConfigurableApplicationContext) context).close();
		
		
	}


	@Override
	public List<GroupInfoDetailsBean> selectGroupInfoDetail(int groupInfoNo){
		String selectGroupInfoDetail="from GroupInfoDetailsBean where groupInfo_No=?";
		
		List<GroupInfoDetailsBean> result=null;
		
		Query<GroupInfoDetailsBean> query = this.getSession().createQuery(selectGroupInfoDetail);
		query.setParameter(0, groupInfoNo);
		result=query.getResultList();
		return result;
	}

}
