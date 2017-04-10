package model;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FriendService {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("eeit9211/beans.config.xml");
		FriendService friendService = (FriendService) context.getBean("friendService");
		 List<FriendBean> result = friendService.selectMember(1, "2");
		 System.out.println(result);
		((ConfigurableApplicationContext) context).close();
	}

	private FriendDAO friendDAO;

	public FriendService(FriendDAO friendDAO) {
		this.friendDAO = friendDAO;
	}

	// 查友誼關係
	public FriendBean selectRelation(int loginMemberNo, int urlMemberNo){
		FriendBean result=friendDAO.selectRelation(loginMemberNo, urlMemberNo);
		return result;
	}
	
	// 查詢會員
	public List<FriendBean> selectMember(int loginMemberNo, String words) {
		List<FriendBean> result = friendDAO.selectMember(loginMemberNo, words);
		return result;
	}

	// 查詢名單：送出的邀請、封鎖、好友
	public List<FriendBean> selectRelationList(int loginMemberNo, int friendStatusNo) {
		List<FriendBean> result = friendDAO.selectRelationList(loginMemberNo, friendStatusNo);
		return result;
	}

	// 查詢名單：收到的邀請
	public List<FriendBean> selectRequestedList(int memberFriendNo, int friendStatusNo) {
		List<FriendBean> result = friendDAO.selectRequestedList(memberFriendNo, friendStatusNo);
		return result;
	}

	// 刪除：送出的邀請、收到的邀請、刪除封鎖
	public boolean deleteRelationFromList(int friendNo) {
		boolean result = friendDAO.deleteRelationFromList(friendNo);
		return result;
	}

	// 刪除好友
	public boolean deleteFriend(int friendNo, int loginMemberNo, int memberFriendNo) {
		boolean flag = false;
		flag = friendDAO.deleteRelationFromList(friendNo);
		if (flag == true) {
			flag = friendDAO.deleteRelation(loginMemberNo, memberFriendNo);
		}
		return flag;
	}

	// 封鎖好友
	public boolean blockFriend(int friendNo, int loginMemberNo, int memberFriendNo) {
		boolean flag = false;
		flag = friendDAO.updateRelation(friendNo, 2102);
		if (flag == true) {
			flag = friendDAO.deleteRelation(loginMemberNo, memberFriendNo);
		}
		return flag;
	}
	// 送出邀請
	public boolean requestFriend(int loginMemberNo, int memberFriendNo){
		boolean flag= false;
		flag= friendDAO.insertRelation(loginMemberNo, memberFriendNo, 2103);
		return flag;
	}
	// 封鎖陌生人
	public boolean blockMember(int loginMemberNo, int memberFriendNo) {
		boolean flag = false;
		flag = friendDAO.insertRelation(loginMemberNo, memberFriendNo, 2102);
		return flag;
	}

	// 成為好友
	public boolean acceptRequested(int friendNo, int loginMemberNo, int memberFriendNo) {
		boolean flag = false;
		flag = friendDAO.updateRelation(friendNo, 2101);
		if (flag == true) {
			flag = friendDAO.insertRelation(loginMemberNo, memberFriendNo, 2101);
		}
		return flag;
	}

}
