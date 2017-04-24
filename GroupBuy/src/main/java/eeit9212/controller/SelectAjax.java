package eeit9212.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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
import eeit9212.model.GroupInfoService;
import eeit9212.model.OrderInfoBean;
import eeit9212.model.OrderInfoService;
import login.model.MemberBean;

@WebServlet("/eeit9212/grouprecord/selectajax")
public class SelectAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private GroupInfoService groupInfoService;
	private OrderInfoService orderInfoService;
	@Override
	public void init() throws ServletException {
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		groupInfoService = (GroupInfoService) context.getBean("groupInfoService");
		orderInfoService = (OrderInfoService) context.getBean("orderInfoService");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		MemberBean memberBean = (MemberBean) session.getAttribute("loginToken");
		Integer memberNo = memberBean.getMemberNo();
		String groupInfoNoTemp = request.getParameter("groupInfoNo");
		String orderInfoNoTemp = request.getParameter("orderInfoNo");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		System.out.println("groupInfoNoTemp=" + groupInfoNoTemp);
		System.out.println("orderInfoNoTemp=" + orderInfoNoTemp);
		int groupInfoNo = -1;
		if (groupInfoNoTemp != null && groupInfoNoTemp.length() != 0) {
			try {
				groupInfoNo = Integer.parseInt(groupInfoNoTemp);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		int orderInfoNo = -1;
		if (orderInfoNoTemp != null && orderInfoNoTemp.length() != 0) {
			try {
				orderInfoNo = Integer.parseInt(orderInfoNoTemp);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		if (groupInfoNo != -1) {
			AttendGroupInfoBean selectMyAttendedByGroupInfoNo = groupInfoService.selectMyAttendedByGroupInfoNo(memberNo,
					groupInfoNo);
			selectMyAttendedByGroupInfoNo.setFormatDeadLine();
			selectMyAttendedByGroupInfoNo.setFormatStartDate();
			selectMyAttendedByGroupInfoNo.setFormatGrouperCredit();
			Gson gson = new Gson();
			String json = gson.toJson(selectMyAttendedByGroupInfoNo);
			System.out.println(json);
			out.write(json);
			return;
		}
		if (orderInfoNo != -1) {
			OrderInfoBean selectMyOrderInfoByNo=orderInfoService.selectMyOrderInfoByNo(orderInfoNo);
			selectMyOrderInfoByNo.setFormatPayTime();
			Gson gson = new Gson();
			String json = gson.toJson(selectMyOrderInfoByNo);
			System.out.println(json);
			out.write(json);
			return;
		}

		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
