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

import searchgroup.model.MemberBean;
import searchgroup.model.SearchService;

@WebServlet("/searchgroup/search.controller")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SearchService searchService;
	@Override
	public void init() throws ServletException {
		ServletContext application = this.getServletContext();
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(application);
		searchService= (SearchService)context.getBean("searchService");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("UTF-8");
//		接收資料
		String productTypeNo = request.getParameter("productTypeNo");
		System.out.println("productTypeNo : " + productTypeNo);
		int IntproductTypeNo= Integer.parseInt(productTypeNo);
		List<Map<String, String>> result = searchService.select(IntproductTypeNo);
		 List<Map<String, String>> groupTypeResult = searchService.selectGroupType();
		request.setAttribute("groupType", groupTypeResult);
		request.setAttribute("groupInfo", result);

//		//以下做update click groupType值  
		HttpSession session = request.getSession();
		MemberBean memberBean = (MemberBean)session.getAttribute("loginOK");
		Integer memberNo = memberBean.getMemberNo();
		System.out.println("memberNo : "+memberNo);
		int insertResult = searchService.insertClickTimes
				("update recommend set recommend_ProductType"+IntproductTypeNo+"=recommend_ProductType"+IntproductTypeNo+"+1 where member_No = "+memberNo);
		System.out.println("success update " + insertResult + " data");
		request.getRequestDispatcher(
				"/searchgroup/searchResult.jsp").forward(request, response);
		return;
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("UTF-8");
		//接收資料
		String name = request.getParameter("name");
		//驗證資料(無)
		//轉換資料(無)
		//呼叫model, 根據Model執行結果呼叫View
		List<Map<String, String>> result;
		if(name == null || name.trim().length() ==0){
			result = searchService.select(null);
		}else{
			result = searchService.select(name);
		}
		System.out.println("result : " + result );
		List<Map<String, String>> groupTypeResult = searchService.selectGroupType();
		
		request.setAttribute("groupType", groupTypeResult);
		request.setAttribute("groupInfo", result);
		request.getRequestDispatcher(
				"/searchgroup/searchResult.jsp").forward(request, response);
		return;
	}

}
