package member.model;

import java.util.HashSet;
import java.util.Set;

import wish.model.WishMsgBean;

public class MemberBean {
	private Integer memberNo;
	private Integer memberStatus;
	private String memberAccount;
	private String memberName;
	private String memberPassword;
	private String memberNickName;
	private java.util.Date memberBirth;
	private String memberPhone;
	private String memberAddress;
	private String memberPic;
	private Integer groupAttendanceTotalSuccess;
	private Integer groupAttendanceTotalQt;
	private Double groupCredit;
//	private Set<WishMsgBean> msgs = new HashSet<WishMsgBean>();

	public Integer getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(Integer memberNo) {
		this.memberNo = memberNo;
	}

	public Integer getMemberStatus() {
		return memberStatus;
	}

	public void setMemberStatus(Integer memberStatus) {
		this.memberStatus = memberStatus;
	}

	public String getMemberAccount() {
		return memberAccount;
	}

	public void setMemberAccount(String memberAccount) {
		this.memberAccount = memberAccount;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberPassword() {
		return memberPassword;
	}

	public void setMemberPassword(String memberPassword) {
		this.memberPassword = memberPassword;
	}

	public String getMemberNickName() {
		return memberNickName;
	}

	public void setMemberNickName(String memberNickName) {
		this.memberNickName = memberNickName;
	}

	public java.util.Date getMemberBirth() {
		return memberBirth;
	}

	public void setMemberBirth(java.util.Date memberBirth) {
		this.memberBirth = memberBirth;
	}

	public String getMemberPhone() {
		return memberPhone;
	}

	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}

	public String getMemberAddress() {
		return memberAddress;
	}

	public void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
	}

	public String getMemberPic() {
		return memberPic;
	}

	public void setMemberPic(String memberPic) {
		this.memberPic = memberPic;
	}

	public Integer getGroupAttendanceTotalSuccess() {
		return groupAttendanceTotalSuccess;
	}

	public void setGroupAttendanceTotalSuccess(Integer groupAttendanceTotalSuccess) {
		this.groupAttendanceTotalSuccess = groupAttendanceTotalSuccess;
	}

	public Integer getGroupAttendanceTotalQt() {
		return groupAttendanceTotalQt;
	}

	public void setGroupAttendanceTotalQt(Integer groupAttendanceTotalQt) {
		this.groupAttendanceTotalQt = groupAttendanceTotalQt;
	}

	public Double getGroupCredit() {
		return groupCredit;
	}

	public void setGroupCredit(Double groupCredit) {
		this.groupCredit = groupCredit;
	}

//	public Set<WishMsgBean> getMsgs() {
//		return msgs;
//	}
//
//	public void setMsgs(Set<WishMsgBean> msgs) {
//		this.msgs = msgs;
//	}

}	

