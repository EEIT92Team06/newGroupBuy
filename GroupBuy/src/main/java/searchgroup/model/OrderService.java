package searchgroup.model;

public class OrderService {
	private OrderDAO orderDAO;
	public OrderService(OrderDAO orderDAO) {
		this.orderDAO = orderDAO;
	}
	public void insertOrder(OrderBean orderBean){
		orderDAO.insert(orderBean);
	}
}
