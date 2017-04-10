package model;

public class MemberBean {
	private Integer memberNo;
	private String memberStatus;
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
	
	public Integer getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(Integer memberNo) {
		this.memberNo = memberNo;
	}
	public String getMemberStatus() {
		return memberStatus;
	}
	public void setMemberStatus(String memberStatus) {
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
	public String getMemberPassword() {
		return memberPassword;
	}
	public void setMemberPassword(String memberPassword) {
		this.memberPassword = memberPassword;
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
	@Override
	public String toString() {
		return "MemberBean [memberNo=" + memberNo + ", memberStatus=" + memberStatus + ", memberAccount="
				+ memberAccount + ", memberName=" + memberName + ", memberPassword=" + memberPassword
				+ ", memberNickName=" + memberNickName + ", memberBirth=" + memberBirth + ", memberPhone=" + memberPhone
				+ ", memberAddress=" + memberAddress + ", memberPic=" + memberPic + ", groupAttendanceTotalSuccess="
				+ groupAttendanceTotalSuccess + ", groupAttendanceTotalQt=" + groupAttendanceTotalQt + ", groupCredit="
				+ groupCredit + "]";
	}
	
	
	
	
	
}
