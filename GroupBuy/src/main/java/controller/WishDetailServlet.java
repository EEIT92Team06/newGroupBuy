package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import member.model.MemberBean;
import wish.model.WishInterestBean;
import wish.model.WishInterestService;
import wish.model.WishMsgBean;
import wish.model.WishMsgService;
import wish.model.WishPictureBean;
import wish.model.WishPictureService;
import wish.model.WishPoolBean;
import wish.model.WishPoolService;

@WebServlet("/wish/wishdetail.controller")
public class WishDetailServlet extends HttpServlet {
	private Integer memberNo;
	private WishPoolService wishPoolService;
	private WishPictureService wishPictureService;
	private WishMsgService wishMsgService;
	private WishInterestService wishInterestService;
	@Override
	public void init() throws ServletException {
		ServletContext application = this.getServletContext();
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(application);
		wishPoolService = (WishPoolService)context.getBean("wishPoolService");
		wishPictureService = (WishPictureService)context.getBean("wishPictureService");
		wishMsgService = (WishMsgService)context.getBean("wishMsgService");
		wishInterestService = (WishInterestService)context.getBean("wishInterestService");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
        //接收資料
		MemberBean memberBean = new MemberBean();
		memberBean.setMemberNo(3);
		HttpSession session = request.getSession();
		session.setAttribute("LoginOK", memberBean);
		MemberBean member = (MemberBean) session.getAttribute("LoginOK");

		memberNo = member.getMemberNo();
		String wishNo = request.getParameter("wishNo");
		int wishNumber = 0;
		try {
			wishNumber = Integer.parseInt(wishNo);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		//透過wishNumber取得單筆許願資訊
		WishPoolBean wishPoolBean = new WishPoolBean();
		wishPoolBean.setWishNo(wishNumber);
		List<WishPoolBean> detail = wishPoolService.select(wishPoolBean);
		session.setAttribute("wishDetail", detail);
		
		//透過wishNumber取得該許願的所有圖片
		List<WishPictureBean> pictures = wishPictureService.getWishPic(wishNumber);
		session.setAttribute("wishPics", pictures);
		
		//透過wishNumber取得該許願的所有留言資訊
		List<WishMsgBean> messages = wishMsgService.getWishMsg(wishNumber);
		session.setAttribute("msgDetail", messages);
		
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
		response.sendRedirect(request.getContextPath()+"/wish/wishdetail.jsp");
		return;
	}

	
}
