package eeit9212.model;

import java.sql.Blob;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;

public class AttendGroupInfoBean {
	private Integer groupInfoNo;
	private Integer orderInfoNo;
	private Integer groupInfoMemberNo;
	private String memberName;
	private Integer groupStatusNo;
	private String groupStatus;
	private String productType;
	private String groupInfoName;
	private Integer groupInfoMinProductQt;
	private Integer groupInfoTotalProductQt;
	private java.sql.Timestamp groupInfoStartDate;
	private java.sql.Timestamp groupInfoDeadLine;
	private String groupInfoContent;
	private String groupInfoShippingWay;
	private String groupInfoBankAccount;
	private Double grouperCredit;
	private Integer orderStatusNo;
	private String orderStatus;
	private Blob groupInfoCoverPic;
	private String formatDeadLine;
	private String formatGrouperCredit;
	public String getFormatDeadLine() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return formatDeadLine = format.format(getGroupInfoDeadLine());
	}

	public void setFormatDeadLine() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		this.formatDeadLine = format.format(getGroupInfoDeadLine());
	}
	public String getFormatGrouperCredit() {
		DecimalFormat format = new DecimalFormat("0.0");
		if (grouperCredit != null) {
			String formatDouble = format.format(grouperCredit);			
				return formatDouble;		
		}
		else{
			return "首次開團";
		}
	}
	public void setFormatGrouperCredit() {
		DecimalFormat format = new DecimalFormat("0.0");
		if (grouperCredit != null) {
			String formatDouble = format.format(grouperCredit);			
				this.formatGrouperCredit=formatDouble;		
		}
		else{
			this.formatGrouperCredit="主揪首次開團";
		}
	}

	@Override
	public String toString() {
		return "GroupInfoBean [groupInfoNo=" + groupInfoNo + ", orderInfoNo=" + orderInfoNo + ", groupInfoMemberNo="
				+ groupInfoMemberNo + ", memberName=" + memberName + ", groupStatusNo=" + groupStatusNo
				+ ", groupStatus=" + groupStatus + ", productType=" + productType + ", groupInfoName=" + groupInfoName
				+ ", groupInfoMinProductQt=" + groupInfoMinProductQt + ", groupInfoTotalProductQt="
				+ groupInfoTotalProductQt + ", groupInfoStartDate=" + groupInfoStartDate + ", groupInfoDeadLine="
				+ groupInfoDeadLine + ", groupInfoContent=" + groupInfoContent + ", groupInfoShippingWay="
				+ groupInfoShippingWay + ", groupInfoBankAccount=" + groupInfoBankAccount + ", grouperCredit="
				+ grouperCredit + ", orderStatusNo=" + orderStatusNo + ", orderStatus=" + orderStatus
				+ ", groupInfoCoverPic=" + groupInfoCoverPic + "]";
	}

	public Integer getGroupInfoNo() {
		return groupInfoNo;
	}

	public void setGroupInfoNo(Integer groupInfoNo) {
		this.groupInfoNo = groupInfoNo;
	}

	public Integer getOrderInfoNo() {
		return orderInfoNo;
	}

	public void setOrderInfoNo(Integer orderInfoNo) {
		this.orderInfoNo = orderInfoNo;
	}

	public Integer getGroupInfoMemberNo() {
		return groupInfoMemberNo;
	}

	public void setGroupInfoMemberNo(Integer groupInfoMemberNo) {
		this.groupInfoMemberNo = groupInfoMemberNo;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public Integer getGroupStatusNo() {
		return groupStatusNo;
	}

	public void setGroupStatusNo(Integer groupStatusNo) {
		this.groupStatusNo = groupStatusNo;
	}

	public String getGroupStatus() {
		return groupStatus;
	}

	public void setGroupStatus(String groupStatus) {
		this.groupStatus = groupStatus;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getGroupInfoName() {
		return groupInfoName;
	}

	public void setGroupInfoName(String groupInfoName) {
		this.groupInfoName = groupInfoName;
	}

	public Integer getGroupInfoMinProductQt() {
		return groupInfoMinProductQt;
	}

	public void setGroupInfoMinProductQt(Integer groupInfoMinProductQt) {
		this.groupInfoMinProductQt = groupInfoMinProductQt;
	}

	public Integer getGroupInfoTotalProductQt() {
		return groupInfoTotalProductQt;
	}

	public void setGroupInfoTotalProductQt(Integer groupInfoTotalProductQt) {
		this.groupInfoTotalProductQt = groupInfoTotalProductQt;
	}

	public java.sql.Timestamp getGroupInfoStartDate() {
		return groupInfoStartDate;
	}

	public void setGroupInfoStartDate(java.sql.Timestamp groupInfoStartDate) {
		this.groupInfoStartDate = groupInfoStartDate;
	}

	public java.sql.Timestamp getGroupInfoDeadLine() {
		return groupInfoDeadLine;
	}

	public void setGroupInfoDeadLine(java.sql.Timestamp groupInfoDeadLine) {
		this.groupInfoDeadLine = groupInfoDeadLine;
	}

	public String getGroupInfoContent() {
		return groupInfoContent;
	}

	public void setGroupInfoContent(String groupInfoContent) {
		this.groupInfoContent = groupInfoContent;
	}

	public String getGroupInfoShippingWay() {
		return groupInfoShippingWay;
	}

	public void setGroupInfoShippingWay(String groupInfoShippingWay) {
		this.groupInfoShippingWay = groupInfoShippingWay;
	}

	public String getGroupInfoBankAccount() {
		return groupInfoBankAccount;
	}

	public void setGroupInfoBankAccount(String groupInfoBankAccount) {
		this.groupInfoBankAccount = groupInfoBankAccount;
	}

	public Double getGrouperCredit() {
		return grouperCredit;
	}

	public void setGrouperCredit(Double grouperCredit) {
		this.grouperCredit = grouperCredit;
	}

	public Integer getOrderStatusNo() {
		return orderStatusNo;
	}

	public void setOrderStatusNo(Integer orderStatusNo) {
		this.orderStatusNo = orderStatusNo;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Blob getGroupInfoCoverPic() {
		return groupInfoCoverPic;
	}

	public void setGroupInfoCoverPic(Blob groupInfoCoverPic) {
		this.groupInfoCoverPic = groupInfoCoverPic;
	}

}
