package eeit9212.model;

public class OrderInfoBean {

	private Integer orderInfoNo;
	private Integer memberNo;
	private String memberName;
	private Integer groupAttendanceTotalSuccess;
	private Integer groupAttendanceTotalQt;
	private Double orderInfoPriceTotal;
	private Integer orderInfoStatusNo;
	private String orderInfoStatus;
	private String orderInfoAfterSuccessPackageNo;
	private java.sql.Timestamp orderInfoAfterSuccessPayTime;
	private String orderInfoAfterSuccessPhone;
	private String orderInfoAfterSuccessDestination;
	private String orderInfoAfterSuccessBankAccount;

	@Override
	public String toString() {
		return "OrderInfoBean [orderInfoNo=" + orderInfoNo + ", memberNo=" + memberNo + ", memberName=" + memberName
				+ ", groupAttendanceTotalSuccess=" + groupAttendanceTotalSuccess + ", groupAttendanceTotalQt="
				+ groupAttendanceTotalQt + ", orderInfoPriceTotal=" + orderInfoPriceTotal + ", orderInfoStatusNo="
				+ orderInfoStatusNo + ", orderInfoStatus=" + orderInfoStatus + ", orderInfoAfterSuccessPackageNo="
				+ orderInfoAfterSuccessPackageNo + ", orderInfoAfterSuccessPayTime=" + orderInfoAfterSuccessPayTime
				+ ", orderInfoAfterSuccessPhone=" + orderInfoAfterSuccessPhone + ", orderInfoAfterSuccessDestination="
				+ orderInfoAfterSuccessDestination + ", orderInfoAfterSuccessBankAccount="
				+ orderInfoAfterSuccessBankAccount + "]";
	}

	public Integer getOrderInfoNo() {
		return orderInfoNo;
	}

	public void setOrderInfoNo(Integer orderInfoNo) {
		this.orderInfoNo = orderInfoNo;
	}

	public Integer getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(Integer memberNo) {
		this.memberNo = memberNo;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public Integer getGroupAttendanceTotalSuccess() {
		return groupAttendanceTotalSuccess;
	}

	public void setGroupAttendanceTotalSuccess(Integer groupAttendanceTotalSuccess) {
		this.groupAttendanceTotalSuccess = groupAttendanceTotalSuccess;
	}

	public Integer getGroupAttendanceTotalQt() {
		return groupAttendanceTotalQt;
	}

	public void setGroupAttendanceTotalQt(Integer groupAttendanceTotalQt) {
		this.groupAttendanceTotalQt = groupAttendanceTotalQt;
	}

	public Double getOrderInfoPriceTotal() {
		return orderInfoPriceTotal;
	}

	public void setOrderInfoPriceTotal(Double orderInfoPriceTotal) {
		this.orderInfoPriceTotal = orderInfoPriceTotal;
	}

	public Integer getOrderInfoStatusNo() {
		return orderInfoStatusNo;
	}

	public void setOrderInfoStatusNo(Integer orderInfoStatusNo) {
		this.orderInfoStatusNo = orderInfoStatusNo;
	}

	public String getOrderInfoStatus() {
		return orderInfoStatus;
	}

	public void setOrderInfoStatus(String orderInfoStatus) {
		this.orderInfoStatus = orderInfoStatus;
	}

	public String getOrderInfoAfterSuccessPackageNo() {
		return orderInfoAfterSuccessPackageNo;
	}

	public void setOrderInfoAfterSuccessPackageNo(String orderInfoAfterSuccessPackageNo) {
		this.orderInfoAfterSuccessPackageNo = orderInfoAfterSuccessPackageNo;
	}

	public java.sql.Timestamp getOrderInfoAfterSuccessPayTime() {
		return orderInfoAfterSuccessPayTime;
	}

	public void setOrderInfoAfterSuccessPayTime(java.sql.Timestamp orderInfoAfterSuccessPayTime) {
		this.orderInfoAfterSuccessPayTime = orderInfoAfterSuccessPayTime;
	}

	public String getOrderInfoAfterSuccessPhone() {
		return orderInfoAfterSuccessPhone;
	}

	public void setOrderInfoAfterSuccessPhone(String orderInfoAfterSuccessPhone) {
		this.orderInfoAfterSuccessPhone = orderInfoAfterSuccessPhone;
	}

	public String getOrderInfoAfterSuccessDestination() {
		return orderInfoAfterSuccessDestination;
	}

	public void setOrderInfoAfterSuccessDestination(String orderInfoAfterSuccessDestination) {
		this.orderInfoAfterSuccessDestination = orderInfoAfterSuccessDestination;
	}

	public String getOrderInfoAfterSuccessBankAccount() {
		return orderInfoAfterSuccessBankAccount;
	}

	public void setOrderInfoAfterSuccessBankAccount(String orderInfoAfterSuccessBankAccount) {
		this.orderInfoAfterSuccessBankAccount = orderInfoAfterSuccessBankAccount;
	}

}
