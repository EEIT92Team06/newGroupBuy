package insertAnnounce.mode.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import insertAnnounce.model.AnnounceDAO;
import insertAnnounce.model.AnnouncementBean;
import searchgroup.model.OrderBean;
import searchgroup.model.OrderDAO;
import searchgroup.model.OrderDetailsBean;

public class AnnounceDAOhb implements AnnounceDAO {
	private SessionFactory sessionFactory;
	public AnnounceDAOhb(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	public static void main(String args[]){
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.cfg.xml");
		SessionFactory sessionFactory = (SessionFactory)context.getBean("sessionFactory");
		sessionFactory.getCurrentSession().beginTransaction();
		AnnounceDAO announceDAO = (AnnounceDAO) context.getBean("announceDAO");
		
//		Timestamp time = new Timestamp(new Date().getTime());
//		System.out.println("time : " + time);
		
//		AnnouncementBean announce = new AnnouncementBean(1, 9301, "hi", time, "nono");
//		announceDAO.insert(announce);
		
//		List<Object[]> results = announceDAO.selectPartMem();
//		for(Object[] result : results){
//			System.out.println("result : " + result[0].toString());
//			System.out.println("result : " + result[1].toString());
//		}
//		int result = announceDAO.insertBan(1);
//		System.out.println("result : " + result);
		
//		System.out.println(announceDAO.updateGroupStatus(2));
//		System.out.println(announceDAO.updateOrderInfoStatusByGroupInfoNo(1));
		
		List<Object[]> results = announceDAO.selectReports();
		for(Object[] result : results){
			System.out.println(result);
		}
		
		sessionFactory.getCurrentSession().getTransaction().commit();
		((ConfigurableApplicationContext) context).close();
	}
	
	@Override
	public void insert(AnnouncementBean announcement) {
		Session session = this.getSession();
		session.save(announcement);
	}
	
	
	private static final String SELECTMEMBER = "select member_No , member_Name from member";
	@Override
	public List<Object[]> select() {
		Session session = this.getSession();
		NativeQuery query = session.createNativeQuery(SELECTMEMBER);
		return query.getResultList();
	}
	
	private static final String SELECTMEMBERWITHSTATUS = 
			"select member_No , member_Name from member where member_Status!=9103";
	@Override
	public List<Object[]> selectPartMem() {
		Session session = this.getSession();
		NativeQuery query = session.createNativeQuery(SELECTMEMBERWITHSTATUS);
		return query.getResultList();
	}
			
			
	private static final String UPDATEMEMBERSTATUS = "update member set member_Status=9103 where member_No = ?";
	@Override
	public int update(int memberNo) {
		Session session = this.getSession();
		NativeQuery query = session.createNativeQuery(UPDATEMEMBERSTATUS);
		query.setParameter(1, memberNo);
		return query.executeUpdate();
	}
	
	private static final String INSERTBAN = "insert into ban values(?,GETDATE()+3)";
	@Override
	public int insertBan(int memberNo) {
		Session session = this.getSession();
		NativeQuery query = session.createNativeQuery(INSERTBAN);
		query.setParameter(1, memberNo);
		return query.executeUpdate();
	}
	
	private static final String UPDATEGROUPSTATUS = "update groupInfo set groupStatus_No = 12 where groupInfo_No = ?";
	@Override
	public int updateGroupStatus(int groupInfoNo) {
		Session session = this.getSession();
		NativeQuery query = session.createNativeQuery(UPDATEGROUPSTATUS);
		query.setParameter(1, groupInfoNo);
		return query.executeUpdate();
	}
	
	
	private static final String updateOrderInfoStatusByGroupInfoNo = 
			"update orderInfo set orderInfoStatus_No=1005 where groupInfo_No=?";
	@Override
	public int updateOrderInfoStatusByGroupInfoNo(int groupInfoNo) {
		NativeQuery query = this.getSession().createNativeQuery(updateOrderInfoStatusByGroupInfoNo);
		query.setParameter(1, groupInfoNo);
		return query.executeUpdate();
	}
	
	 
	  
	private static final String SELECTREPORTS = 
	"select report_No,report.member_No,member_Name,report.reportType_No ,reportType ,reportStatus_No,report_Target,report_Content "
   +"from report join reportType on report.reportType_No = reportType.reportType_No "
   +"join member on report.member_No = member.member_No";
	@Override
	public List<Object[]> selectReports() {
		Session session = this.getSession();
		NativeQuery query = session.createNativeQuery(SELECTREPORTS);
		return query.getResultList();
	}
}
