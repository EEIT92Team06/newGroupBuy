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
import searchgroup.model.GroupMsgService;
import searchgroup.model.SearchService;

@WebServlet("/Backstage/BackStageServlet.controller")
public class BackStageServlet extends HttpServlet {
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
		request.setCharacterEncoding("UTF-8");
		response.setContentType("UTF-8");
		List<Map<String, String>> members = announcementService.select();
		List<Map<String, String>> Partmembers = announcementService.selectPartMem();
		List<Map<String, String>> Allgroup = searchService.select(null);
		List<Map<String, String>> reports = announcementService.selectReports();
		HttpSession session = request.getSession();
		session.setAttribute("members", members);
		session.setAttribute("Partmembers", Partmembers);
		session.setAttribute("Allgroup", Allgroup);
		session.setAttribute("Allreports", reports); 
		String contextPath = getServletContext().getContextPath();
		response.sendRedirect(response.encodeRedirectURL(contextPath+"/Backstage/backstage0.jsp"));
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
