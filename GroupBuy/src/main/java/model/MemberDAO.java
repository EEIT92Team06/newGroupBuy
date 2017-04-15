package model;

import java.util.List;

//import java.util.List;

public interface MemberDAO {
	
//	List<MemberBean> select();
	//查詢會員資料
	MemberBean selectMemberInfo(int memberNo);
	
	//修改會員密碼
	MemberBean updateMemberPassword(int memberNo, String memberPassword);
	
	//修改會員大頭貼
	MemberBean updateMemberPic(int memberNo, String memberPic);
	
	
	//修改會員資料
	MemberBean updateMemberInfo(int memberNo, String memberNickName, String memberAddress);
	
	//新增會員資料(後台)
//	MemberBean insert(MemberBean memberNo);
	//刪除會員資料(後台)
//	MemberBean delete(MemberBean memberNo);
	
}
