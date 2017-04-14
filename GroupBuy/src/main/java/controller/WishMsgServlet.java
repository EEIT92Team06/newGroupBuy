package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import login.model.MemberBean;
import wish.model.WishInterestService;
import wish.model.WishMsgBean;
import wish.model.WishMsgService;
import wish.model.WishPoolBean;

@WebServlet("/wish/wishMsg.controller")
public class WishMsgServlet extends HttpServlet {
	Integer memberNo;
	private WishMsgService wishMsgService;
    private WishInterestService wishInterestService;
	@Override
	public void init() throws ServletException {
		ServletContext application = this.getServletContext();
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(application);
		wishMsgService = (WishMsgService)context.getBean("wishMsgService");
		wishInterestService = (WishInterestService)context.getBean("wishInterestService");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Map<String, String> errorMsg = new HashMap<String, String>();
		request.setAttribute("errorMsg", errorMsg);
		// 接收資料
		HttpSession session = request.getSession();
		MemberBean member = (MemberBean) session.getAttribute("loginToken");

		memberNo = member.getMemberNo();
		String wishNo = request.getParameter("wishNo");
		String msg = request.getParameter("content");
		String send = request.getParameter("send");
        System.out.println(memberNo);
		// 驗證資料
		if ("確認送出".equals(send)) {
			if (msg == null || msg.trim().length()==0) {
				errorMsg.put("message", "請輸入留言內容!");
				
			}
		}
		// 轉換資料
		int wishNumber = 0;
		if (wishNo != null && wishNo.trim().length() != 0) {
			try {
				wishNumber = Integer.parseInt(wishNo);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		
		if(errorMsg!=null && !errorMsg.isEmpty()){
			request.getRequestDispatcher("/wish/wishdetail.jsp").forward(request, response);
			return;
		}
		
		// 呼叫model, 根據Model執行結果呼叫View
		WishMsgBean bean = new WishMsgBean();
		WishPoolBean wishPoolBean = new WishPoolBean();
		wishPoolBean.setWishNo(wishNumber);
		bean.setMemberNo(memberNo);
		bean.setWishPoolBean(wishPoolBean);
        bean.setWishMsgContent(msg);
        
        if("確認送出".equals(send)){
        	Boolean msgInsert = wishMsgService.insert(bean);
        	//取得所有留言
        	List<WishMsgBean> msgDetail = wishMsgService.getWishMsg(wishNumber);
    		session.setAttribute("msgDetail", msgDetail);
        	//透過wishNumber取得該許願的讚數
    		int count = wishInterestService.likeCount(wishNumber);
    		if (count == 0) {
    			session.setAttribute("like", "成為第一個喜歡此商品的人!");
    		}else{
    			session.setAttribute("like", count+"個人對此商品有興趣!");
    		}
    		//判斷是否按過讚
    		boolean result = wishInterestService.likeOrNot(wishNumber, memberNo);
    		if(result==true){
    			session.setAttribute("likeOrNot", "收回讚");
    		}else{
    			session.setAttribute("likeOrNot", "有興趣");
    		}

        	if(msgInsert==true){
        		response.sendRedirect(request.getContextPath()+"/wish/wishdetail.jsp");
        		return;
        	}else{
        		errorMsg.put("message", "留言失敗!");
        		request.getRequestDispatcher("/wish/wishdetail.jsp").forward(request, response);
        		return;
        	}
        }
        
	}

}
