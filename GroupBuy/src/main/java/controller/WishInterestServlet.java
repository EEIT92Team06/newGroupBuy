package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gson.Gson;

import member.model.MemberBean;
import wish.model.WishInterestBean;
import wish.model.WishInterestService;

@WebServlet("/wish/wishinterest.controller")
public class WishInterestServlet extends HttpServlet {
	Integer memberNo;
    private WishInterestService wishInterestService;
	@Override
	public void init() throws ServletException {
		ServletContext application = this.getServletContext();
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(application);
		wishInterestService = (WishInterestService)context.getBean("wishInterestService");
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 前端透過AJAX判斷按讚情況
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		Map<String, String> map = new HashMap<>();
		// 接收資料
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
		WishInterestBean bean = new WishInterestBean();
		bean.setMemberNo(memberNo);
		bean.setWishNo(wishNumber);

		boolean insert = wishInterestService.likeInsert(bean);
		if (insert == false) {
			wishInterestService.likeDelete(bean);
		}
		int count = wishInterestService.likeCount(wishNumber);
		String likes = Integer.toString(count);
		if (count == 0) {
			map.put("like", "成為第一個喜歡此商品的人!");
		} else {
			map.put("like", likes+"個人對此商品有興趣!");
		}
		boolean status = wishInterestService.likeOrNot(wishNumber, memberNo);
		if(status==true){
			map.put("likeOrNot", "收回讚");
		}else{
			map.put("likeOrNot", "有興趣");
		}
		out.println(gson.toJson(map));
		out.close();
	}
}
