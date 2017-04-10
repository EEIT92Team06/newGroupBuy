package searchgroup.model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import searchgroup.model.GroupMsgBean;
import searchgroup.model.GroupMsgDAO;
import searchgroup.model.GroupMsgReplyBean;

public class GroupMsgDAOhb implements GroupMsgDAO {
	private SessionFactory sessionFactory;
	public GroupMsgDAOhb(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.cfg.xml");
		SessionFactory sessionFactory = (SessionFactory)context.getBean("sessionFactory");
		sessionFactory.getCurrentSession().beginTransaction();
		GroupMsgDAOhb groupMsgDAOhb = (GroupMsgDAOhb) context.getBean("groupMsgDAO");
//		List<Object[]> results = groupMsgDAOhb.selectMsg(1);
//		for(Object[] result : results){
//			for(int i=0 ; i< result.length;i++){
//				System.out.println("result : "+result[i]);
//			}
//		}
//-------------------------------------------------------
//		List<Object[]> results = groupMsgDAOhb.selectReplyMsg(1);
//		for(Object[] result : results){
//			for(int i=0 ; i< result.length;i++){
//				System.out.println("result : "+result[i]);
//			}
//		}
//-------------------------------------------------------
		GroupMsgBean groupMsgBean = new GroupMsgBean(1, 1, "testest");
		groupMsgDAOhb.insert(groupMsgBean);
//-------------------------------------------------------
//		GroupMsgReplyBean groupMsgReplyBean = new GroupMsgReplyBean(5, 1, "哈哈");
//		groupMsgDAOhb.insertReply(groupMsgReplyBean);
		
		sessionFactory.getCurrentSession().getTransaction().commit();
		((ConfigurableApplicationContext) context).close();
	}
	
	

   
	private static String SELECT_BY_GROUPINFONO = "select groupMsg_No , member_Name , groupMsg_Content "
	+ "from groupMsg join member on member.member_No = groupMsg.member_No "
	+ "where groupInfo_No = ?";
	@Override
	public List<Object[]> selectMsg(int groupInfoNo) {
		Session session = this.getSession();    
		NativeQuery query = session.createNativeQuery(SELECT_BY_GROUPINFONO);
		query.setParameter(1, groupInfoNo);
		return query.getResultList();
	}
	
	private static String SELECT_BY_GROUPMSGNO = "select member_Name ,groupMsgReply_Content from groupMsgReply "
			+"join member on member.member_No = groupMsgReply.member_No "
			+"where  groupMsg_No= ?";
	
	public List<Object[]> selectReplyMsg(int groupMsgNo) {
		Session session = this.getSession();
		NativeQuery query = session.createNativeQuery(SELECT_BY_GROUPMSGNO);
		query.setParameter(1, groupMsgNo);
		return query.getResultList();
	}
	
	private static String INSERTMSG = "insert into groupMsg values(?,?,?)";
	@Override
	public void insert(GroupMsgBean groupMsgBean) {
		Session session = this.getSession();
		session.save(groupMsgBean);
	}

	private static String INSERTMSGREPLY= "insert into groupMsgReply values(?,?,?);";
	@Override
	public void insertReply(GroupMsgReplyBean groupMsgReplyBean) {
		Session session = this.getSession();
		session.save(groupMsgReplyBean);
	}
	



}
