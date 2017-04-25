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

import eeit9212.model.OrderInfoService;

@WebServlet("/eeit9212/grouprecord/updateajax")
public class UpdateAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderInfoService orderInfoService;

	@Override
	public void init() throws ServletException {
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		orderInfoService = (OrderInfoService) context.getBean("orderInfoService");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		String orderInfoNoTemp = request.getParameter("orderInfoNo");
		String packageNo = request.getParameter("packageNo");
		String locationFrom = request.getParameter("locationFrom");
		int orderInfoNo = -1;
		if (orderInfoNoTemp != null && orderInfoNoTemp.length() != 0) {
			try {
				orderInfoNo = Integer.parseInt(orderInfoNoTemp);
			} catch (Exception e) {
				System.out.println("orderInfoNo轉型失敗");
			}
		}
		PrintWriter out = response.getWriter();
		if ("receivePayMoney".equals(locationFrom)) {			
				orderInfoService.updateOrderInfoStatus(1105, orderInfoNo);
				out.write("success");
			return;
		}
		if (packageNo != null && packageNo.length() != 0) {
			orderInfoService.updatePackageNo(packageNo, orderInfoNo);
			orderInfoService.updateOrderInfoStatus(1203, orderInfoNo);
			out.write("success");
			return;
			// 可能要在這寫呼叫站內信的功能
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
