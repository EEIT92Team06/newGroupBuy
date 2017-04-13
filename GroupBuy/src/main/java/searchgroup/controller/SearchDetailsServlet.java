package searchgroup.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import searchgroup.model.GroupMsgService;
import login.model.MemberBean;
import searchgroup.model.SearchDetailsService;

/**
 * Servlet implementation class SearchDetailsServlet
 */
@WebServlet("/searchgroup/searchDetailsServlet.controller")
public class SearchDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SearchDetailsService searchDetailsService;
	private GroupMsgService groupMsgService;
	@Override
	public void init() throws ServletException {
		ServletContext application = this.getServletContext();
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(application);
		searchDetailsService = (SearchDetailsService)context.getBean("searchDetailsService");
		groupMsgService = (GroupMsgService)context.getBean("groupMsgService");
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("UTF-8");
		//從session中取的現在用戶是誰，要將member_No丟出去
		HttpSession session = request.getSession();		

		//接受來自searchResult.jsp的參數 groupInfoNo
		System.out.println("this is SearchDetailsServlet & SearchGroupMsg!");
		String groupInfoNo = request.getParameter("groupInfoNo");
		//轉換資料
		int no = Integer.parseInt(groupInfoNo);
		Map<String, String> result = searchDetailsService.selectDetails(no);
		List<Map<String, String>> resultMulti = searchDetailsService.selectGroupProdsDetails(no);
		List<Map<String, String>> resultMulti2 = searchDetailsService.selectDetailsPicNo(no);
		String memberNo = result.get("memberNo");
		int iMemberNo = Integer.parseInt(memberNo);
		System.out.println("iMemberNo : " + iMemberNo);
		System.out.println("resulthere : " + result);
		//insert clickTime進去table(等於該團點擊率+1)
		
		int insertResult = searchDetailsService.insertClickTimes(no);
		System.out.println("insertResult : " + insertResult +"筆資料新增");
		
		//取得團主圖片
		String memberPic = searchDetailsService.selectmemberPic(iMemberNo);
		System.out.println("memberPic : " + memberPic);
		//丟出去groupInfoNo , 團資料，(多方)團明細產品資料  , (多方)團照片pk 
		session.setAttribute("groupInfoNo", no);
		MemberBean memberBean = (MemberBean)session.getAttribute("loginToken");
		session.setAttribute("memberBean", memberBean);
		session.setAttribute("result", result);
		session.setAttribute("resultMulti",resultMulti);
		session.setAttribute("resultMulti2",resultMulti2);
		session.setAttribute("memberPic", memberPic);
		
		
		List<Map<String, Object>> selectMsg = groupMsgService.selectMSG(no);
		
		List<Integer> MsgNoList = new ArrayList<Integer>();
		for(int i =0 ; i<selectMsg.size(); i++){
//			int msgNo = selectMsg.get(i).getGroupMsgNo();
			String groupMsgNo = (String)selectMsg.get(i).get("groupMsgNo");
			int msgNo = Integer.parseInt(groupMsgNo);
			MsgNoList.add(msgNo);
		}
//		
		session.setAttribute("msgNoList", MsgNoList);
		session.setAttribute("selectMsg", selectMsg);
		String contextPath = getServletContext().getContextPath();
		response.sendRedirect(response
				.encodeRedirectURL(contextPath+"/searchgroup/searchDetails.jsp"));
		return;
		
	}

}
