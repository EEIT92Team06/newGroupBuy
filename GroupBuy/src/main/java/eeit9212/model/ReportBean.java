package eeit9212.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="report")
public class ReportBean {
	@Id
	@Column(name="report_No")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer reportNo;
	@Column(name="member_No")
	private Integer memberNo;
	@Column(name="reportType_No")
	private Integer reportTypeNo;
	@Column(name="reportStatus_No")
	private Integer reportStatusNo;
	@Column(name="report_Target")
	private Integer reportTarget;
	@Column(name="report_Content")
	private String reportContent;
	
	
	@Override
	public String toString() {
		return "ReportBean [reportNo=" + reportNo + ", memberNo=" + memberNo + ", reportTypeNo=" + reportTypeNo
				+ ", reportStatusNo=" + reportStatusNo + ", reportTarget=" + reportTarget + ", reportContent="
				+ reportContent + "]";
	}
	
	public Integer getReportNo() {
		return reportNo;
	}
	public void setReportNo(Integer reportNo) {
		this.reportNo = reportNo;
	}
	public Integer getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(Integer memberNo) {
		this.memberNo = memberNo;
	}
	public Integer getReportTypeNo() {
		return reportTypeNo;
	}
	public void setReportTypeNo(Integer reportTypeNo) {
		this.reportTypeNo = reportTypeNo;
	}
	public Integer getReportStatusNo() {
		return reportStatusNo;
	}
	public void setReportStatusNo(Integer reportStatusNo) {
		this.reportStatusNo = reportStatusNo;
	}
	public Integer getReportTarget() {
		return reportTarget;
	}
	public void setReportTarget(Integer reportTarget) {
		this.reportTarget = reportTarget;
	}
	public String getReportContent() {
		return reportContent;
	}
	public void setReportContent(String reportContent) {
		this.reportContent = reportContent;
	}

	
}
