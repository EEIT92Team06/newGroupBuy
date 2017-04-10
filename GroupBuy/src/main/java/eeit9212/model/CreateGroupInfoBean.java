package eeit9212.model;

import java.sql.Blob;
import java.util.Arrays;

public class CreateGroupInfoBean{
	private Integer groupInfoNo;
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
	private Blob groupInfoCoverPic;
	
	@Override
	public String toString() {
		return "CreateGroupInfoBean [groupInfoNo=" + groupInfoNo + ", groupInfoMemberNo=" + groupInfoMemberNo
				+ ", memberName=" + memberName + ", groupStatusNo=" + groupStatusNo + ", groupStatus=" + groupStatus
				+ ", productType=" + productType + ", groupInfoName=" + groupInfoName + ", groupInfoMinProductQt="
				+ groupInfoMinProductQt + ", groupInfoTotalProductQt=" + groupInfoTotalProductQt
				+ ", groupInfoStartDate=" + groupInfoStartDate + ", groupInfoDeadLine=" + groupInfoDeadLine
				+ ", groupInfoContent=" + groupInfoContent + ", groupInfoShippingWay=" + groupInfoShippingWay
				+ ", groupInfoBankAccount=" + groupInfoBankAccount + ", groupInfoCoverPic=" + groupInfoCoverPic + "]";
	}


	public Integer getGroupInfoNo() {
		return groupInfoNo;
	}

	public void setGroupInfoNo(Integer groupInfoNo) {
		this.groupInfoNo = groupInfoNo;
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

	public Blob getGroupInfoCoverPic() {
		return groupInfoCoverPic;
	}

	public void setGroupInfoCoverPic(Blob groupInfoCoverPic) {
		this.groupInfoCoverPic = groupInfoCoverPic;
	}
	
	
}
