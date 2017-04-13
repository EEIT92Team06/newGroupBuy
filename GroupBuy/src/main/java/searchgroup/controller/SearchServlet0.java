package searchgroup.controller;

import java.io.IOException;
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

import login.model.MemberBean;
import searchgroup.model.SearchService;

@WebServlet("/headline/SearchServlet0.controller")
public class SearchServlet0 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SearchService searchService;
	
	@Override
	public void init() throws ServletException {
		ServletContext application = this.getServletContext();
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(application);
		searchService = (SearchService)context.getBean("searchService");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("UTF-8");
		//以下是熱門推薦團的程式	
		List<Map<String, String>> selectTop2Group = searchService.selectTop2Group();
		HttpSession session = request.getSession();
		session.setAttribute("selectTop2Group", selectTop2Group);
		String contextPath = getServletContext().getContextPath();
		
		//以下是推薦團的程式	
		MemberBean memberBean = (MemberBean)session.getAttribute("loginToken");
		System.out.println("memberNo : " + memberBean.getMemberNo());
		Integer memberNo = memberBean.getMemberNo();
		int groupTypeNo = searchService.selectRecommendTable(memberNo);
		List<Map<String, String>> recommendGroup = searchService.select(groupTypeNo);
		session.setAttribute("recommendGroup", recommendGroup);
		
		response.sendRedirect(response
				.encodeRedirectURL(contextPath+"/searchgroup/search.jsp"));
		return;
	}

}
