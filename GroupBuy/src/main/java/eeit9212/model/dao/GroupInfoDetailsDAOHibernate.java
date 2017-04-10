package eeit9212.model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import eeit9212.model.GroupInfoDetailsBean;
import eeit9212.model.GroupInfoDetailsDAO;

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
	public List<eeit9212.model.GroupInfoDetailsBean> selectGroupInfoDetail(int groupInfoNo){
		String selectGroupInfoDetail="from eeit9212.model.GroupInfoDetailsBean where groupInfo_No=?";
		
		List<eeit9212.model.GroupInfoDetailsBean> result=null;
		
		Query<eeit9212.model.GroupInfoDetailsBean> query = this.getSession().createQuery(selectGroupInfoDetail,eeit9212.model.GroupInfoDetailsBean.class);
		query.setParameter(0, groupInfoNo);
		result=query.getResultList();
		return result;
	}

}
