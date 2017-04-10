package searchgroup.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gson.Gson;

import searchgroup.model.GroupMsgBean;
import searchgroup.model.GroupMsgService;

@WebServlet("/searchgroup/GroupMsgServlet.controller")
public class GroupMsgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GroupMsgService groupMsgService;
	@Override
	public void init() throws ServletException {
		ServletContext application = this.getServletContext();
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(application);
		groupMsgService = (GroupMsgService)context.getBean("groupMsgService");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("this is doGet");
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		System.out.println("this is doPost");
		PrintWriter out = response.getWriter();
		//接收資料
		//轉換資料
		//封裝成bean物件
		//依據結果顯示view
		String groupMsg = request.getParameter("groupMsg");
		String memberNo = request.getParameter("memberNo");
		System.out.println("memberNo : " + memberNo);
		System.out.println("groupMsg : " + groupMsg);
		int IntmemberNo = Integer.parseInt(memberNo);
		String groupInfoNo = request.getParameter("groupInfoNo");
		int IntgroupInfoNo = Integer.parseInt(groupInfoNo);
		
		GroupMsgBean groupMsgBean = new GroupMsgBean(IntmemberNo, IntgroupInfoNo, groupMsg);
		groupMsgService.insertMsg(groupMsgBean);
		
		//新增取新的msgNoList
		List<Map<String, Object>> selectMsg = groupMsgService.selectMSG(IntgroupInfoNo);
		List<Integer> MsgNoList = new ArrayList<Integer>();
		for(int i =0 ; i<selectMsg.size(); i++){
			String groupMsgNo = (String)selectMsg.get(i).get("groupMsgNo");
			int msgNo = Integer.parseInt(groupMsgNo);
			MsgNoList.add(msgNo);
		}
		Map all = new HashMap();
		all.put("msgNoList" , MsgNoList);
		all.put("groupMsg" , groupMsg);
		
		System.out.println("all :" + all);
		
		String resultall = new Gson().toJson(all);
		System.out.println("resultall " + resultall);
        out.println(resultall);
        out.flush();
		
	}

}
