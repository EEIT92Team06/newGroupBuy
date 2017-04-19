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

import login.model.MemberBean;
import sitemail.model.SiteMailService;

@WebServlet("/mailAjaxServlet.do")
public class MailAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SiteMailService siteMailService;

	@Override
	public void init() throws ServletException {

		ServletContext application = this.getServletContext();
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(application);
		siteMailService = (SiteMailService) context.getBean("siteMailService");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(1111111);
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html ;charset=UTF-8");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		Map<String, Integer> totalUnRead = new HashMap<String, Integer>();
		HttpSession session=request.getSession();
		MemberBean memberBean=(MemberBean)session.getAttribute("loginToken");
		Integer unReadNum=siteMailService.getUnReadNum(memberBean);
		totalUnRead.put("unReadNum", unReadNum);
		out.println(gson.toJson(totalUnRead));
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
