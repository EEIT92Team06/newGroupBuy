package eeit9212.model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import eeit9212.model.GroupInfoPicBean;
import eeit9212.model.GroupInfoPicDAO;

public class GroupInfoPicDAOHibernate implements GroupInfoPicDAO {

	private SessionFactory sessionFactory;

	public GroupInfoPicDAOHibernate(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("eeit9212/beansHibernate.xml");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		sessionFactory.getCurrentSession().beginTransaction();
		GroupInfoPicDAO groupInfoPicDAOJdbc = new GroupInfoPicDAOHibernate(sessionFactory);
		GroupInfoPicBean select1=groupInfoPicDAOJdbc.selectGroupInfoPicByNo(4);
		System.out.println(select1);
		
		List<GroupInfoPicBean> select = groupInfoPicDAOJdbc.selectGroupInfoPic(1);
		System.out.println(select);

		for (GroupInfoPicBean bean : select) {
			System.out.print(bean.getGroupInfoPicNo() + "  ");
			System.out.print(bean.getGroupInfoNo() + "  ");
			System.out.println(bean.getGroupInfoPicProductPic());
		}
		
		
		sessionFactory.getCurrentSession().getTransaction().commit();
		((ConfigurableApplicationContext) context).close();
		
		
	}


	@Override
	public GroupInfoPicBean selectGroupInfoPicByNo(int groupInfoPicNo) {
		String selectGroupInfoPicByNo = "from eeit9212.model.GroupInfoPicBean where groupInfoPic_No=?";
		GroupInfoPicBean result=null;
		Query<GroupInfoPicBean> query = this.getSession().createQuery(selectGroupInfoPicByNo);
		query.setParameter(0, groupInfoPicNo);	
		try{
			result = query.getSingleResult();
			}catch(Exception e){
				System.out.println("selectGroupInfoPicByNo查無資料");
			}
		return result;

	}
	
	
	@Override
	public List<GroupInfoPicBean> selectGroupInfoPic(int groupInfoNo) {
		String selectGroupInfoPic = "from GroupInfoPicBean where groupInfo_No=?";

		List<GroupInfoPicBean> result = null;
		Query<GroupInfoPicBean> query = this.getSession().createQuery(selectGroupInfoPic);
		query.setParameter(0, groupInfoNo);
		result=query.getResultList();
		return result;

	}

}
