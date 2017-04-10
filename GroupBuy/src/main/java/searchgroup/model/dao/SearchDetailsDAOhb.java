package searchgroup.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import searchgroup.model.SearchDetailsDAO;

public class SearchDetailsDAOhb implements SearchDetailsDAO {

	private SessionFactory sessionFactory;

	public SearchDetailsDAOhb(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public static void main(String args[]) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.cfg.xml");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		sessionFactory.getCurrentSession().beginTransaction();

		SearchDetailsDAO searchDetailsDAO = (SearchDetailsDAO) context.getBean("searchDetailsDAO");
//		 Object[] result = searchDetailsDAO.selectDetails(1);
//		 for(int i=0;i<result.length;i++){
//		 System.out.println("result : " + result[i]);
//		 }
//		 -------------------------------------------------------
//		List<Object[]> result = searchDetailsDAO.selectGroupProdsDetails(1);
//		for (Object[] objArr : result) {
//			for (int i = 0; i < objArr.length; i++) {
//				System.out.println(objArr[i]);
//			}
//		}
//		 -------------------------------------------------------
//		List<Object> result = searchDetailsDAO.selectDetailsPicNo(1);
//		for (Object obj : result) {
//				System.out.println("result : " + obj);
//			
//		}
//		 -------------------------------------------------------
//		int result = searchDetailsDAO.insertClickTimes(1);
//		System.out.println("成功update "+result+"筆資料");
//		 -------------------------------------------------------
		Object result = searchDetailsDAO.selectmemberPic(1);
		System.out.println("result : " + result);
		
		
		sessionFactory.getCurrentSession().getTransaction().commit();
		((ConfigurableApplicationContext) context).close();
	}

	private static final String SELECTDETAILS3_BY_GROUPINFONO = "select groupInfoPic_No from groupInfoPic where groupInfo_No = ?";
	private static final String SELECTDETAILS2_BY_GROUPINFONO = "select groupInfoDetails_No , groupInfoDetails_ProdcutName , groupInfoDetails_ProductPrice "
			+ "from groupInfoDetails " + "where groupInfo_No = ?";
	private static final String SELECTDETAILS_BY_GROUPINFONO = "select groupInfo.member_No , groupInfo_Name , member_Name , groupInfo_StartDate , groupInfo_DeadLine , "
			+ "groupInfo_Content , groupInfo_ShippingWay , productType, groupStatus , groupInfo_MinProductQt "
			+ ", grouperCredit_totalScore/grouperCredit_totalPeople result "
			+ "from groupInfo join productType on groupInfo.productType_No = productType.productType_No "
			+ "join groupStatus on groupInfo.groupStatus_No = groupStatus.groupStatus_No "
			+ "join member on groupInfo.member_No = member.member_No "
			+ "Left Outer join grouperCredit on grouperCredit.member_No = member.member_No " + "where groupInfo_No = ?";

	@Override
	public Object[] selectDetails(int groupInfoNo) {
		Session session = this.getSession();
		NativeQuery<Object[]> query = session.createNativeQuery(SELECTDETAILS_BY_GROUPINFONO);
		query.setParameter(1, groupInfoNo);
		return query.getSingleResult();
	}

	public List<Object[]> selectGroupProdsDetails(int groupInfoNo) {
		Session session = this.getSession();
		NativeQuery<Object[]> query = session.createNativeQuery(SELECTDETAILS2_BY_GROUPINFONO);
		query.setParameter(1, groupInfoNo);
		return query.getResultList();
	}

	public List<Object> selectDetailsPicNo(int groupInfoNo) {
		Session session = this.getSession();
		NativeQuery<Object> query = session.createNativeQuery(SELECTDETAILS3_BY_GROUPINFONO);
		query.setParameter(1, groupInfoNo);
		return query.getResultList();
	}
	private static final String SELECT_BY_MEMBERNO = "  select member_Pic from member where member_No = ?";
	public Object selectmemberPic(int memberNo) {
		Session session = this.getSession();
		NativeQuery<Object> query = session.createNativeQuery(SELECT_BY_MEMBERNO);
		query.setParameter(1, memberNo);
		return query.getSingleResult();
	}
	
	private static final String INSERTCLICKTIME_BY_GROUPINFONO = "update groupInfo set groupInfo_ClickTimes = groupInfo_ClickTimes+1 "
			+ "where groupInfo_No = ?";
	public int insertClickTimes(int groupInfoNo) {
		Session session = this.getSession();
		NativeQuery query = session.createNativeQuery(INSERTCLICKTIME_BY_GROUPINFONO);
		query.setParameter(1, groupInfoNo);
		return query.executeUpdate();
	}
	
}
