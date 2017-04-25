package eeit9212.model;

import java.util.List;

public interface OrderInfoDAO {

	int updateOrderInfoStatusByOrderStatusNo(int groupInfoNo, int orderInfoStatusNo, int whereOrderInfoStatusNo);

	int updateOrderInfoStatusByGroupInfoNo(int groupInfoNo, int orderInfoStatusNo);

	int updatePackageNo(String packageNo, int orderInfoNo);

	int insertOrderInfoAfterSuccess(int orderInfoNo, String orderInfoAfterSuccessPhone,
			String orderInfoAfterSuccessDestination, String orderInfoAfterSuccessBankAccount);

	OrderInfoBean selectMyOrderInfoByNo(int orderInfoNo);

	OrderInfoBean updateOrderInfoStatus(int orderInfoStatusNo, int orderInfoNo);

	double selectTotalPrice(int orderInfoNo);

	List<OrderInfoBean> selectMyGroupOrderInfo(int groupInfoNo);

}