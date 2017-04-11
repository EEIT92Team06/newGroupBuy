package controller;

import java.io.IOException;
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

import login.model.LoginService;
import login.model.MemberBean;

@WebServlet("/loginServlet.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginService loginService;

	@Override
	public void init() throws ServletException {
		ServletContext application = this.getServletContext();
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(application);
		loginService = (LoginService) context.getBean("loginService");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Map<String, String> errorMessages = new HashMap<String, String>();
		request.setAttribute("errorMessages", errorMessages);
		response.setContentType("UTF-8");
		HttpSession session = request.getSession();
		// 抓資料
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		// 判斷是否有輸入
		if (account == null || account.trim().length() == 0) {
			errorMessages.put("accountError", "請輸入帳號");
		}
		if (password == null || password.trim().length() == 0) {
			errorMessages.put("passwordError", "請輸入密碼");
		}
		// 空值forward到原登入畫面
		if (!errorMessages.isEmpty()) {
			request.getRequestDispatcher("/secure/login.jsp").forward(request, response);
			return;
		}
		// 驗證資料
		MemberBean memberBean = loginService.login(account, password);
		if (memberBean != null) {
			int statusNum = loginService.checkRegistryStatus(account);
			String ban = loginService.AfterBanTime(memberBean.getMemberNo());
			System.out.println("ban : " + ban);
			if (statusNum >= 9101) {
				if(!loginService.checkStatus(memberBean.getMemberNo())){
					String path = request.getContextPath();
					response.sendRedirect(path + "/secure/ban.jsp");
					return;
				}else{
					session.setAttribute("loginToken", memberBean);
					String path = request.getContextPath();
					response.sendRedirect(path + "/theindex.jsp");
					return;
				}
			} else {
				String path = request.getContextPath();
				response.sendRedirect(path + "/secure/sendregistryemail.jsp");
			}
		} else { 
			errorMessages.put("loginError", "帳號或密碼錯誤");
			request.getRequestDispatcher("/secure/login.jsp").forward(request, response);
			return;
		}
	}

}
