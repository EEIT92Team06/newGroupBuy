package sitemail.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="siteMailCan")
public class SiteMailCanBean {
	@Id
	@Column(name="siteMailCan_No")
	private int siteMailCanNo;
	@Column(name="siteMailCan_Title")
	private String siteMailCanTitle;
	@Column(name="siteMailCan_Content")
	private String siteMailCanContent;
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
		return "SiteMailCanBean [siteMailCanNo=" + siteMailCanNo + ", siteMailCanTitle=" + siteMailCanTitle
				+ ", siteMailCanContent=" + siteMailCanContent + "]";
	}
	
}
