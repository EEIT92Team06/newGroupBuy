package eeit9212.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import eeit9212.model.OrderInfoBean;
import eeit9212.model.OrderInfoService;

@WebServlet("/eeit9212/grouprecord/aftersuccessajax")
public class AfterSuccessAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderInfoService orderInfoService;

	@Override
	public void init() throws ServletException {
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		orderInfoService = (OrderInfoService) context.getBean("orderInfoService");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String orderInfoAfterSuccessBankAccount = request.getParameter("account");
		String orderInfoAfterSuccessPhone = request.getParameter("phone");
		String orderInfoAfterSuccessDestination = request.getParameter("address");
		String orderInfoNoTemp = request.getParameter("orderInfoNo");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		int orderInfoNo = -1;
		if (orderInfoNoTemp != null && orderInfoNoTemp.length() != 0) {
			try {
				orderInfoNo = Integer.parseInt(orderInfoNoTemp);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		if (orderInfoAfterSuccessBankAccount != null && orderInfoAfterSuccessBankAccount.length() != 0) {
			System.out.println("從myattendedgroupinfo.jsp收到請求");
			OrderInfoBean insertAndUpdateTransfer = orderInfoService.insertAndUpdateTransfer(orderInfoNo, 1102,
					orderInfoAfterSuccessPhone, orderInfoAfterSuccessDestination, orderInfoAfterSuccessBankAccount);
			out.write(insertAndUpdateTransfer.getFormatPayTime());
			
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
