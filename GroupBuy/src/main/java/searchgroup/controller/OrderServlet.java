package searchgroup.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import searchgroup.model.OrderBean;
import searchgroup.model.OrderDetailsBean;
import searchgroup.model.OrderService;

/**
 * Servlet implementation class Order
 */

@WebServlet("/searchgroup/order.controller")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderService orderService;
	@Override
	public void init() throws ServletException {
		ServletContext application = this.getServletContext();
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(application);
		orderService = (OrderService)context.getBean("orderService");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("2222");
		//接收資料
		//轉換資料
		//封裝資料
		//List<OrderDetailsBean> list
		String[] groupInfoDetailsNo = request.getParameterValues("groupInfoDetailsNo");		
		String[] quantity = request.getParameterValues("quantity");
		/*
		OrderBean orderBean = new OrderBean();
		OrderDetailsBean orderDetailsBean1 = new OrderDetailsBean(orderBean, 6, 3);
		OrderDetailsBean orderDetailsBean2 = new OrderDetailsBean(orderBean, 7, 4);

		Set<OrderDetailsBean> orderDetails = new HashSet<OrderDetailsBean>();
		orderDetails.add(orderDetailsBean1);
		orderDetails.add(orderDetailsBean2);
		
		orderBean.setGroupInfoNo(1);
		orderBean.setMemberNo(5);
		orderBean.setOrderInfoStatusNo(1001);
		orderBean.setOrderDetails(orderDetails); 
		
		orderDAO.insert(orderBean);
		*/
		OrderBean orderBean = new OrderBean();
		Set<OrderDetailsBean> orderDetails = new HashSet<OrderDetailsBean>();
		
//		List<OrderDetailsBean> list = new ArrayList<OrderDetailsBean>();
		int counter = 0;
		for(int i =0 ; i<groupInfoDetailsNo.length; i++){
			OrderDetailsBean OrderDetailsBean = new OrderDetailsBean(
					orderBean,Integer.parseInt(groupInfoDetailsNo[i]), Integer.parseInt(quantity[i]));
			orderDetails.add(OrderDetailsBean);
			if(Integer.parseInt(quantity[i])==0){
				counter++;
			}  
		}
		String contextPath = getServletContext().getContextPath();
		//如果都選擇0的話
		HttpSession session = request.getSession();
		if(counter == groupInfoDetailsNo.length){
			session.setAttribute("orderFail", "fail");
			response.sendRedirect(response
					.encodeRedirectURL(contextPath+"/searchgroup/searchDetails.jsp"));
			return;
		}
		
		//OrderBean
		String groupInfoNo = request.getParameter("groupInfoNo");
		System.out.println("groupInfoNo" + groupInfoNo);
		String memberNo = request.getParameter("memberNo");
		System.out.println("memberNo : " + memberNo);
		int IntgroupInfoNo = Integer.parseInt(groupInfoNo);
		int IntmemberNo = Integer.parseInt(memberNo);
		orderBean.setGroupInfoNo(IntgroupInfoNo);
		orderBean.setMemberNo(IntmemberNo);
		orderBean.setOrderInfoStatusNo(1001);
		orderBean.setOrderDetails(orderDetails); 
		
		//將封裝好的資料insert進去訂單以及訂單明細
		orderService.insertOrder(orderBean);
		session.setAttribute("ordersuc", "suc");
		response.sendRedirect(response
				.encodeRedirectURL(contextPath+"/searchgroup/searchDetails.jsp"));
		return;

		
	}

}
