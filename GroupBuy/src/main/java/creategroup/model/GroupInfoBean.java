package creategroup.model;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class GroupInfoBean {
	private int groupInfoNo;
	private int memberNo;
	private String memberAccount;
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
	private PicBean groupInfoCoverPic;
	private List<GroupInfoDetailsBean> groupInfoDetails;
	private List<PicBean> groupInfoPics;


	public List<PicBean> getGroupInfoPics() {
		return groupInfoPics;
	}
	public void setGroupInfoPicBean(List<PicBean> groupInfoPics) {
		this.groupInfoPics = groupInfoPics;
	}
	public List<GroupInfoDetailsBean> getGroupInfoDetails() {
		return groupInfoDetails;
	}
	public void setGroupInfoDetailsBean(List<GroupInfoDetailsBean> groupInfoDetails) {
		this.groupInfoDetails = groupInfoDetails;
	}

	public int getGroupInfoNo() {
		return groupInfoNo;
	}

	public void setGroupInfoNo(int groupInfoNo) {
		this.groupInfoNo = groupInfoNo;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public String getMemberAccount() {
		return memberAccount;
	}

	public void setMemberAccount(String memberAccount) {
		this.memberAccount = memberAccount;
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

	public PicBean getGroupInfoCoverPic() {
		return groupInfoCoverPic;
	}

	public void setGroupInfoCoverPic(PicBean groupInfoCoverPic) {
		this.groupInfoCoverPic = groupInfoCoverPic;
	}
	public int getGroupInfoClickTimes() {
		return groupInfoClickTimes;
	}
	public void setGroupInfoClickTimes(int groupInfoClickTimes) {
		this.groupInfoClickTimes = groupInfoClickTimes;
	}



}
