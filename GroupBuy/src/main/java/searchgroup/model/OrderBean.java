package searchgroup.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="orderInfo")
public class OrderBean {
	@Id
	@Column(name = "orderInfo_No")
	@GeneratedValue(strategy = GenerationType.IDENTITY)    
	private int orderInfoNo;
	@Column(name="groupInfo_No")
	private int groupInfoNo;
	@Column(name="member_No")
	private int memberNo;
	@Column(name="orderInfoStatus_No")
	private int orderInfoStatusNo;
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="orderbean")
	private Set<OrderDetailsBean> orderDetails = new HashSet<OrderDetailsBean>();
	
	
	
	public OrderBean() {
	}
	public OrderBean(int groupInfoNo, int memberNo, int orderInfoStatusNo, Set<OrderDetailsBean> orderDetails) {
		this.groupInfoNo = groupInfoNo;
		this.memberNo = memberNo;
		this.orderInfoStatusNo = orderInfoStatusNo;
		this.orderDetails = orderDetails;
	}
	public int getOrderInfoNo() {
		return orderInfoNo;
	}
	public void setOrderInfoNo(int orderInfoNo) {
		this.orderInfoNo = orderInfoNo;
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
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public int getOrderInfoStatusNo() {
		return orderInfoStatusNo;
	}
	public void setOrderInfoStatusNo(int orderInfoStatusNo) {
		this.orderInfoStatusNo = orderInfoStatusNo;
	}
	public Set<OrderDetailsBean> getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(Set<OrderDetailsBean> orderDetails) {
		this.orderDetails = orderDetails;
	}
	@Override
	public String toString() {
		return "OrderBean [orderInfoNo=" + orderInfoNo + ", groupInfoNo=" + groupInfoNo + ", memberNo=" + memberNo
				+ ", orderInfoStatusNo=" + orderInfoStatusNo + ", orderDetails=" + orderDetails + "]";
	}
	
	
	


	
	
	
}
