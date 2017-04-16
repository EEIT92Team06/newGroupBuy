package backstage.controller;

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

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import insertAnnounce.model.AnnouncementService;
import searchgroup.model.SearchService;

@WebServlet("/Backstage/banGroupServlet")
public class banGroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AnnouncementService announcementService;
	private SearchService searchService;
	@Override
	public void init() throws ServletException {
		ServletContext application = this.getServletContext();
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(application);
		announcementService = (AnnouncementService)context.getBean("announceService");
		searchService = (SearchService)context.getBean("searchService");
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("UTF-8");
		String[] checkboxs = request.getParameterValues("checkbox");
		
		for(String checkbox : checkboxs){
			announcementService.modifyGroupAndOrder(Integer.parseInt(checkbox));
		}
		List<Map<String, String>> Allgroup = searchService.select(null);
		HttpSession session = request.getSession();
		session.setAttribute("Allgroup", Allgroup);
		String contextPath = getServletContext().getContextPath();
		response.sendRedirect(response.encodeRedirectURL(contextPath+"/Backstage/newbackstage3.jsp"));
		return;
	}

}
