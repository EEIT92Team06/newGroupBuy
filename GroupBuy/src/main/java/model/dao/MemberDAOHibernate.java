package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import model.MemberBean;
import model.MemberDAO;
///GroupBuy/src/main/resources/eeit9211/beans.config.xml
public class MemberDAOHibernate implements MemberDAO {

	private SessionFactory sessionFactory;

	public MemberDAOHibernate(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	public static void main(String[] args){
		ApplicationContext context=new ClassPathXmlApplicationContext("eeit9211/beansHibernate.config.xml");
		SessionFactory sessionFactory=(SessionFactory)context.getBean("sessionFactory");
		MemberDAO memberDAO=(MemberDAO)context.getBean("memberDAO");
		sessionFactory.getCurrentSession().beginTransaction();
//		System.out.println(memberDAO.selectMemberInfo(1));
//		System.out.println(memberDAO.updateMemberPassword(999, "zz"));
//		System.out.println(memberDAO.updateMemberInfo(999, "aa", "addd"));
//		System.out.println(memberDAO.updateMemberPic(999, "aaaaaaa"));
		
		sessionFactory.getCurrentSession().getTransaction().commit();
		((ConfigurableApplicationContext)context).close();
	}
	
	@Override
	public MemberBean selectMemberInfo(int memberNo) {
//		B車可select但看不懂*

		String selectMemberInfo= "FROM model.MemberBean WHERE member_No=?";
		MemberBean result = null;
		 Query<MemberBean> query = this.getSession().createQuery(selectMemberInfo);
//		 只有B車問號從0開始
		 query.setParameter(0, memberNo);
		 try {
			result=query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public MemberBean updateMemberPassword(int memberNo, String memberPassword) {
		String updateMemberPassword="UPDATE member SET member_Password=? WHERE member_No=?";
		MemberBean result=null;
		Query query=this.getSession().createNativeQuery(updateMemberPassword);
		query.setParameter(1, memberPassword);
		query.setParameter(2, memberNo);
		int i=query.executeUpdate();
		if(i!=0){
			result=new MemberBean();
			result.setMemberNo(memberNo);
			result.setMemberPassword(memberPassword);
		}
		return result;
	}

	@Override
	public MemberBean updateMemberPic(int memberNo, String memberPic) {
		String updateMemberPic="UPDATE member SET member_Pic=? WHERE member_No=?";
		MemberBean result=null;
		Query query=this.getSession().createNativeQuery(updateMemberPic);
		query.setParameter(1, memberPic);
		query.setParameter(2, memberNo);
		int i=query.executeUpdate();
		if(i!=0){
			result=new MemberBean();
			result.setMemberNo(memberNo);
			result.setMemberPic(memberPic);
		}
		return result;
	};
	
	@Override
	public MemberBean updateMemberInfo(int memberNo, String memberNickName, String memberAddress){
		String updateMemberInfo="UPDATE member SET member_NickName=?, member_Address=? WHERE member_No=?";
		MemberBean result=null;
		Query query=this.getSession().createNativeQuery(updateMemberInfo);
		query.setParameter(1, memberNickName);
		query.setParameter(2, memberAddress);
		query.setParameter(3, memberNo);
		int i=query.executeUpdate();
		if(i!=0){
			result=new MemberBean();
			result.setMemberNo(memberNo);
			result.setMemberNickName(memberNickName);
			result.setMemberAddress(memberAddress);
		}
		return result;
	}


}
