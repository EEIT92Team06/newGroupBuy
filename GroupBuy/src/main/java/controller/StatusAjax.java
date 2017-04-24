package controller;

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

import login.model.MemberBean;
import model.FriendBean;
import model.FriendService;

/**
 * Servlet implementation class FriendServlet
 */
@WebServlet("/statusajax")
public class StatusAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private FriendService friendService;

	@Override
	public void init() throws ServletException {
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		friendService = (FriendService) context.getBean("friendService");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();
		HttpSession session = request.getSession();

		List<FriendBean> list = null;
		String relationBtn = request.getParameter("RelationBtn");
		String friendNo1 = request.getParameter("friendNo");
		String memberFriendNo1 = request.getParameter("memberFriendNo");
		System.out.println("RelationBtn="+relationBtn);
		System.out.println("friendNo1="+friendNo1);
		System.out.println("memberFriendNo1="+memberFriendNo1);
		Integer friendNo = null;
		if (friendNo1 != null && friendNo1.length() != 0) {
			try {
				friendNo = Integer.parseInt(friendNo1);
			} catch (NumberFormatException e) {
				System.out.println("friendNo Error=" + friendNo);
				e.printStackTrace();
			}
		}

		Integer memberFriendNo = null;
		if (memberFriendNo1 != null && memberFriendNo1.length() != 0) {
			try {
				memberFriendNo = Integer.parseInt(memberFriendNo1);
			} catch (NumberFormatException e) {
				System.out.println("memberFriendNo Error=" + memberFriendNo);
				e.printStackTrace();
			}
		}

		int loginMemberNo = (Integer) ((MemberBean) session.getAttribute("loginToken")).getMemberNo(); // 拿session內memberNo
		if ("requested".equals(relationBtn)) {
			list = friendService.selectRequestedList(loginMemberNo, 2103);
			Gson gson=new Gson();
			String listJson=gson.toJson(list);
			out.write(listJson);
		}
		if ("requesting".equals(relationBtn)) {
			list = friendService.selectRelationList(loginMemberNo, 2103);
			Gson gson=new Gson();
			String listJson=gson.toJson(list);
			out.write(listJson);
		}
		// 邀請成為朋友
				if ("Request".equals(relationBtn)) {
					friendService.requestFriend(loginMemberNo, memberFriendNo);
				}
		// 刪除
		if ("Delete".equals(relationBtn)) {
			friendService.deleteFriend(friendNo, memberFriendNo, loginMemberNo);
			request.setAttribute("friendlist", 1);
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
