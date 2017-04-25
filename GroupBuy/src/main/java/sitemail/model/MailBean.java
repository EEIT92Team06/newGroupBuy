package sitemail.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="mail")
public class MailBean {
	@Id
	@Column(name="siteMail_No")
	private int siteMailNo;
	@Column(name="member_No")
	private int memberNo;
	@Column(name="siteMailStatus_No")
	private int siteMailStatusNo;
	@Column(name="siteMail_Time")
	private java.sql.Timestamp siteMailTime;
	@Column(name="siteMailCan_No")
	private int siteMailCanNo;
	@Column(name="siteMailCan_Title")
	private String siteMailCanTitle;
	@Column(name="siteMailCan_Content")
	private String siteMailCanContent;
	
	public int getSiteMailNo() {
		return siteMailNo;
	}
	public void setSiteMailNo(int siteMailNo) {
		this.siteMailNo = siteMailNo;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public int getSiteMailStatusNo() {
		return siteMailStatusNo;
	}
	public void setSiteMailStatusNo(int siteMailStatusNo) {
		this.siteMailStatusNo = siteMailStatusNo;
	}
	public java.sql.Timestamp getSiteMailTime() {
		return siteMailTime;
	}
	public void setSiteMailTime(java.sql.Timestamp siteMailTime) {
		this.siteMailTime = siteMailTime;
	}
	public int getSiteMailCanNo() {
		return siteMailCanNo;
	}
	public void setSiteMailCanNo(int siteMailCanNo) {
		this.siteMailCanNo = siteMailCanNo;
	}
	public String getSiteMailCanTitle() {
		return siteMailCanTitle;
	}
	public void setSiteMailCanTitle(String siteMailCanTitle) {
		this.siteMailCanTitle = siteMailCanTitle;
	}
	public String getSiteMailCanContent() {
		return siteMailCanContent;
	}
	public void setSiteMailCanContent(String siteMailCanContent) {
		this.siteMailCanContent = siteMailCanContent;
	}
	@Override
	public String toString() {
		return "MailBean [siteMailNo=" + siteMailNo + ", memberNo=" + memberNo + ", siteMailStatusNo="
				+ siteMailStatusNo + ", siteMailTime=" + siteMailTime + ", siteMailCanNo=" + siteMailCanNo
				+ ", siteMailCanTitle=" + siteMailCanTitle + ", siteMailCanContent=" + siteMailCanContent + "]";
	}

}
