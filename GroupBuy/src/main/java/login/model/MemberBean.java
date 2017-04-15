package login.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="member")
public class MemberBean {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="member_No")
	private Integer memberNo;
	@Column(name="member_Status")
	private Integer memberStatus;
	@Column(name="member_Account")
	private String memberAccount;
	@Column(name="member_Name")
	private String memberName;
	@Column(name="member_Password")
	private String memberPassword;
	@Column(name="member_NickName")
	private String memberNickName;
	@Column(name="member_Birth")
	private java.util.Date memberBirth;
	@Column(name="member_Phone")
	private String memberPhone;
	@Column(name="member_Address")
	private String memberAddress;
	@Column(name="member_Pic")
	private String memberPic;

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
	@Override
	public String toString() {
		return "MemberBean [memberNo=" + memberNo + ", memberStatus=" + memberStatus + ", memberAccount="
				+ memberAccount + ", memberName=" + memberName + ", memberPassword=" + memberPassword
				+ ", memberNickName=" + memberNickName + ", memberBirth=" + memberBirth + ", memberPhone=" + memberPhone
				+ ", memberAddress=" + memberAddress + ", memberPic=" + memberPic + "]";
	}

	
}
