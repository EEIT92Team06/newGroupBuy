package searchgroup.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="orderInfoDetails")
public class OrderDetailsBean {
	@Id
	@Column(name = "orderInfoDetails_No")
	@GeneratedValue(strategy = GenerationType.IDENTITY)   
	private int orderInfoDetailsNo;
	@ManyToOne 
	@JoinColumn(name ="orderInfo_No")
	private OrderBean orderbean;
	@Column(name="groupInfoDetails_No")
	private int groupInfoDetailsNo;
	@Column(name="orderInfoDetails_ProductQt")
	private int orderInfoDetailsProductQt;

	public OrderDetailsBean(){}

	public OrderDetailsBean(OrderBean orderbean, int groupInfoDetailsNo, int orderInfoDetailsProductQt) {
		super();
		this.orderbean = orderbean;
		this.groupInfoDetailsNo = groupInfoDetailsNo;
		this.orderInfoDetailsProductQt = orderInfoDetailsProductQt;
	}

	public int getOrderInfoDetailsNo() {
		return orderInfoDetailsNo;
	}

	public void setOrderInfoDetailsNo(int orderInfoDetailsNo) {
		this.orderInfoDetailsNo = orderInfoDetailsNo;
	}

	public OrderBean getOrderbean() {
		return orderbean;
	}

	public void setOrderbean(OrderBean orderbean) {
		this.orderbean = orderbean;
	}

	public int getGroupInfoDetailsNo() {
		return groupInfoDetailsNo;
	}

	public void setGroupInfoDetailsNo(int groupInfoDetailsNo) {
		this.groupInfoDetailsNo = groupInfoDetailsNo;
	}

	public int getOrderInfoDetailsProductQt() {
		return orderInfoDetailsProductQt;
	}

	public void setOrderInfoDetailsProductQt(int orderInfoDetailsProductQt) {
		this.orderInfoDetailsProductQt = orderInfoDetailsProductQt;
	}
	
	
	
}
