package eeit9212.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import eeit9212.model.AttendGroupInfoBean;
import eeit9212.model.GroupInfoService;
import eeit9212.model.CreditAttendanceService;
import eeit9212.model.OrderInfoService;
import login.model.MemberBean;

@WebServlet("/eeit9212/grouprecord/checkorderajax")
public class CheckOrderAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderInfoService orderInfoService;

	@Override
	public void init() throws ServletException {
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());	
		orderInfoService=(OrderInfoService)context.getBean("orderInfoService");
	
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 
		String orderInfoStatus = request.getParameter("orderInfoStatus");
		String orderInfoNoTemp = request.getParameter("orderInfoNo");
		System.out.println("orderInfoStatus=" + orderInfoStatus);
		System.out.println("orderInfoNoTemp=" + orderInfoNoTemp);
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		int orderInfoNo = -1;
		if (orderInfoNoTemp != null && orderInfoNoTemp.length() != 0) {
			orderInfoNo = Integer.parseInt(orderInfoNoTemp);
			if ("reject".equals(orderInfoStatus)) {
				orderInfoService.updateOrderInfoStatus(1002, orderInfoNo);

			} else if ("accept".equals(orderInfoStatus)) {
				orderInfoService.updateOrderInfoStatus(1003, orderInfoNo);
			}
			
			out.write("success");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
