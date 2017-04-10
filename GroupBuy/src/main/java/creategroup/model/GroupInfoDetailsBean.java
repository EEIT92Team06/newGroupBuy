package creategroup.model;


import java.io.Serializable;

public class GroupInfoDetailsBean  implements Serializable{

	private int groupInfoDetailsNo;
	private GroupInfoBean groupInfoNo;
	private String groupInfoDetailsProdcutName;
	private double groupInfoDetailsProductPrice;
	public int getGroupInfoDetailsNo() {
		return groupInfoDetailsNo;
	}
	public void setGroupInfoDetailsNo(int groupInfoDetailsNo) {
		this.groupInfoDetailsNo = groupInfoDetailsNo;
	}
	public GroupInfoBean getGroupInfoNo() {
		return groupInfoNo;
	}
	public void setGroupInfoNo(GroupInfoBean groupInfoNo) {
		this.groupInfoNo = groupInfoNo;
	}
	public String getGroupInfoDetailsProdcutName() {
		return groupInfoDetailsProdcutName;
	}
	public void setGroupInfoDetailsProdcutName(String groupInfoDetailsProdcutName) {
		this.groupInfoDetailsProdcutName = groupInfoDetailsProdcutName;
	}
	public double getGroupInfoDetailsProductPrice() {
		return groupInfoDetailsProductPrice;
	}
	public void setGroupInfoDetailsProductPrice(double groupInfoDetailsProductPrice) {
		this.groupInfoDetailsProductPrice = groupInfoDetailsProductPrice;
	}

	

}
