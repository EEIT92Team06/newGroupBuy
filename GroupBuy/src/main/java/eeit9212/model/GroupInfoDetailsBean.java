package eeit9212.model;

public class GroupInfoDetailsBean {

	private Integer groupInfoDetailsNo;
	private String groupInfoDetailsProdcutName;
	private Double groupInfoDetailsProductPrice;
	
	@Override
	public String toString() {
		return "GroupInfoDetailsBean [groupInfoDetailsNo=" + groupInfoDetailsNo + ", groupInfoDetailsProdcutName="
				+ groupInfoDetailsProdcutName + ", groupInfoDetailsProductPrice=" + groupInfoDetailsProductPrice + "]";
	}

	public Integer getGroupInfoDetailsNo() {
		return groupInfoDetailsNo;
	}

	public void setGroupInfoDetailsNo(Integer groupInfoDetailsNo) {
		this.groupInfoDetailsNo = groupInfoDetailsNo;
	}

	public String getGroupInfoDetailsProdcutName() {
		return groupInfoDetailsProdcutName;
	}

	public void setGroupInfoDetailsProdcutName(String groupInfoDetailsProdcutName) {
		this.groupInfoDetailsProdcutName = groupInfoDetailsProdcutName;
	}

	public Double getGroupInfoDetailsProductPrice() {
		return groupInfoDetailsProductPrice;
	}

	public void setGroupInfoDetailsProductPrice(Double groupInfoDetailsProductPrice) {
		this.groupInfoDetailsProductPrice = groupInfoDetailsProductPrice;
	}

	
	
}
