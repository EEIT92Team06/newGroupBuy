package eeit9212.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="orderInfoAfterSuccess")
public class OrderInfoAfterSuccessBean {
	
	@Id
	@Column(name="orderInfo_No")
	private Integer orderInfoNo;
	@Column(name="orderInfoAfterSuccess_PackageNo")
	private String orderInfoAfterSuccessPackageNo;
	@Column(name="orderInfoAfterSuccess_PayTime")
	private java.sql.Timestamp orderInfoAfterSuccessPayTime;
	@Column(name="orderInfoAfterSuccess_Phone")
	private String orderInfoAfterSuccessPhone;
	@Column(name="orderInfoAfterSuccess_Destination")
	private String orderInfoAfterSuccessDestination;
	@Column(name="orderInfoAfterSuccess_BankAccount")
	private String orderInfoAfterSuccessBankAccount;


	
	@Override
	public String toString() {
		return "OrderInfoAfterSuccessBean [orderInfoNo=" + orderInfoNo + ", orderInfoAfterSuccessPackageNo="
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
