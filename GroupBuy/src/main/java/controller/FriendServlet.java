package controller;

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

import login.model.MemberBean;
import model.FriendBean;
import model.FriendService;

/**
 * Servlet implementation class FriendServlet
 */
@WebServlet("/friend/friend.controller")
public class FriendServlet extends HttpServlet {
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
		String x = request.getParameter("x");
		HttpSession session = request.getSession();

		List<FriendBean> list=null;
		
		String relationBtn=request.getParameter("RelationBtn");
		String friendNo1=request.getParameter("friendNo");
		String memberFriendNo1=request.getParameter("memberFriendNo");
		String searchTxt=request.getParameter("searchTxt");
		String searchMark=request.getParameter("SearchMark");
		Integer friendNo=null;
		if(friendNo1!=null&&friendNo1.length()!=0){
			try {
				friendNo=Integer.parseInt(friendNo1);
			} catch (NumberFormatException e) {
				System.out.println("friendNo Error="+friendNo);
				e.printStackTrace();
			}
		}
		
		Integer memberFriendNo=null;
		if(memberFriendNo1!=null&&memberFriendNo1.length()!=0){
			try {
				memberFriendNo=Integer.parseInt(memberFriendNo1);
			} catch (NumberFormatException e) {
				System.out.println("memberFriendNo Error="+memberFriendNo);
				e.printStackTrace();
			}
		}
		
		int loginMemberNo = (Integer)((MemberBean)session.getAttribute("loginToken")).getMemberNo(); // 拿session內memberNo
		//好友列表
		if ("friend".equals(x)) {
			list = friendService.selectRelationList(loginMemberNo, 2101);
			request.setAttribute("friendlist", 1);
		}
		//邀請列表
		if ("requested".equals(x)) {
			list = friendService.selectRequestedList(loginMemberNo, 2103);
			request.setAttribute("requested", 1);
		}
		//被邀請列表
		if("requesting".equals(x)){
			list = friendService.selectRelationList(loginMemberNo, 2103);
			request.setAttribute("requesting", 1);
		}
		//黑名單
		if("blockade".equals(x)){
			list= friendService.selectRelationList(loginMemberNo, 2102);
			request.setAttribute("blockade", 1);
		}
		//搜尋會員
		if("Search".equals(relationBtn)){
			list=friendService.selectMember(loginMemberNo, searchTxt);
			System.out.println("search="+searchTxt);
			request.setAttribute("search", 1);
			session.setAttribute("searchMark", searchTxt);
			System.out.println("");
		}
		
		//邀請成為朋友
		if("Request".equals(relationBtn)){
			friendService.requestFriend(loginMemberNo, memberFriendNo);
			list=friendService.selectMember(loginMemberNo, searchTxt);
		}
		//刪除
		if("Delete".equals(relationBtn)){
			friendService.deleteFriend(friendNo, memberFriendNo, loginMemberNo);
			list = friendService.selectRelationList(loginMemberNo, 2101);
			request.setAttribute("friendlist", 1);
		}
		if("Block".equals(relationBtn)){
			friendService.blockFriend(friendNo, memberFriendNo, loginMemberNo);
			list = friendService.selectRelationList(loginMemberNo, 2101);
			request.setAttribute("friendlist", 1);
		}
		if("CancelRequest".equals(relationBtn)){
			friendService.deleteRelationFromList(friendNo);
			list = friendService.selectRelationList(loginMemberNo, 2103);
			request.setAttribute("requesting", 1);
		}
		if("BeFriend".equals(relationBtn)){
			friendService.acceptRequested(friendNo, loginMemberNo, memberFriendNo);
			list = friendService.selectRequestedList(loginMemberNo, 2103);
			request.setAttribute("requested", 1);
		}
		//拒絕好友邀請
		if("Refuse".equals(relationBtn)){
			friendService.deleteRelationFromList(friendNo);
			list = friendService.selectRequestedList(loginMemberNo, 2103);
			request.setAttribute("requested", 1);
		}
		if("UnBlock".equals(relationBtn)){
			friendService.deleteRelationFromList(friendNo);
			list= friendService.selectRelationList(loginMemberNo, 2102);
			request.setAttribute("blockade", 1);
		}
		
		if (list != null) {
			if(searchMark!=null){
				list=friendService.selectMember(loginMemberNo, searchMark);
			
				request.removeAttribute("friendlist");
				request.removeAttribute("requesting");
				request.removeAttribute("requested");
				request.removeAttribute("blockade");
				request.setAttribute("search", 1);
			}
			request.setAttribute("relationList", list);
			RequestDispatcher rd = request.getRequestDispatcher("friendlist.jsp");
			rd.forward(request, response);
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
