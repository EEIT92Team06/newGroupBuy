package searchgroup.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import searchgroup.model.GroupMsgReplyBean;
import searchgroup.model.GroupMsgService;

@WebServlet("/searchgroup/GroupMsgReplyServlet.controller")
public class GroupMsgReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GroupMsgService groupMsgService;
	@Override
	public void init() throws ServletException {
		ServletContext application = this.getServletContext();
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(application);
		groupMsgService = (GroupMsgService)context.getBean("groupMsgService");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(345);
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/plain; charset=UTF-8");
		PrintWriter out = response.getWriter();
		//接收資料
		//轉換資料
		//封裝成bean物件
		//依據結果顯示view
		String msgNo = request.getParameter("msgNo");
		String memberNo = request.getParameter("memberNo");
		String replyMsg = request.getParameter("replyMsg");
		
		System.out.println("msgNo : " + msgNo);
		System.out.println("memberNo : " + memberNo);
		System.out.println("replyMsg : " + replyMsg);
		int IntmsgNo = Integer.parseInt(msgNo);
		int IntmemberNo = Integer.parseInt(memberNo);
		
		GroupMsgReplyBean groupMsgReplyBean= new GroupMsgReplyBean(IntmemberNo, IntmsgNo, replyMsg);
		groupMsgService.insertreplyMsg(groupMsgReplyBean);
        out.println(replyMsg);
        out.flush();
		
	}

}
