package model.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import model.FriendBean;
import model.FriendDAO;

public class FriendDAOHibernate implements FriendDAO {

	private SessionFactory sessionFactory;

	public FriendDAOHibernate(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("eeit9211/beansHibernate.config.xml");
		FriendDAO friendDAO = (FriendDAO) context.getBean("friendDAO");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();

		// List<FriendBean> result =friendDAO.selectRelationList(1, 2101);
		// List<FriendBean> result =friendDAO.selectRequestedList(99, 2103);
		// System.out.println(friendDAO.insertRelation(99, 2, 2101));
		// System.out.println(friendDAO.deleteRelationFromList(1));
		// System.out.println(friendDAO.deleteRelation(1, 2));
		// System.out.println(friendDAO.updateRelation(3, 2101));
		List<FriendBean> result = friendDAO.selectMember(1, "米");
//		System.out.println(friendDAO.selectRelation(1, 3));
		System.out.println(result);
		transaction.commit();
		((ConfigurableApplicationContext) context).close();
	}

	@Override
	public List<FriendBean> selectRelationList(int loginMemberNo, int friendStatusNo) {
		String selectRelationList = "SELECT friend_No, memberfriend_No, member_Name, member_NickName, member_Pic "
				+ "FROM friend JOIN member ON friend.memberFriend_No=member.member_No "
				+ "WHERE friend.member_No=? AND friendStatus_No=?";
		List<FriendBean> result = null;
		Session session = sessionFactory.getCurrentSession();
		Query<Object[]> query = session.createNativeQuery(selectRelationList);
		query.setParameter(1, loginMemberNo);
		query.setParameter(2, friendStatusNo);
		result = new ArrayList<FriendBean>();
		List<Object[]> list = query.getResultList();
		for (Object[] obj : list) {
			FriendBean result1 = new FriendBean();
			result1.setFriendNo((Integer) obj[0]);
			result1.setMemberNo(Integer.parseInt(obj[1].toString()));
			result1.setMemberFriendNo((Integer) obj[1]);
			result1.setMemberName((String) obj[2]);
			result1.setMemberNickName((String) obj[3]);
			result1.setMemberPic((String) obj[4]);
			result.add(result1);
		}

		return result;
	}

	@Override
	public List<FriendBean> selectRequestedList(int memberFriendNo, int friendStatusNo) {
		String selectRelationList = "SELECT friend_No, friend.member_No, member_Name, member_NickName, member_Pic "
				+ "FROM friend JOIN member ON friend.member_No=member.member_No "
				+ "WHERE friend.memberFriend_No=? AND friendStatus_No=?";
		List<FriendBean> result = null;
		Query<Object[]> query = sessionFactory.getCurrentSession().createNativeQuery(selectRelationList);
		query.setParameter(1, memberFriendNo);
		query.setParameter(2, friendStatusNo);
		result = new ArrayList<FriendBean>();
		List<Object[]> list = query.getResultList();
		for (Object[] obj : list) {
			FriendBean result1 = new FriendBean();
			result1.setFriendNo((Integer) obj[0]);
			result1.setMemberNo((Integer) obj[1]);
			result1.setMemberName((String) obj[2]);
			result1.setMemberNickName((String) obj[3]);
			result1.setMemberPic((String) obj[4]);
			result.add(result1);
		}
		return result;
	}

	@Override
	public boolean insertRelation(int loginMemberNo, int memberFriendNo, int friendStatusNo) {
		String insertRelation = "INSERT INTO friend (member_No, memberfriend_No, friendStatus_No) VALUES(?, ?, ?)";
		Query query = this.sessionFactory.getCurrentSession().createNativeQuery(insertRelation);
		query.setParameter(1, loginMemberNo);
		query.setParameter(2, memberFriendNo);
		query.setParameter(3, friendStatusNo);
		int i = 0;
		try {
			i = query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (i != 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteRelationFromList(int friendNo) {
		String deleteRelationFromList = "DELETE FROM friend WHERE friend_No=?";
		Query query = this.sessionFactory.getCurrentSession().createNativeQuery(deleteRelationFromList);
		query.setParameter(1, friendNo);
		int i = query.executeUpdate();
		if (i != 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteRelation(int loginMemberNo, int memberFriendNo) {
		String deleteRelation = "DELETE FROM friend WHERE member_No=? AND memberFriend_No=?";
		Query query = this.sessionFactory.getCurrentSession().createNativeQuery(deleteRelation);
		query.setParameter(1, loginMemberNo);
		query.setParameter(2, memberFriendNo);
		int i = query.executeUpdate();
		if (i != 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updateRelation(int friendNo, int friendStatusNo) {
		String updateRelation = "UPDATE friend SET friendStatus_No=? WHERE friend_No=?";
		Query query = this.sessionFactory.getCurrentSession().createNativeQuery(updateRelation);
		query.setParameter(1, friendStatusNo);
		query.setParameter(2, friendNo);

		int i = query.executeUpdate();
		if (i != 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<FriendBean> selectMember(int loginMemberNo, String words) {
		String selectMember = "selectMember @memberNo=?, @words=?";
		List<FriendBean> result = null;
		Query<Object[]> query = this.sessionFactory.getCurrentSession().createNativeQuery(selectMember);
		query.setParameter(1, loginMemberNo);
		query.setParameter(2, words);
		List<Object[]> list = query.getResultList();
		result = new ArrayList<FriendBean>();
		for (Object[] obj : list) {
			FriendBean result1 = new FriendBean();
			result1.setFriendNo((Integer) obj[0]);
			result1.setFdMemberNo((Integer) obj[1]);
			result1.setMemberFriendNo((Integer) obj[2]);
			result1.setFriendStatusNo((Integer) obj[3]);
			result1.setMemberNo((Integer) obj[4]);
			result1.setMemberAccount((String) obj[5]);
			result1.setMemberName((String) obj[6]);
			result1.setMemberNickName((String) obj[7]);
			result1.setMemberPic((String) obj[8]);
			result.add(result1);
		}
		return result;
	}

	@Override
	public FriendBean selectRelation(int loginMemberNo, int urlMemberNo) {
		String selectRelation = "selectRelation @myMemberNo=?, @memberNo=?";
		FriendBean result = null;
		Query<Object[]> query = this.sessionFactory.getCurrentSession().createNativeQuery(selectRelation);
		query.setParameter(1, loginMemberNo);
		query.setParameter(2, urlMemberNo);
		Object[] obj=query.getSingleResult();
		result=new FriendBean();
		result.setFriendNo((Integer)obj[0]);
		result.setFdMemberNo((Integer)obj[1]);
		result.setMemberFriendNo((Integer) obj[2]);
		result.setFriendStatusNo((Integer) obj[3]);
		result.setMemberNo((Integer) obj[4]);
		result.setMemberAccount((String) obj[5]);
		result.setMemberName((String) obj[6]);
		result.setMemberNickName((String) obj[7]);
		result.setMemberPic((String) obj[8]);
		return result;
	}
}
