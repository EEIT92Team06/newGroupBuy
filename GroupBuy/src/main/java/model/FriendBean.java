package model;

public class FriendBean {

	private Integer friendNo;
	private Integer fdMemberNo;
	private Integer memberFriendNo;
	private Integer friendStatusNo;

	private Integer memberNo;
	private String memberAccount;
	private String memberName;
	private String memberNickName;
	private String memberPic;
	private String memberStatus;
	private String memberAddress;
	private java.util.Date memberBirth;
	private String memberPhone;
	
	
	
	@Override
	public String toString() {
		return "FriendBean [friendNo=" + friendNo + ", fdMemberNo=" + fdMemberNo + ", memberFriendNo=" + memberFriendNo
				+ ", friendStatusNo=" + friendStatusNo + ", memberNo=" + memberNo + ", memberAccount=" + memberAccount
				+ ", memberName=" + memberName + ", memberNickName=" + memberNickName + ", memberPic=" + memberPic
				+ ", memberStatus=" + memberStatus + ", memberAddress=" + memberAddress + ", memberBirth=" + memberBirth
				+ ", memberPhone=" + memberPhone + "]\r";
	}
	
	public Integer getFriendNo() {
		return friendNo;
	}
	public void setFriendNo(Integer friendNo) {
		this.friendNo = friendNo;
	}
	public Integer getFdMemberNo() {
		return fdMemberNo;
	}
	public void setFdMemberNo(Integer fdMemberNo) {
		this.fdMemberNo = fdMemberNo;
	}
	public Integer getMemberFriendNo() {
		return memberFriendNo;
	}
	public void setMemberFriendNo(Integer memberFriendNo) {
		this.memberFriendNo = memberFriendNo;
	}
	public Integer getFriendStatusNo() {
		return friendStatusNo;
	}
	public void setFriendStatusNo(Integer friendStatusNo) {
		this.friendStatusNo = friendStatusNo;
	}
	public Integer getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(Integer memberNo) {
		this.memberNo = memberNo;
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
	public String getMemberNickName() {
		return memberNickName;
	}
	public void setMemberNickName(String memberNickName) {
		this.memberNickName = memberNickName;
	}
	public String getMemberPic() {
		return memberPic;
	}
	public void setMemberPic(String memberPic) {
		this.memberPic = memberPic;
	}
	public String getMemberStatus() {
		return memberStatus;
	}
	public void setMemberStatus(String memberStatus) {
		this.memberStatus = memberStatus;
	}
	public String getMemberAddress() {
		return memberAddress;
	}
	public void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
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
	
}
