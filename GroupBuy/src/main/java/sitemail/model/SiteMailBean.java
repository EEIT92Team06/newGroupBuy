package sitemail.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="siteMail")
public class SiteMailBean {

	@Id
	@Column(name="siteMail_No")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int siteMailNo;
	@Column(name="member_No")
	private int memberNo;
	@Column(name="siteMailStatus_No")
	private int siteMailStatusNo;
	@Column(name="siteMailCan_No")
	private int siteMailCanNo;
	@Column(name="siteMail_Time")
	private java.sql.Timestamp siteMailTime;
	
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
	public int getSiteMailCanNo() {
		return siteMailCanNo;
	}
	public void setSiteMailCanNo(int siteMailCanNo) {
		this.siteMailCanNo = siteMailCanNo;
	}
	public java.sql.Timestamp getSiteMailTime() {
		return siteMailTime;
	}
	public void setSiteMailTime(java.sql.Timestamp siteMailTime) {
		this.siteMailTime = siteMailTime;
	}
	@Override
	public String toString() {
		return "SiteMailBean [siteMailNo=" + siteMailNo + ", memberNo=" + memberNo + ", siteMailStatusNo="
				+ siteMailStatusNo + ", siteMailCanNo=" + siteMailCanNo + ", siteMailTime=" + siteMailTime + "]";
	}
		

}
