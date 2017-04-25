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

import com.google.gson.Gson;

import eeit9212.model.AttendGroupInfoBean;
import eeit9212.model.CreateGroupInfoBean;
import eeit9212.model.GroupInfoService;
import eeit9212.model.CreditAttendanceService;
import eeit9212.model.OrderInfoService;
import login.model.MemberBean;

@WebServlet("/eeit9212/grouprecord/checkorderajax")
public class CheckOrderAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderInfoService orderInfoService;
	private GroupInfoService groupInfoService;
	@Override
	public void init() throws ServletException {
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());	
		orderInfoService=(OrderInfoService)context.getBean("orderInfoService");
		groupInfoService = (GroupInfoService) context.getBean("groupInfoService");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 HttpSession session=request.getSession();
		String orderInfoStatus = request.getParameter("orderInfoStatus");
		String orderInfoNoTemp = request.getParameter("orderInfoNo");
		String groupInfoNoTemp = request.getParameter("groupInfoNo");
		System.out.println("orderInfoStatus=" + orderInfoStatus);
		System.out.println("orderInfoNoTemp=" + orderInfoNoTemp);
		System.out.println("groupInfoNoTemp=" + groupInfoNoTemp);
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		int groupInfoNo=-1;
		if (groupInfoNoTemp != null && groupInfoNoTemp.length() != 0) {
			try {
				groupInfoNo = Integer.parseInt(groupInfoNoTemp);
			} catch (NumberFormatException e) {
				System.out.println("groupInfoNoTemp轉型失敗");
			}
			
		}
		int orderInfoNo = -1;
		if (orderInfoNoTemp != null && orderInfoNoTemp.length() != 0) {
			try {
				orderInfoNo = Integer.parseInt(orderInfoNoTemp);
			} catch (NumberFormatException e) {
				System.out.println("orderInfoNoTemp轉型失敗");		
			}	
		}
		if ("reject".equals(orderInfoStatus)) {
			orderInfoService.updateOrderInfoStatus(1002, orderInfoNo);
			out.write("success");

		} else if ("accept".equals(orderInfoStatus)) {
			orderInfoService.updateOrderInfoStatus(1003, orderInfoNo);
			CreateGroupInfoBean selectGroupInfoByGroupInfoNo = groupInfoService.selectGroupInfoByGroupInfoNo(groupInfoNo);
			session.setAttribute("selectProductQt", selectGroupInfoByGroupInfoNo.getGroupInfoTotalProductQt());
			Gson gson = new Gson();
			String json = gson.toJson(selectGroupInfoByGroupInfoNo);
			System.out.println(json);
			out.write(json);

		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
