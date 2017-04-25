package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
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

import login.model.LoginService;
import login.model.MemberBean;
import searchgroup.model.SearchService;

@WebServlet("/loginServlet.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginService loginService;
	private SearchService searchService;

	@Override
	public void init() throws ServletException {
		ServletContext application = this.getServletContext();
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(application);
		loginService = (LoginService) context.getBean("loginService");
		searchService = (SearchService) context.getBean("searchService");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Map<String, String> errorMessages = new HashMap<String, String>();
		Map<String, String> successLogin = new HashMap<String, String>();
		Map<String, String> banLogin = new HashMap<String, String>();
		Map<String, String> unFinishLogin = new HashMap<String, String>();
		request.setAttribute("errorMessages", errorMessages);
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		// 抓資料
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		// 判斷是否有輸入
		if ((account == null || account.trim().length() == 0) || (password == null || password.trim().length() == 0)) {
			if (account == null || account.trim().length() == 0) {
				errorMessages.put("accountError", "請輸入帳號");

			}
			if (password == null || password.trim().length() == 0) {
				errorMessages.put("passwordError", "請輸入密碼");
			}
			out.println(gson.toJson(errorMessages));
			out.close();
		}
		// 驗證資料
		MemberBean memberBean = loginService.login(account, password);
		if (memberBean != null) {
			// 0415 Kai加的----------------------------------
			memberBean.getMemberNo();
			try {
				int xxx = searchService.selectRecommendTable(memberBean.getMemberNo());
			} catch (Exception e) {
				int result = searchService.insertRecommend(memberBean.getMemberNo());
				System.out.println("成功新增 " + result + "筆recommend資料");
			}
			// 0415 Kai加的End--------------------------------

			int statusNum = loginService.checkRegistryStatus(account);
			String ban = loginService.AfterBanTime(memberBean.getMemberNo());
			System.out.println("ban : " + ban);

			if (statusNum >= 9101) {
				if (!loginService.checkStatus(memberBean.getMemberNo())) {
					Timestamp banT = loginService.selectban(memberBean.getMemberNo());
					session.setAttribute("loginToken", memberBean);
					session.setAttribute("banT", banT);
					String path = request.getContextPath();
					banLogin.put("banUrl", path + "/secure/ban.jsp");
					out.println(gson.toJson(banLogin));
					out.close();			
				} else if((statusNum==9101) ||(statusNum==9102)) {
					session.setAttribute("loginToken", memberBean);
					String path = request.getContextPath();
					successLogin.put("indexUrl", path+"/theindex.jsp");
					out.println(gson.toJson(successLogin));
					out.close();
				}else if(statusNum==9104){
					session.setAttribute("loginToken", memberBean);
					session.setAttribute("managerLogin", memberBean);
					String path = request.getContextPath();
					successLogin.put("backStageUrl", path+"/Backstage/BackStageServlet.controller");
					out.println(gson.toJson(successLogin));
					out.close();
				}
			} else {
				String path = request.getContextPath();
				unFinishLogin.put("unFinishLogin", path + "/secure/sendregistryemail.jsp");
				out.println(gson.toJson(unFinishLogin));
				out.close();
			}
		} else {
			errorMessages.put("loginError", "帳號或密碼錯誤");
			out.println(gson.toJson(errorMessages));
			out.close();

		}
	}

}
