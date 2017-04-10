package model;

import java.util.List;

public interface FriendDAO {

	//查詢會員(array)
//	List<String[]> selectMember1(String words);

	//查該會員與我的關係(到該會員頁面時用MemberServlet)
	FriendBean selectRelation(int loginMemberNo, int memberNo);
	
	//查詢會員
	List<FriendBean> selectMember(int loginMemberNo, String words);
	
	//查詢名單：送出的邀請、封鎖、好友
	//SELECT * FROM friend WHERE member_No=? AND friendStatus_No=?(2101/2102/2103)
	List<FriendBean> selectRelationList(int loginMemberNo, int friendStatusNo);//--得到fdNo, 抓memberFriendNo
	//查詢名單：收到的邀請
	//SELECT * FROM friend WHERE memberFriend_No=? AND friendStatus_No=?(2103)(2102)
	List<FriendBean> selectRequestedList(int memberFriendNo, int friendStatusNo);//--得到fdNo, 抓memberNo
	

	//新增：送出的邀請、封鎖陌生人、成為好友(a)
	//INSERT INTO friend VALUES(?, ?, ?)(2103/2102/2101) (我)(他)
	boolean insertRelation(int loginMemberNo, int memberFriendNo, int friendStatusNo);
	
	//刪除：送出的邀請、收到的邀請、刪除封鎖、刪除好友(getMemberFriendNo,deleteRelation)
	//DELETE FROM friend WHERE friend_No=?
	boolean deleteRelationFromList(int friendNo);//--delete fdNo + 得到memberFriendNo,塞至delete指令的memberNo
	
	//刪除對方對我的關係：刪除對方好友、封鎖好友
	//DELETE FROM friend WHERE memberNo=? and memberFriendNo=?(好友)(我)
	boolean deleteRelation(int loginMemberNo, int memberFriendNo);
	
	//更新關係：接受好友邀請
	//UPDATE friend SET friendStatusNo=?(2101) where friendNo=?(x)+ insertRelation
	boolean updateRelation(int friendNo, int friendStatusNo);
	//更新關係：封鎖好友
	//UPDATE friend SET friendStatusNo=?(2102) where friendNo=?(x)+deleteRelation
	
	

}
