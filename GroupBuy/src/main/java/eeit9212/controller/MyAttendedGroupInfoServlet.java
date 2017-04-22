package eeit9212.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import eeit9212.model.AttendGroupInfoBean;
import eeit9212.model.GroupInfoPicBean;
import eeit9212.model.GroupInfoService;
import eeit9212.model.OrderInfoBean;
import eeit9212.model.OrderInfoDetailsBean;
import eeit9212.model.OrderInfoService;
import login.model.MemberBean;

@WebServlet("/eeit9212/grouprecord/myattendedgroupinfo.controller")
public class MyAttendedGroupInfoServlet extends HttpServlet {

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
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		
		MemberBean memberBean = (MemberBean) session.getAttribute("loginToken");
		Integer memberNo=memberBean.getMemberNo();
		String contextPath = request.getContextPath();
		String groupInfoNoTemp = request.getParameter("groupInfoNo");
		String orderInfoNoTemp = request.getParameter("orderInfoNo");
		
		String locationFrom = request.getParameter("locationFrom");
		System.out.println("locationFrom="+locationFrom);
		System.out.println("groupInfoNoTemp="+groupInfoNoTemp);
		System.out.println("orderInfoNoTemp="+orderInfoNoTemp);
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

		
		// 判斷是否從myattendedgroup.jsp呼叫的，我有帶參數orderInfoNo。
		if (orderInfoNo != -1) {
			System.out.println("從myattendedgroup.jsp收到請求");
			session.setAttribute("orderInfoNo", orderInfoNo);

			if("timeout".equals(locationFrom)){
				groupInfoService.updateGroupStatus(groupInfoNo, 2);
			}	
			if("againTimeout".equals(locationFrom)){
				groupInfoService.updateGroupStatus(groupInfoNo, 6);
			}	
			
		
						
			AttendGroupInfoBean selectMyAttendedByGroupInfoNo = groupInfoService.selectMyAttendedByGroupInfoNo(memberNo,
					groupInfoNo);		
			request.setAttribute("selectMyAttendedByGroupInfoNo", selectMyAttendedByGroupInfoNo);
			OrderInfoBean selectMyOrderInfoByNo=orderInfoService.selectMyOrderInfoByNo(orderInfoNo);
			request.setAttribute("selectMyOrderInfoByNo", selectMyOrderInfoByNo);
			List<OrderInfoDetailsBean> selectOrderInfoDetails = orderInfoService.selectOrderInfoDetails(orderInfoNo);
			System.out.println(selectOrderInfoDetails);
			request.setAttribute("selectOrderInfoDetails", selectOrderInfoDetails);
			List<GroupInfoPicBean> selectGroupInfoPic = groupInfoService.selectGroupInfoPic(groupInfoNo);
			request.setAttribute("selectGroupInfoPic", selectGroupInfoPic);
			double selectTotalPrice = orderInfoService.selectTotalPrice(orderInfoNo);
			request.setAttribute("selectTotalPrice", selectTotalPrice);
			RequestDispatcher rd=request.getRequestDispatcher("/eeit9212/grouprecord/myattendedgroupinfo.jsp");
			rd.forward(request, response);
//			response.sendRedirect(contextPath+"/eeit9212/grouprecord/myattendedgroupinfo.jsp");
			return;
		}
		System.out.println("從test.jsp收到請求");
		//暫時不用判斷哪些團流團。
//		if("checkNoExtension".equals(locationFrom)){
//			orderInfoService.updateOrderInfoStatus(1005, orderInfoNo);
//	
//		}	
		List<AttendGroupInfoBean> selectMyAttendedGroupInfo = groupInfoService.selectMyAttendedGroupInfo(memberNo);
		request.setAttribute("selectMyAttendedGroupInfo", selectMyAttendedGroupInfo);
		RequestDispatcher rd=request.getRequestDispatcher("/eeit9212/grouprecord/myattendedgroup.jsp");
		rd.forward(request, response);	
//		response.sendRedirect(contextPath+"/eeit9212/grouprecord/myattendedgroup.jsp");
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
