package eeit9212.model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import eeit9212.model.GroupAttendanceDAO;

public class GroupAttendanceDAOHibernate implements GroupAttendanceDAO{

	private SessionFactory sessionFactory;

	public GroupAttendanceDAOHibernate(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("eeit9212/beansHibernate.xml");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		sessionFactory.getCurrentSession().beginTransaction();
		GroupAttendanceDAO groupAttendanceDAOJdbc = new GroupAttendanceDAOHibernate(sessionFactory);
		System.out.println(groupAttendanceDAOJdbc.selectGroupAttendance(5));
//		System.out.println(groupAttendanceDAOJdbc.insertGroupAttendance(5, 0));
		System.out.println(groupAttendanceDAOJdbc.updateGroupAttendance(5, 1));
		
		
		sessionFactory.getCurrentSession().getTransaction().commit();
		((ConfigurableApplicationContext) context).close();

	}


	@Override
	public boolean selectGroupAttendance(int memberNo) {
		String selectGroupAttendance = "select * from groupAttendance where member_No=?";
		boolean result=false;
		NativeQuery<?> query = this.getSession().createNativeQuery(selectGroupAttendance);
		query.setParameter(1, memberNo);
		List<?> aa = query.getResultList();
		if(aa.size()!=0){
			result=true;
		}
		return result;
	}

	@Override
	public int insertGroupAttendance(int memberNo,int success) {
		String insertGroupAttendance = "insert into groupAttendance values(?,1,?);";
		int result = -1;
		NativeQuery<?> query = this.getSession().createNativeQuery(insertGroupAttendance);
		query.setParameter(1, memberNo);
		query.setParameter(2, success);
		try{
		result=query.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;

	}


	@Override
	public int updateGroupAttendance(int memberNo,int success) {
		String updateGroupAttendance = "update groupAttendance set groupAttendance_TotalQt=groupAttendance_TotalQt+1,groupAttendance_TotalSuccess=groupAttendance_TotalSuccess+? where member_No=?";
		
		NativeQuery<?> query = this.getSession().createNativeQuery(updateGroupAttendance);
		query.setParameter(1, success);
		query.setParameter(2, memberNo);
		return query.executeUpdate();
	}
	
	
	
}
