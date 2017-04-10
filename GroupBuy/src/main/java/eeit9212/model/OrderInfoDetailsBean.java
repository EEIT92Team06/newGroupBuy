package eeit9212.model;

public class OrderInfoDetailsBean {

	private Integer orderInfoDetailsNo;
	private String groupInfoDetailsProdcutName;
	private Double productTotalPriceByQt;
	private Integer orderInfoDetailsProductQt;

	@Override
	public String toString() {
		return "OrderInfoDetailsBean [orderInfoDetailsNo=" + orderInfoDetailsNo + ", groupInfoDetailsProdcutName="
				+ groupInfoDetailsProdcutName + ", productTotalPriceByQt=" + productTotalPriceByQt
				+ ", orderInfoDetailsProductQt=" + orderInfoDetailsProductQt + "]";
	}

	public Integer getOrderInfoDetailsNo() {
		return orderInfoDetailsNo;
	}

	public void setOrderInfoDetailsNo(Integer orderInfoDetailsNo) {
		this.orderInfoDetailsNo = orderInfoDetailsNo;
	}

	public String getGroupInfoDetailsProdcutName() {
		return groupInfoDetailsProdcutName;
	}

	public void setGroupInfoDetailsProdcutName(String groupInfoDetailsProdcutName) {
		this.groupInfoDetailsProdcutName = groupInfoDetailsProdcutName;
	}

	public Double getProductTotalPriceByQt() {
		return productTotalPriceByQt;
	}

	public void setProductTotalPriceByQt(Double productTotalPriceByQt) {
		this.productTotalPriceByQt = productTotalPriceByQt;
	}

	public Integer getOrderInfoDetailsProductQt() {
		return orderInfoDetailsProductQt;
	}

	public void setOrderInfoDetailsProductQt(Integer orderInfoDetailsProductQt) {
		this.orderInfoDetailsProductQt = orderInfoDetailsProductQt;
	}

}
