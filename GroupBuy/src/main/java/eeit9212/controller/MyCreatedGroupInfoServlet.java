package eeit9212.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
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

import eeit9212.model.CreateGroupInfoBean;
import eeit9212.model.CreditAttendanceService;
import eeit9212.model.GroupInfoDetailsBean;
import eeit9212.model.GroupInfoService;
import eeit9212.model.OrderInfoBean;
import eeit9212.model.OrderInfoDetailsBean;
import eeit9212.model.OrderInfoService;
import login.model.MemberBean;

@WebServlet("/eeit9212/grouprecord/mycreatedgroupinfo.controller")
public class MyCreatedGroupInfoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private GroupInfoService groupInfoService;
	private OrderInfoService orderInfoService;
	private CreditAttendanceService creditAttendanceService;

	@Override
	public void init() throws ServletException {
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		groupInfoService = (GroupInfoService) context.getBean("groupInfoService");
		orderInfoService = (OrderInfoService) context.getBean("orderInfoService");
		creditAttendanceService = (CreditAttendanceService) context.getBean("creditAttendanceService");

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		MemberBean memberBean = (MemberBean) session.getAttribute("loginToken");
		Integer memberNo=memberBean.getMemberNo();
		System.out.println("memberBean="+memberNo);
		String contextPath = request.getContextPath();
		String groupInfoNoTemp = request.getParameter("groupInfoNo");
		String locationFrom = request.getParameter("locationFrom");
		System.out.println("memberNo="+memberNo);
		System.out.println("groupInfoNoTemp="+groupInfoNoTemp);
		System.out.println("locationFrom="+locationFrom);
		
		CreateGroupInfoBean selectGroupInfoByGroupInfoNo=null;
		
		int groupInfoNo = -1;
		// 判斷是否從mycreatedgroup.jsp呼叫的，我有帶參數groupInfoNo。
		if (groupInfoNoTemp != null && groupInfoNoTemp.length() != 0) {
			System.out.println("從mycreatedgroup.jsp收到請求");
			try {
				groupInfoNo = Integer.parseInt(groupInfoNoTemp);			
				
					
				
			
				if("noExtension".equals(locationFrom)){
					groupInfoService.updateGroupStatus(groupInfoNo, 4);
					orderInfoService.updateOrderInfoStatusByOrderStatusNo(groupInfoNo, 1005, 1104);
					orderInfoService.updateOrderInfoStatusByOrderStatusNo(groupInfoNo, 1005, 1003);
					orderInfoService.updateOrderInfoStatusByOrderStatusNo(groupInfoNo, 1005, 1001);
				
				}
		
				if("groupEnd".equals(locationFrom)){
					groupInfoService.updateGroupStatus(groupInfoNo, 11);
				}
				
				selectGroupInfoByGroupInfoNo = groupInfoService.selectGroupInfoByGroupInfoNo(groupInfoNo);
				request.setAttribute("selectGroupInfoByGroupInfoNo", selectGroupInfoByGroupInfoNo);
				System.out.println("團狀態="+selectGroupInfoByGroupInfoNo.getGroupStatusNo());
				List<GroupInfoDetailsBean> selectGroupInfoDetail = groupInfoService.selectGroupInfoDetail(groupInfoNo);
				request.setAttribute("selectGroupInfoDetail", selectGroupInfoDetail);
				List<OrderInfoBean> selectMyGroupOrderInfo = orderInfoService.selectMyGroupOrderInfo(groupInfoNo);
				request.setAttribute("selectMyGroupOrderInfo", selectMyGroupOrderInfo);
				// 把所有訂單明細存到一個List裡面
				List<OrderInfoDetailsBean> selectOneOrderInfoDetails = new ArrayList<OrderInfoDetailsBean>();
				for (OrderInfoBean bean1 : selectMyGroupOrderInfo) {
					for (GroupInfoDetailsBean bean : selectGroupInfoDetail) {
						OrderInfoDetailsBean select = orderInfoService.selectOneOrderInfoDetails(bean1.getOrderInfoNo(),
								bean.getGroupInfoDetailsNo());
						selectOneOrderInfoDetails.add(select);
					}
				}
				
				
				request.setAttribute("selectOneOrderInfoDetails", selectOneOrderInfoDetails);
				//為了讓賣家能按F5看到新的訂單，所以也使用forward，按F5重新檢查有沒有新的訂單。
				RequestDispatcher rd = request.getRequestDispatcher("/eeit9212/grouprecord/mycreatedgroupinfo.jsp");
				rd.forward(request, response);			

				return;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("從test.jsp收到請求");
		List<CreateGroupInfoBean> selectMyCreatedGroupInfo = groupInfoService.selectMyCreatedGroupInfo(memberNo);
		request.setAttribute("selectMyCreatedGroupInfo", selectMyCreatedGroupInfo);
		System.out.println("selectMyCreatedGroupInfo="+selectMyCreatedGroupInfo);
		//為了要讓使用者按F5可以改變團狀態所以改用forward，存到session的改存request。
		RequestDispatcher rd = request.getRequestDispatcher("/eeit9212/grouprecord/mycreatedgroup.jsp");
		rd.forward(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
