package eeit9212.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import creategroup.model.GroupInfoBean;
import eeit9212.model.AttendGroupInfoBean;
import eeit9212.model.CreateGroupInfoBean;
import eeit9212.model.GroupInfoDAO;

public class GroupInfoDAOHibernate implements GroupInfoDAO {

	private SessionFactory sessionFactory;

	public GroupInfoDAOHibernate(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public static void main(String args[]) {
		ApplicationContext context = new ClassPathXmlApplicationContext("eeit9212/beansHibernate.xml");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		sessionFactory.getCurrentSession().beginTransaction();
		GroupInfoDAO groupInfoDAOJdbc = new GroupInfoDAOHibernate(sessionFactory);
		// System.out.println(groupInfoDAOJdbc.updateGroupInfoDeadLine(10,new
		// Timestamp(new java.util.Date().getTime()+86400000)));
		// System.out.println(groupInfoDAOJdbc.updateGroupStatus(5, 15));

		 CreateGroupInfoBean selectGroupInfoByGroupInfoNo = groupInfoDAOJdbc.selectGroupInfoByGroupInfoNo(1);
		 System.out.println(selectGroupInfoByGroupInfoNo);
		
		AttendGroupInfoBean selectMyAttendedByGroupInfoNo = groupInfoDAOJdbc.selectMyAttendedByGroupInfoNo(1, 3);
		System.out.println(selectMyAttendedByGroupInfoNo);
		 List<AttendGroupInfoBean> selectMyAttendedGroupInfo =
		 groupInfoDAOJdbc.selectMyAttendedGroupInfo(1);
		  System.out.println(selectMyAttendedGroupInfo);
		 for (AttendGroupInfoBean bean : selectMyAttendedGroupInfo) {
		 System.out.print(bean.getGroupInfoNo() + " ");
		 System.out.print(bean.getOrderInfoNo() + " ");
		 System.out.print(bean.getGroupInfoMemberNo() + " ");
		 System.out.print(bean.getMemberName() + " ");
		 System.out.print(bean.getGroupStatusNo() + " ");
		 System.out.print(bean.getGroupStatus() + " ");
		 System.out.print(bean.getProductType() + " ");
		 System.out.print(bean.getGroupInfoName() + " ");
		 System.out.print(bean.getGroupInfoMinProductQt() + " ");
		 System.out.print(bean.getGroupInfoTotalProductQt() + " ");
		 System.out.print(bean.getGroupInfoStartDate() + " ");
		 System.out.print(bean.getGroupInfoDeadLine() + " ");
		 System.out.print(bean.getGroupInfoContent() + " ");
		 System.out.print(bean.getGroupInfoShippingWay() + " ");
		 System.out.print(bean.getGroupInfoBankAccount() + " ");
		 System.out.print(bean.getGrouperCredit() + " ");
		 System.out.print(bean.getOrderStatusNo() + " ");
		 System.out.print(bean.getOrderStatus() + " ");
		 System.out.println(bean.getGroupInfoCoverPic());
		
		 }
		
		  List<CreateGroupInfoBean> selectMyCreatedGroupInfo = groupInfoDAOJdbc.selectMyCreatedGroupInfo(1);
		  System.out.println(selectMyCreatedGroupInfo);
		 for (CreateGroupInfoBean bean1 : selectMyCreatedGroupInfo) {
		 System.out.print(bean1.getGroupInfoNo() + " ");
		 System.out.print(bean1.getGroupInfoMemberNo() + " ");
		 System.out.print(bean1.getMemberName() + " ");
		 System.out.print(bean1.getGroupStatusNo() + " ");
		 System.out.print(bean1.getGroupStatus() + " ");
		 System.out.print(bean1.getProductType() + " ");
		 System.out.print(bean1.getGroupInfoName() + " ");
		 System.out.print(bean1.getGroupInfoMinProductQt() + " ");
		 System.out.print(bean1.getGroupInfoTotalProductQt() + " ");
		 System.out.print(bean1.getGroupInfoStartDate() + " ");
		 System.out.print(bean1.getGroupInfoDeadLine() + " ");
		 System.out.print(bean1.getGroupInfoContent() + " ");
		 System.out.print(bean1.getGroupInfoShippingWay() + " ");
		 System.out.print(bean1.getGroupInfoBankAccount() + " ");
		 System.out.println(bean1.getGroupInfoCoverPic());
		 }
		sessionFactory.getCurrentSession().getTransaction().commit();
		((ConfigurableApplicationContext) context).close();
	}

	@Override
	public int updateGroupInfoDeadLine(int groupInfoNo, Timestamp deadLine) {

		String updateGroupInfoDeadLine = "update groupInfo set groupInfo_DeadLine=? where groupInfo_No=?";
		NativeQuery<?> query = this.getSession().createNativeQuery(updateGroupInfoDeadLine);
		query.setParameter(1, deadLine);
		query.setParameter(2, groupInfoNo);
		return query.executeUpdate();
	}

	@Override
	public int updateGroupStatus(int groupInfoNo, int groupStatusNo) {

		String updateGroupStatus = "update groupInfo set groupStatus_No=? where groupInfo_No=?";
		int result = -1;
		NativeQuery<?> query = this.getSession().createNativeQuery(updateGroupStatus);
		query.setParameter(1, groupStatusNo);
		query.setParameter(2, groupInfoNo);
		try {
			result = query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public AttendGroupInfoBean selectMyAttendedByGroupInfoNo(int memberNo, int groupInfoNo) {

		String selectMyAttendedByGroupInfoNo = "from AttendGroupInfoBean where orderInfoMember_No=? and groupInfo_No=?";
		AttendGroupInfoBean result = null;
		Query<AttendGroupInfoBean> query = this.getSession().createQuery(selectMyAttendedByGroupInfoNo);
		query.setParameter(0, memberNo);
		query.setParameter(1, groupInfoNo);
		try{
		result = query.getSingleResult();
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("selectMyAttendedByGroupInfoNo查無資料");
		}
		return result;

	}

	@Override
	public List<AttendGroupInfoBean> selectMyAttendedGroupInfo(int memberNo) {

		String selectMyAttendedGroupInfo = "from AttendGroupInfoBean where orderInfoMember_No=?";
		List<AttendGroupInfoBean> result = null;
		Query<AttendGroupInfoBean> query = this.getSession().createQuery(selectMyAttendedGroupInfo);	
		query.setParameter(0, memberNo);	
		result = query.getResultList();
		return result;

	}

	@Override
	public CreateGroupInfoBean selectGroupInfoByGroupInfoNo(int groupInfoNo) {
		
		String selectGroupInfoBygroupInfoNo = "from CreateGroupInfoBean where groupInfo_No=?";
		CreateGroupInfoBean result=null;
		Query<CreateGroupInfoBean> query = this.getSession().createQuery(selectGroupInfoBygroupInfoNo);
		query.setParameter(0, groupInfoNo);	
		try{
			result = query.getSingleResult();
			}catch(Exception e){
				e.printStackTrace();
				System.out.println("selectGroupInfoByGroupInfoNo查無資料");
			}
		return result;

	}

	@Override
	public List<CreateGroupInfoBean> selectMyCreatedGroupInfo(int memberNo) {

		String selectMyCreatedGroupInfo = "from CreateGroupInfoBean where member_No=?";
		List<CreateGroupInfoBean> result = null;
		Query<CreateGroupInfoBean> query = this.getSession().createQuery(selectMyCreatedGroupInfo);
		query.setParameter(0, memberNo);	
		result = query.getResultList();	
		return result;
	}
}
