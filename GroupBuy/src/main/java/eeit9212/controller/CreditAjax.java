package eeit9212.controller;

import java.io.IOException;

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

@WebServlet("/eeit9212/grouprecord/creditajax")
public class CreditAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CreditAttendanceService creditAttendanceService;
	private OrderInfoService orderInfoService;
	private GroupInfoService groupInfoService;
	
	@Override
	public void init() throws ServletException {
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		creditAttendanceService=(CreditAttendanceService)context.getBean("creditAttendanceService");
		orderInfoService=(OrderInfoService)context.getBean("orderInfoService");
		groupInfoService=(GroupInfoService)context.getBean("groupInfoService");
	
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 
		HttpSession session = request.getSession();
		int orderInfoNo = (int) session.getAttribute("orderInfoNo");
		MemberBean memberBean = (MemberBean) session.getAttribute("loginToken");
		Integer memberNo=memberBean.getMemberNo();
		String scoreTemp = request.getParameter("score");
		String groupInfoNoTemp=request.getParameter("groupInfoNo");
		String groupInfoMemberNoTemp=request.getParameter("groupInfoMemberNo");
		System.out.println("groupInfoMemberNoTemp=" + groupInfoMemberNoTemp);
		System.out.println("scoreTemp=" + scoreTemp);
		int score = -1;
		if (scoreTemp != null && scoreTemp.length() != 0) {
			try {
				score = Integer.parseInt(scoreTemp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		int groupInfoMemberNo=-1;
		if(groupInfoMemberNoTemp!=null&&groupInfoMemberNoTemp.length()!=0){
			try {
				groupInfoMemberNo = Integer.parseInt(groupInfoMemberNoTemp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		int groupInfoNo=-1;
		if(groupInfoNoTemp!=null&&groupInfoNoTemp.length()!=0){
			try {
				groupInfoNo = Integer.parseInt(groupInfoNoTemp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(creditAttendanceService.updateGrouperCredit(groupInfoMemberNo, score)){
			System.out.println("更新主糾評分groupInfoMemberNo="+groupInfoMemberNo);
			orderInfoService.updateOrderInfoStatus(1202, orderInfoNo);
			System.out.println("更新訂單狀態orderInfoNo="+orderInfoNo);
			creditAttendanceService.updateGroupAttendance(memberNo, 1);
			System.out.println("更新出席率memberNo="+memberNo);
//			GroupInfoBean selectMyAttendedByGroupInfoNo = groupInfoService.selectMyAttendedByGroupInfoNo(memberNo,
//					groupInfoNo);		
//			session.setAttribute("selectMyAttendedByGroupInfoNo", selectMyAttendedByGroupInfoNo);
			return;
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
