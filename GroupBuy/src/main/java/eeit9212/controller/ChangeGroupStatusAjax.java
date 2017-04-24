package eeit9212.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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

import eeit9212.model.CreateGroupInfoBean;
import eeit9212.model.CreditAttendanceService;
import eeit9212.model.GroupInfoService;
import eeit9212.model.OrderInfoBean;
import eeit9212.model.OrderInfoService;
import login.model.MemberBean;

@WebServlet("/eeit9212/grouprecord/changegroupstatus")
public class ChangeGroupStatusAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GroupInfoService groupInfoService;
	private OrderInfoService orderInfoService;
	private CreditAttendanceService creditAttendanceService;

	@Override
	public void init() throws ServletException {
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		groupInfoService = (GroupInfoService) context.getBean("groupInfoService");
		orderInfoService=(OrderInfoService)context.getBean("orderInfoService");
		creditAttendanceService = (CreditAttendanceService) context.getBean("creditAttendanceService");

	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 
		HttpSession session = request.getSession();
		MemberBean memberBean = (MemberBean) session.getAttribute("loginToken");
		Integer memberNo=memberBean.getMemberNo();
		String groupInfoNoTemp = request.getParameter("groupInfoNo");
		String locationFrom = request.getParameter("locationFrom");
		System.out.println("locationFrom=" + locationFrom);
		System.out.println("groupInfoNoTemp=" + groupInfoNoTemp);
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		int groupInfoNo=-1;
		if(groupInfoNoTemp!=null&&groupInfoNoTemp.length()!=0){
			try {
				groupInfoNo = Integer.parseInt(groupInfoNoTemp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if("timeout".equals(locationFrom)){
			groupInfoService.updateGroupStatus(groupInfoNo, 2);
		}
		if("payTimeout".equals(locationFrom)){
			System.out.println("payTimeout GroupNo="+groupInfoNo);
			groupInfoService.updateGroupStatus(groupInfoNo, 8);
			orderInfoService.updateOrderInfoStatusByOrderStatusNo(groupInfoNo, 1004, 1104);
			
		
		}	
		
		if("checkOrder".equals(locationFrom)){
			for(OrderInfoBean orderBean:orderInfoService.selectMyGroupOrderInfo(groupInfoNo)){
				System.out.println("OrderInfoStatusNo()="+orderBean.getOrderInfoStatusNo());
				if(orderBean.getOrderInfoStatusNo()==1004){
					System.out.println("OrderInfoMemberNo()="+orderBean.getMemberNo());
					creditAttendanceService.updateGroupAttendance(orderBean.getMemberNo(), 0);
				}
			}
		}
		
		if("selectOrderInfo".equals(locationFrom)){
			List<OrderInfoBean> selectMyGroupOrderInfo = orderInfoService.selectMyGroupOrderInfo(groupInfoNo);
			Gson gson = new Gson();
			String json = gson.toJson(selectMyGroupOrderInfo);
			System.out.println(json);
			out.write(json);
		}
		if("selectGroupInfo".equals(locationFrom)){
			CreateGroupInfoBean selectGroupInfoByGroupInfoNo = groupInfoService.selectGroupInfoByGroupInfoNo(groupInfoNo);
			selectGroupInfoByGroupInfoNo.setFormatDeadLine();
			selectGroupInfoByGroupInfoNo.setFormatStartDate();
			Gson gson = new Gson();
			String json = gson.toJson(selectGroupInfoByGroupInfoNo);
			System.out.println(json);
			out.write(json);
		}
		if("extension".equals(locationFrom)){
			String deadLineTemp=request.getParameter("deadLine");
			Timestamp deadLine=null;
			if(deadLineTemp!=null&&deadLineTemp.length()!=0){
				try{
				deadLine=Timestamp.valueOf(deadLineTemp);
				groupInfoService.updateGroupStatus(groupInfoNo, 3);
				groupInfoService.updateGroupInfoDeadLine(groupInfoNo, deadLine);
				CreateGroupInfoBean selectGroupInfoByGroupInfoNo = groupInfoService.selectGroupInfoByGroupInfoNo(groupInfoNo);
				selectGroupInfoByGroupInfoNo.setFormatDeadLine();
				selectGroupInfoByGroupInfoNo.setFormatStartDate();
				Gson gson = new Gson();
				String json = gson.toJson(selectGroupInfoByGroupInfoNo);
				System.out.println(json);
				out.write(json);
				}catch(Exception e){
					System.out.println("deadLineTemp格式錯誤="+deadLineTemp);
				}
			}				
		}
		if("againTimeout".equals(locationFrom)){
			groupInfoService.updateGroupStatus(groupInfoNo, 6);
		}	
		if("groupStart".equals(locationFrom)){	
			CreateGroupInfoBean selectGroupInfoByGroupInfoNo = groupInfoService.selectGroupInfoByGroupInfoNo(groupInfoNo);
			if(selectGroupInfoByGroupInfoNo.getGroupStatusNo()==2||selectGroupInfoByGroupInfoNo.getGroupStatusNo()==6){
			groupInfoService.updateGroupStatus(groupInfoNo, 7);
			groupInfoService.updateGroupInfoDeadLine(groupInfoNo,new Timestamp( new java.util.Date().getTime()));
			orderInfoService.updateOrderInfoStatusByOrderStatusNo(groupInfoNo,1101,1003);
			orderInfoService.updateOrderInfoStatusByOrderStatusNo(groupInfoNo,1002,1001);
			
			System.out.println("團開始了");
			}
		}	
		if("startSend".equals(locationFrom)){
			groupInfoService.updateGroupStatus(groupInfoNo, 9);
			orderInfoService.updateOrderInfoStatusByOrderStatusNo(groupInfoNo,1004,1104);
			orderInfoService.updateOrderInfoStatusByOrderStatusNo(groupInfoNo,1201,1102);
			orderInfoService.updateOrderInfoStatusByOrderStatusNo(groupInfoNo,1201,1105);
		}	
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
