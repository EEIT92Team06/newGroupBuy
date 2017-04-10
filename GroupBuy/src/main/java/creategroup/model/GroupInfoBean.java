package creategroup.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.collection.internal.PersistentList;

public class GroupInfoBean implements Serializable{
	private int groupInfoNo;
	private int memberNo;
	private int groupStatusNo;
	private int productTypeNo;
	private String groupInfoName;
	private int groupInfoMinProductQt;
	private Timestamp groupInfoStartDate;
	private Timestamp groupInfoDeadLine;
	private String groupInfoContent;
	private String groupInfoShippingWay;
	private String groupInfoBankAccount;
	private int groupInfoClickTimes;
	private byte[] groupInfoCoverPic;
	private Set<GroupInfoDetailsBean> groupInfoDetails=new HashSet<GroupInfoDetailsBean>();
	private Set<GroupInfoPicBean> groupInfoPics=new HashSet<GroupInfoPicBean>();
	public int getGroupInfoNo() {
		return groupInfoNo;
	}
	public void setGroupInfoNo(int groupInfoNo) {
		this.groupInfoNo = groupInfoNo;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public int getGroupStatusNo() {
		return groupStatusNo;
	}
	public void setGroupStatusNo(int groupStatusNo) {
		this.groupStatusNo = groupStatusNo;
	}
	public int getProductTypeNo() {
		return productTypeNo;
	}
	public void setProductTypeNo(int productTypeNo) {
		this.productTypeNo = productTypeNo;
	}
	public String getGroupInfoName() {
		return groupInfoName;
	}
	public void setGroupInfoName(String groupInfoName) {
		this.groupInfoName = groupInfoName;
	}
	public int getGroupInfoMinProductQt() {
		return groupInfoMinProductQt;
	}
	public void setGroupInfoMinProductQt(int groupInfoMinProductQt) {
		this.groupInfoMinProductQt = groupInfoMinProductQt;
	}
	public Timestamp getGroupInfoStartDate() {
		return groupInfoStartDate;
	}
	public void setGroupInfoStartDate(Timestamp groupInfoStartDate) {
		this.groupInfoStartDate = groupInfoStartDate;
	}
	public Timestamp getGroupInfoDeadLine() {
		return groupInfoDeadLine;
	}
	public void setGroupInfoDeadLine(Timestamp groupInfoDeadLine) {
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
	public int getGroupInfoClickTimes() {
		return groupInfoClickTimes;
	}
	public void setGroupInfoClickTimes(int groupInfoClickTimes) {
		this.groupInfoClickTimes = groupInfoClickTimes;
	}
	public byte[] getGroupInfoCoverPic() {
		return groupInfoCoverPic;
	}
	public void setGroupInfoCoverPic(byte[] groupInfoCoverPic) {
		this.groupInfoCoverPic = groupInfoCoverPic;
	}
	public Set<GroupInfoDetailsBean> getGroupInfoDetails() {
		return groupInfoDetails;
	}
	public void setGroupInfoDetails(Set<GroupInfoDetailsBean> groupInfoDetails) {
		this.groupInfoDetails = groupInfoDetails;
	}
	public Set<GroupInfoPicBean> getGroupInfoPics() {
		return groupInfoPics;
	}
	public void setGroupInfoPics(Set<GroupInfoPicBean> groupInfoPics) {
		this.groupInfoPics = groupInfoPics;
	}
	@Override
	public String toString() {
		return "GroupInfoBean [groupInfoNo=" + groupInfoNo + ", memberNo=" + memberNo + ", groupStatusNo="
				+ groupStatusNo + ", productTypeNo=" + productTypeNo + ", groupInfoName=" + groupInfoName
				+ ", groupInfoMinProductQt=" + groupInfoMinProductQt + ", groupInfoStartDate=" + groupInfoStartDate
				+ ", groupInfoDeadLine=" + groupInfoDeadLine + ", groupInfoContent=" + groupInfoContent
				+ ", groupInfoShippingWay=" + groupInfoShippingWay + ", groupInfoBankAccount=" + groupInfoBankAccount
				+ ", groupInfoClickTimes=" + groupInfoClickTimes + ", groupInfoCoverPic="
				+ Arrays.toString(groupInfoCoverPic) + ", groupInfoDetails=" + groupInfoDetails + ", groupInfoPics="
				+ groupInfoPics + "]";
	}
	


}
