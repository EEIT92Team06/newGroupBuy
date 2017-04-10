package eeit9212.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import eeit9212.model.GrouperCreditDAO;

public class GrouperCreditDAOHibernate implements GrouperCreditDAO {

	private SessionFactory sessionFactory;

	public GrouperCreditDAOHibernate(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("eeit9212/beansHibernate.xml");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		sessionFactory.getCurrentSession().beginTransaction();
		GrouperCreditDAO grouperCreditDAOJdbc = new GrouperCreditDAOHibernate(sessionFactory);
//		System.out.println(grouperCreditDAOJdbc.selectGrouperCredit(4));
//		System.out.println(grouperCreditDAOJdbc.insertGrouperCredit(2, 3));
		System.out.println(grouperCreditDAOJdbc.updateGrouperCredit(4, 5));
		sessionFactory.getCurrentSession().getTransaction().commit();
		((ConfigurableApplicationContext) context).close();

	}


	@Override
	public boolean selectGrouperCredit(int memberNo) {
		String selectGrouperCredit = "select * from grouperCredit where member_No=?";
		boolean result=false;
		NativeQuery<?> query = this.getSession().createNativeQuery(selectGrouperCredit);
		query.setParameter(1, memberNo);
		List<?> rs = query.getResultList();
		if(rs.size()!=0){
			result=true;
		}
		return result;

	}

	@Override
	public int insertGrouperCredit(int memberNo,int score) {
		String insertGrouperCredit = "insert into grouperCredit values(?,?,1)";
		int result = -1;
		NativeQuery<?> query=this.getSession().createNativeQuery(insertGrouperCredit);
		query.setParameter(1, memberNo);
		query.setParameter(2, score);
		try{
			result=query.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;

	}

	@Override
	public int updateGrouperCredit(int memberNo,int score) {
		String updateGrouperCredit = "update grouperCredit set grouperCredit_TotalScore=grouperCredit_TotalScore+?,grouperCredit_TotalPeople=grouperCredit_TotalPeople+1 where member_No=?";
		
		NativeQuery<?> query = this.getSession().createNativeQuery(updateGrouperCredit);
		query.setParameter(1, score);
		query.setParameter(2, memberNo);
		return query.executeUpdate();

	}
	
	
}
