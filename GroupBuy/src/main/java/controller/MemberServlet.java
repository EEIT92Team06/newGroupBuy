package controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import model.FriendBean;
import model.FriendService;
import model.MemberBean;
import model.MemberService;

/**
 * Servlet implementation class MemberServlet
 */
@WebServlet("/member/member.controller")
@MultipartConfig
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MemberService memberService;
	private FriendService friendService;

	@Override
	public void init() throws ServletException {
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		memberService = (MemberService) context.getBean("memberService");
		friendService = (FriendService) context.getBean("friendService");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		
	
		
		
		
		Map<String, String> status = new HashMap<String, String>();
		request.setAttribute("statusKey", status);

		MemberBean memberBean = new MemberBean();

		// test.jsp(login後的首頁)
		String memberNo = request.getParameter("memberNo"); // url memberNo=?
		
		String x = request.getParameter("x");
		String updateInfo = request.getParameter("updateInfo");
		String updatePassword = request.getParameter("updatePassword");
		String relationBtn = request.getParameter("RelationBtn");
		String memberFriendNo1 = request.getParameter("FriendInfomemberFriendNo");
		String friendNo1 = request.getParameter("friendInfofriendNo");
		String otherMemberNo1 = request.getParameter("memberInfomemberNo");

		int loginMemberNo = -1;

		try {
			loginMemberNo = (Integer)((MemberBean)session.getAttribute("loginToken")).getMemberNo(); // 拿session內memberNo
		} catch (Exception e) {
			System.out.println("loginMemberNo Error=" + loginMemberNo);
			e.printStackTrace();
		}

		Integer urlMemberNo = null; // url的memberNo=session內的memberNo
		if (memberNo != null && memberNo.length() != 0) {
			try {
				urlMemberNo = Integer.parseInt(memberNo);
			} catch (NumberFormatException e) {
				System.out.println("urlMemberNo Error=" + urlMemberNo);
				e.printStackTrace();
			}
		}

		Integer memberFriendNo = null;
		if (memberFriendNo1 != null && memberFriendNo1.length() != 0) {
			try {
				memberFriendNo = Integer.parseInt(memberFriendNo1);
			} catch (NumberFormatException e) {
				System.out.println("urlMemberNo Error=" + memberFriendNo1);
				e.printStackTrace();
			}
		}

		Integer friendNo = null;
		if (friendNo1 != null && friendNo1.length() != 0) {
			try {
				friendNo = Integer.parseInt(friendNo1);
			} catch (NumberFormatException e) {
				System.out.println("urlMemberNo Error=" + friendNo);
				e.printStackTrace();
			}
		}

		Integer otherMemberNo = null;
		if (otherMemberNo1 != null && otherMemberNo1.length() != 0) {
			try {
				otherMemberNo = Integer.parseInt(otherMemberNo1);
			} catch (NumberFormatException e) {
				System.out.println("urlMemberNo Error=" + otherMemberNo1);
				e.printStackTrace();
			}
		}

		// 如果url的memberNo==login時存在session內的memberNo,就是url指向的memberNo是否等於login的memberNo
		if (urlMemberNo != null && urlMemberNo.equals(loginMemberNo)) { // 導向自己的
			MemberBean info = memberService.selectMemberInfo(loginMemberNo);
			session.setAttribute("MemberInfo", info);
			RequestDispatcher rd = request.getRequestDispatcher("/member/memberinfo.jsp");
			rd.forward(request, response);
			return;
		} else if (urlMemberNo != null && !(urlMemberNo.equals(loginMemberNo))) { // 導向別人的-----------
			MemberBean info = memberService.selectMemberInfo(urlMemberNo);
			FriendBean fdInfo = friendService.selectRelation(loginMemberNo, urlMemberNo);
			request.setAttribute("FriendInfo", fdInfo);
			request.setAttribute("MemberInfo", info);
			session.setAttribute("otherMemberNo", urlMemberNo);
			RequestDispatcher rd = request.getRequestDispatcher("/member/othermemberinfo.jsp");
			rd.forward(request, response);
			return;
		}

		// 指向至MemberUpdate.jsp
		if ("memberUpdate".equals(x)) {
			RequestDispatcher rd = request.getRequestDispatcher("/member/memberupdate.jsp");
			rd.forward(request, response);
			return;
		}
		// update memberInfo:memberNickName + memberAddress + memberPic
		if ("UPDATE".equals(updateInfo)) {
			String memberNickName = request.getParameter("memberNickname");
			String memberAddress = request.getParameter("memberAddress");
			memberBean.setMemberNickName(memberNickName);
			memberBean.setMemberAddress(memberAddress);
//			request.getPart("picUpload");
			Part part=request.getPart("picUpload");
			if(part.getSubmittedFileName()!=""){
				String picture=(String)session.getAttribute("myPic");
				String realPath = this.getServletContext().getRealPath("/pictures");
				part.write(realPath+File.separator+picture);
				memberService.updateMemberPic(loginMemberNo, picture);
			}
			
			memberService.updateMemberInfo(loginMemberNo, memberNickName, memberAddress);// update
			MemberBean sessionInfo1 = memberService.selectMemberInfo(loginMemberNo);// select
																					// memberInfo存sessionInfo1
			session.setAttribute("MemberInfo", sessionInfo1);// sessionInfo1取代MemberInfo原本的sessionInfo

			response.sendRedirect("memberinfo.jsp");
			return;
		}
		if ("UPDATE".equals(updatePassword)) {
			String memberPassword = request.getParameter("memberPassword");
			String newMemberPassword1 = request.getParameter("newMemberPassword1");
			String newMemberPassword2 = request.getParameter("newMemberPassword2");
			memberBean.setMemberPassword(newMemberPassword2);

			// 判斷是否輸入正確
			if (memberPassword == null || memberPassword.length() == 0) {
				status.put("oldNumEmpty", "請輸入舊密碼");
			}
			if (newMemberPassword1 == null || newMemberPassword1.length() == 0) {
				status.put("newNum1Empty", "請輸入新密碼");
			}
			if (!(newMemberPassword2.equals(newMemberPassword1))) {
				status.put("newNum2Different", "密碼與第一次不同");
			}
			if (memberPassword.equals(newMemberPassword1)) {
				status.put("newNumSameAsOldNum", "新密碼不可與舊密碼相同");
			}
			if (!(status.isEmpty())) {
				RequestDispatcher rd = request.getRequestDispatcher("/member/memberpasswordchange.jsp");
				rd.forward(request, response);
				return;
			}

			MemberBean sessionInfo = (MemberBean) session.getAttribute("MemberInfo");
			String originPassword = sessionInfo.getMemberPassword();

			if (originPassword.equals(memberPassword)) { // 如果原密碼==輸入之舊密碼
				memberService.updateMemberPassword(loginMemberNo, newMemberPassword2);

				MemberBean sessionInfo1 = memberService.selectMemberInfo(loginMemberNo);// select
				// memberInfo存sessionInfo1
				session.setAttribute("MemberInfo", sessionInfo1);// sessionInfo1取代MemberInfo原本的sessionInfo
				response.sendRedirect("memberinfo.jsp");
				return;
			} else {
				status.put("oldNumError", "舊密碼輸入錯誤");// 回MemberInfo.jsp前要先顯示"更改成功",其跳出提示還沒研究-------------------------------------
				RequestDispatcher rd = request.getRequestDispatcher("/member/memberpasswordchange.jsp");
				rd.forward(request, response);
				return;
			}

		}
		if (otherMemberNo != null) {
			// 邀請成為朋友
			if ("Request".equals(relationBtn)) {
				friendService.requestFriend(loginMemberNo, otherMemberNo);
			}
			// 刪除
			if ("Delete".equals(relationBtn)) {
				friendService.deleteFriend(friendNo, otherMemberNo, loginMemberNo);
			}
			if ("Block".equals(relationBtn)) {
				friendService.blockFriend(friendNo, otherMemberNo, loginMemberNo);
			}
			if ("CancelRequest".equals(relationBtn)) {
				friendService.deleteRelationFromList(friendNo);
			}
			if ("BeFriend".equals(relationBtn)) {
				friendService.acceptRequested(friendNo, loginMemberNo, otherMemberNo);
			}
			// 拒絕好友邀請
			if ("Refuse".equals(relationBtn)) {
				friendService.deleteRelationFromList(friendNo);
			}
			if ("UnBlock".equals(relationBtn)) {
				friendService.deleteRelationFromList(friendNo);
			}
			RequestDispatcher rd = request
					.getRequestDispatcher("/member/member.controller?memberNo=" + otherMemberNo);
			rd.forward(request, response);
			return;
		}
		
		
		
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

}
