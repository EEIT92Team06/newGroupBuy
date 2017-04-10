package insertAnnounce.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="annoucement")
public class AnnouncementBean {
	@Id
	@Column(name = "siteMail_No")
	@GeneratedValue(strategy = GenerationType.IDENTITY)    
	private int siteMailNo;
	@Column(name="member_No")
	private int member_No;
	@Column(name="siteMailStatus_No")
	private int siteMailStatus_No;
	@Column(name="siteMail_Title")
	private String siteMail_Title;
	@Column(name="siteMail_Time")
	private Timestamp siteMail_Time;
	@Column(name="siteMail_Content")
	private String siteMail_Content;
	@Column(name="siteMail_Type")
	private String siteMail_Type;
	
	
	

	
	public AnnouncementBean(int member_No, int siteMailStatus_No, String siteMail_Title,
			Timestamp siteMail_Time, String siteMail_Content, String siteMail_Type) {
		super();
		this.member_No = member_No;
		this.siteMailStatus_No = siteMailStatus_No;
		this.siteMail_Title = siteMail_Title;
		this.siteMail_Time = siteMail_Time;
		this.siteMail_Content = siteMail_Content;
		this.siteMail_Type = siteMail_Type;
	}
	public int getSiteMailNo() {
		return siteMailNo;
	}
	public void setSiteMailNo(int siteMailNo) {
		this.siteMailNo = siteMailNo;
	}
	public int getMember_No() {
		return member_No;
	}
	public void setMember_No(int member_No) {
		this.member_No = member_No;
	}
	public int getSiteMailStatus_No() {
		return siteMailStatus_No;
	}
	public void setSiteMailStatus_No(int siteMailStatus_No) {
		this.siteMailStatus_No = siteMailStatus_No;
	}
	public String getSiteMail_Title() {
		return siteMail_Title;
	}
	public void setSiteMail_Title(String siteMail_Title) {
		this.siteMail_Title = siteMail_Title;
	}
	
	public Timestamp getSiteMail_Time() {
		return siteMail_Time;
	}
	public void setSiteMail_Time(Timestamp siteMail_Time) {
		this.siteMail_Time = siteMail_Time;
	}
	public String getSiteMail_Content() {
		return siteMail_Content;
	}
	public void setSiteMail_Content(String siteMail_Content) {
		this.siteMail_Content = siteMail_Content;
	}
	
	public String getSiteMail_Type() {
		return siteMail_Type;
	}
	public void setSiteMail_Type(String siteMail_Type) {
		this.siteMail_Type = siteMail_Type;
	}
	@Override
	public String toString() {
		return "AnnouncementBean [siteMailNo=" + siteMailNo + ", member_No=" + member_No + ", siteMailStatus_No="
				+ siteMailStatus_No + ", siteMail_Title=" + siteMail_Title + ", siteMail_Time=" + siteMail_Time
				+ ", siteMail_Content=" + siteMail_Content + "]";
	}
	
}
