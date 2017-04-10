package eeit9212.model;

import java.util.List;

public interface OrderInfoDetailsDAO {

	OrderInfoDetailsBean selectOneOrderInfoDetails(int orderInfoNo, int groupInfoDetailsNo);

	List<OrderInfoDetailsBean> selectOrderInfoDetails(int orderInfoNo);

}