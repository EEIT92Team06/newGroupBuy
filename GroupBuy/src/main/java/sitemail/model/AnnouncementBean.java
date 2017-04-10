package sitemail.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "annoucement")
public class AnnouncementBean {
  @Id
  @Column(name="siteMail_No")
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private int siteMailNo;
  @Column(name="member_No")
  private int memberNo;
  @Column(name="siteMailStatus_No")
  private int siteMailStatusNo;
  @Column(name="siteMail_Title")
  private String siteMailTitle;
  @Column(name="siteMail_Time")
  private java.sql.Timestamp siteMailTime;
  @Column(name="siteMail_Content")
  private String siteMailContent;
  @Column(name="siteMail_Type")
  private String siteMailType;
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
public String getSiteMailTitle() {
	return siteMailTitle;
}
public void setSiteMailTitle(String siteMailTitle) {
	this.siteMailTitle = siteMailTitle;
}
public java.sql.Timestamp getSiteMailTime() {
	return siteMailTime;
}
public void setSiteMailTime(java.sql.Timestamp siteMailTime) {
	this.siteMailTime = siteMailTime;
}
public String getSiteMailContent() {
	return siteMailContent;
}
public void setSiteMailContent(String siteMailContent) {
	this.siteMailContent = siteMailContent;
}
public String getSiteMailType() {
	return siteMailType;
}
public void setSiteMailType(String siteMailType) {
	this.siteMailType = siteMailType;
}
@Override
public String toString() {
	return "AnnouncementBean [siteMailNo=" + siteMailNo + ", memberNo=" + memberNo + ", siteMailStatusNo="
			+ siteMailStatusNo + ", siteMailTitle=" + siteMailTitle + ", siteMailTime=" + siteMailTime
			+ ", siteMailContent=" + siteMailContent + ", siteMailType=" + siteMailType + "]";
}

  
}
